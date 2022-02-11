package danieladamzoltan.recipeservice.services;

import danieladamzoltan.recipeservice.models.RecipeCategory;
import danieladamzoltan.recipeservice.repositories.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public List<RecipeCategory> findAllCategories(){ return foodCategoryRepository.findAll(); }

    public List<String> getAllCategoriesName(){ return foodCategoryRepository.getFoodCategoryByName();}
}
