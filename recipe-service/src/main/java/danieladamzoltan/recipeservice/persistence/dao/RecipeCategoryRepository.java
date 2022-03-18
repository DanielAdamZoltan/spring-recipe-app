package danieladamzoltan.recipeservice.persistence.dao;

import danieladamzoltan.recipeservice.persistence.models.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> {

    void deleteRecipeCategoryById(Long id);

    Optional<RecipeCategory> findRecipeCategoryById(Long id);

    //    @Query("SELECT name FROM RecipeCategory WHERE id=?1")
    Optional<RecipeCategory> findRecipeCategoryByName(String name);
}
