package danieladamzoltan.recipeservice.persistence.dao;

import danieladamzoltan.recipeservice.persistence.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    void deleteIngredientById(Long id);

    Optional<Ingredient> findIngredientById(Long id);

//    @Query("SELECT name FROM ingredients WHERE id=?1")
    Optional<Ingredient> findIngredientByName(String name);
}
