package danieladamzoltan.recipeservice.services;

import danieladamzoltan.recipeservice.exception.NotFoundException;
import danieladamzoltan.recipeservice.persistence.models.RecipeCategory;
import danieladamzoltan.recipeservice.persistence.dao.RecipeCategoryRepository;
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

    public RecipeCategory addRecipeCategory(RecipeCategory recipeCategory) {
        return recipeCategoryRepository.save(recipeCategory);
    }

    public RecipeCategory updateRecipeCategory(RecipeCategory recipeCategory) {
        return recipeCategoryRepository.save(recipeCategory);
    }

    public RecipeCategory findRecipeCategoryById(Long id) {
        return recipeCategoryRepository.findRecipeCategoryById(id)
                .orElseThrow(() -> new NotFoundException("Recipe Category by Id " + id + " was not found!"));
    }

    public void deleteRecipeCategoryById(Long id) {
        recipeCategoryRepository.deleteRecipeCategoryById(id);
    }


    public RecipeCategory findRecipeCategoryByName(String name){ return recipeCategoryRepository.findRecipeCategoryByName(name)
            .orElseThrow(() -> new NotFoundException("Recipe Category by Name " + name + " was not found!"));
    }
}
