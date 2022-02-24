package danieladamzoltan.userservice.services;

import danieladamzoltan.userservice.persistence.models.User;
import danieladamzoltan.userservice.persistence.models.VerificationToken;
import danieladamzoltan.userservice.persistence.models.request.LoginRequest;
import danieladamzoltan.userservice.persistence.models.request.RegisterRequest;
import danieladamzoltan.userservice.persistence.models.response.JwtResponse;

public interface IUserService {

    User registerNewUser(RegisterRequest registerRequest);

    JwtResponse authenticateUser(LoginRequest loginRequest);

    void saveRegisteredUser(User user);

    void createVerificationTokenForUser(final User user, final String token);

    VerificationToken getVerificationToken(final String VerificationToken);
}
