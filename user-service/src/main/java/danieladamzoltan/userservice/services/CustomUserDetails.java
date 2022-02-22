package danieladamzoltan.userservice.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import danieladamzoltan.userservice.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private Boolean active;
    private Collection<? extends GrantedAuthority> authorities;

//    public CustomUserDetails(Long id, String email, String password, Boolean active, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//        this.active = active;
//    }

    public CustomUserDetails(Long id, String email, String password, String firstName, String lastName, Boolean active, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.authorities = authorities;
    }

    public static CustomUserDetails build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.isActive(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public Long getId(){
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUserDetails that = (CustomUserDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(active, that.active) && Objects.equals(authorities, that.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, firstName, lastName, active, authorities);
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CustomUserDetails user = (CustomUserDetails) o;
//        return Objects.equals(id, user.id) && Objects.equals(email, user.email)
//                && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName)
//                && Objects.equals(lastName, user.lastName) && Objects.equals(active, user.active)
//                && Objects.equals(authorities, user.authorities);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, email, password, active, authorities);
//    }
}
