package danieladamzoltan.userservice.persistence.dao;

import danieladamzoltan.userservice.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
//@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    void deleteUserById(Long id);
}
