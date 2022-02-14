package danieladamzoltan.recipeservice.services;

import danieladamzoltan.recipeservice.exception.NotFoundException;
import danieladamzoltan.recipeservice.models.Cuisine;
import danieladamzoltan.recipeservice.models.RecipeCategory;
import danieladamzoltan.recipeservice.repositories.RecipeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RecipeCategoryService {

    private final RecipeCategoryRepository recipeCategoryRepository;

    @Autowired
    public RecipeCategoryService(RecipeCategoryRepository recipeCategoryRepository) {
        this.recipeCategoryRepository = recipeCategoryRepository;
    }

    public List<RecipeCategory> findAllCategories(){ return recipeCategoryRepository.findAll(); }

    public RecipeCategory addRecipeCategory(RecipeCategory cuisine) {
//        cuisine.setRecipeCategoryCode(UUID.randomUUID().toString());
        return recipeCategoryRepository.save(cuisine);
    }

    public RecipeCategory updateCuisine(RecipeCategory cuisine) {
        return recipeCategoryRepository.save(cuisine);
    }

    public RecipeCategory findCuisineById(Long id) {
        return recipeCategoryRepository.findRecipeCategoryById(id)
                .orElseThrow(() -> new NotFoundException("Recipe Category by Id " + id + " was not found!"));
    }

    public void deleteRecipeCategory(Long id) {
        recipeCategoryRepository.deleteRecipeCategoryById(id);
    }


    public List<String> getAllRecipeCategoryName(String name){ return recipeCategoryRepository.findRecipeCategoryByName(name);}
}
