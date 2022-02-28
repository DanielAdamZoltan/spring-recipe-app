package danieladamzoltan.userservice.persistence.models.dto;

import danieladamzoltan.userservice.validation.ValidPassword;
import lombok.Data;

@Data
public class PasswordDto {

    private String oldPassword;
    private String token;

    @ValidPassword
    private String newPassword;
}
