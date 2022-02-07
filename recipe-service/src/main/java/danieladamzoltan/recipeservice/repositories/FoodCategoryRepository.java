package danieladamzoltan.recipeservice.repositories;

import danieladamzoltan.recipeservice.models.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

    @Query("SELECT name FROM FoodCategory ORDER BY name")
    List<String> getFoodCategoryByName();





}
