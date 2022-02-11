package danieladamzoltan.recipeservice.repositories;

import danieladamzoltan.recipeservice.models.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodCategoryRepository extends JpaRepository<RecipeCategory, Long> {

    @Query("SELECT name FROM RecipeCategory ORDER BY name")
    List<String> getFoodCategoryByName();





}
