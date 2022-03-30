package danieladamzoltan.recipeservice.persistence.dao;

import danieladamzoltan.recipeservice.persistence.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
