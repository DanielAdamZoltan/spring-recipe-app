package danieladamzoltan.recipeservice.repositories;

import danieladamzoltan.recipeservice.models.Recipe;
import danieladamzoltan.recipeservice.models.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    void deleteRecipeById(Long id);

    Optional<Recipe> findRecipeById(Long id);

    //    @Query("SELECT name FROM Recipe WHERE id=?1")
    Optional<Recipe> findRecipeByName(String name);
}
