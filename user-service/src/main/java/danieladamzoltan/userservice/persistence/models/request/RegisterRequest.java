package danieladamzoltan.userservice.persistence.models.request;

import danieladamzoltan.userservice.validation.PasswordMatches;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@PasswordMatches
@ToString
public class RegisterRequest{

    @NotNull
    @NotEmpty
    @Size(max = 50)
//    @Email(regexp = "^[_A-Za-z0-9-+]+ (.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)* (.[A-Za-z]{2,})$")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 40)
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String firstName;

    @NotBlank
    @Size(max = 20)
    private String lastName;


    private Set<String> role;

}
