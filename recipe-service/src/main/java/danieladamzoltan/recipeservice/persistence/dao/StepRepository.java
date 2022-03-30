package danieladamzoltan.recipeservice.persistence.dao;

import danieladamzoltan.recipeservice.persistence.models.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<Step, Long> {
}
