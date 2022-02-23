package danieladamzoltan.userservice.repositories;

import danieladamzoltan.userservice.models.EmailConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailConfirmationTokenRepository extends JpaRepository<EmailConfirmationToken, Long> {
    EmailConfirmationToken findByEmailConfirmationToken(String emailConfirmationToken);

}
