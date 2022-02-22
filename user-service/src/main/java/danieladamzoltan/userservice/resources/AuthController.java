package danieladamzoltan.userservice.resources;

import danieladamzoltan.userservice.exception.NotFoundException;
import danieladamzoltan.userservice.jwt.util.JwtUtil;
import danieladamzoltan.userservice.models.ERole;
import danieladamzoltan.userservice.models.Role;
import danieladamzoltan.userservice.models.User;
import danieladamzoltan.userservice.models.request.LoginRequest;
import danieladamzoltan.userservice.models.request.RegisterRequest;
import danieladamzoltan.userservice.models.response.JwtResponse;
import danieladamzoltan.userservice.models.response.MessageResponse;
import danieladamzoltan.userservice.repositories.RoleRepository;
import danieladamzoltan.userservice.repositories.UserRepository;
import danieladamzoltan.userservice.services.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authentication")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //maybe error cause this line
        String jwt = jwtUtil.generateToken((UserDetails) authentication);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = customUserDetails.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                customUserDetails.getId(),
                customUserDetails.getUsername(),
                roles
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()), registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getActive());
        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() ->  new NotFoundException("Error: role is not found!"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new NotFoundException("Error: role is not found!"));
                        roles.add(adminRole);
                        break;
                    case "moderator":
                        Role moderatorRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new NotFoundException("Error: role is not found!"));
                        roles.add(moderatorRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new NotFoundException("Error: role is not found!"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
//        user.setEmail("mukodj@mar.hu");
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
