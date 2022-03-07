package danieladamzoltan.userservice.services;

import danieladamzoltan.userservice.persistence.models.PasswordResetToken;
import danieladamzoltan.userservice.persistence.models.User;
import danieladamzoltan.userservice.persistence.models.VerificationToken;
import danieladamzoltan.userservice.persistence.models.dto.LoginRequest;
import danieladamzoltan.userservice.persistence.models.dto.UserDto;
import danieladamzoltan.userservice.persistence.models.dto.JwtResponse;

import java.util.Optional;

public interface IUserService {

    User registerNewUserAdmin(UserDto userDto);

    User registerNewUser(UserDto userDto);

    JwtResponse authenticateUser(LoginRequest loginRequest);

    void saveRegisteredUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    void deleteUser(User user);

    void deleteUser(Long id, User user);

    User getUser(String verificationToken);

    Optional<User> getUserByID(long id);

    User findUserByEmail(String email);

    VerificationToken generateNewVerificationToken(String existingVerificationToken);

    void createPasswordResetTokenForUser(User user, String token);

    PasswordResetToken getPasswordResetToken(String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    String validateVerificationToken(String token);
}
