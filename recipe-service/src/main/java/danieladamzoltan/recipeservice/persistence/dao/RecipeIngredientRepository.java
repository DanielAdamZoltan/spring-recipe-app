package danieladamzoltan.recipeservice.persistence.dao;

import danieladamzoltan.recipeservice.persistence.models.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    void deleteRecipeIngredientById(Long id);

    Optional<RecipeIngredient> findRecipeIngredientById(Long id);

    //    @Query("SELECT name FROM ingredients WHERE id=?1")
//    Optional<RecipeIngredient> findRecipeIngredientByName(String name);
}
