package danieladamzoltan.recipeservice.services;


import danieladamzoltan.recipeservice.exception.NotFoundException;
import danieladamzoltan.recipeservice.models.Recipe;
import danieladamzoltan.recipeservice.models.RecipeIngredient;
import danieladamzoltan.recipeservice.repositories.RecipeIngredientRepository;
import danieladamzoltan.recipeservice.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public List<RecipeIngredient> findAllRecipeIngredients(){ return recipeIngredientRepository.findAll(); }

    public RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    public RecipeIngredient updateRecipeIngredient(RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    public RecipeIngredient findRecipeIngredientById(Long id) {
        return recipeIngredientRepository.findRecipeIngredientById(id)
                .orElseThrow(() -> new NotFoundException("Recipe Ingredient by Id " + id + " was not found!"));
    }

    public void deleteRecipeIngredient(Long id) {
        recipeIngredientRepository.deleteRecipeIngredientById(id);
    }


//    public RecipeIngredient findRecipeIngredientByName(String name){ return recipeIngredientRepository.findRecipeIngredientByName(name)
//            .orElseThrow(() -> new NotFoundException("Recipe Ingredient by Name " + name + " was not found!"));
//    }
}
