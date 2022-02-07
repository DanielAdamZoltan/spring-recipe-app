package danieladamzoltan.recipeservice.repositories;

import danieladamzoltan.recipeservice.models.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

}
