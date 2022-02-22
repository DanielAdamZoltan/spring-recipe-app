package danieladamzoltan.userservice.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class JwtResponse {

    private String jwt;
    private Long id;
    private String email;
    private List<String> roles;

}
