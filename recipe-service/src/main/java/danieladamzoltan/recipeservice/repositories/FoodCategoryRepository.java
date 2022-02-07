package danieladamzoltan.recipeservice.repositories;

import danieladamzoltan.recipeservice.models.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {


}
