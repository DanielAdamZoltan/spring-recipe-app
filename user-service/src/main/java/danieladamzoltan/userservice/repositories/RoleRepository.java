package danieladamzoltan.userservice.repositories;

import danieladamzoltan.userservice.models.ERole;
import danieladamzoltan.userservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
