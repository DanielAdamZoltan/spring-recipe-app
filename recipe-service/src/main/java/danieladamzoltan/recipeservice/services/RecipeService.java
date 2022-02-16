package danieladamzoltan.recipeservice.services;

import danieladamzoltan.recipeservice.exception.NotFoundException;
import danieladamzoltan.recipeservice.models.Recipe;
import danieladamzoltan.recipeservice.models.RecipeCategory;
import danieladamzoltan.recipeservice.repositories.IngredientRepository;
import danieladamzoltan.recipeservice.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAllRecipes(){ return recipeRepository.findAll(); }

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findRecipeById(id)
                .orElseThrow(() -> new NotFoundException("Recipe by Id " + id + " was not found!"));
    }

    public void deleteRecipeById(Long id) {
        recipeRepository.deleteRecipeById(id);
    }


    public Recipe findRecipeByTitle(String title){ return recipeRepository.findRecipeByTitle(title)
            .orElseThrow(() -> new NotFoundException("Recipe by Title " + title + " was not found!"));
    }
}
