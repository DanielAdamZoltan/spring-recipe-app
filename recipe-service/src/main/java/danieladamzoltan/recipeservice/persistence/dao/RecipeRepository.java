package danieladamzoltan.recipeservice.persistence.dao;

import danieladamzoltan.recipeservice.persistence.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    void deleteRecipeById(Long id);

    Optional<Recipe> findRecipeById(Long id);

    //    @Query("SELECT name FROM Recipe WHERE id=?1")
    Optional<Recipe> findRecipeByTitle(String title);
}
