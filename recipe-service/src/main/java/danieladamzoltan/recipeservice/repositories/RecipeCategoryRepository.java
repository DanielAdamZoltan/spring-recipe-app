package danieladamzoltan.recipeservice.repositories;

import danieladamzoltan.recipeservice.models.Ingredient;
import danieladamzoltan.recipeservice.models.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> {

    void deleteRecipeCategoryById(Long id);

    Optional<RecipeCategory> findRecipeCategoryById(Long id);

    //    @Query("SELECT name FROM RecipeCategory WHERE id=?1")
    Optional<RecipeCategory> findRecipeCategoryByName(String name);
}
