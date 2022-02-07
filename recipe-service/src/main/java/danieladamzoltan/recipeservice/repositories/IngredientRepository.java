package danieladamzoltan.recipeservice.repositories;

import danieladamzoltan.recipeservice.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findIngredientById(Long id);

//    @Query("SELECT name FROM ingredients WHERE id=?1")
    Optional<Ingredient> findIngredientByName(String name);
}
