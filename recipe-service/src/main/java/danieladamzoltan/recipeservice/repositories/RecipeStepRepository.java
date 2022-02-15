package danieladamzoltan.recipeservice.repositories;

import danieladamzoltan.recipeservice.models.Ingredient;
import danieladamzoltan.recipeservice.models.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {

    void deleteRecipeStepById(Long id);

    Optional<RecipeStep> findRecipeStepById(Long id);

    //    @Query("SELECT name FROM ingredients WHERE id=?1")
//    Optional<RecipeStep> findRecipeStepByName(String name);
}