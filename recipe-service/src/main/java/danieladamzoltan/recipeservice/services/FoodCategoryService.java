package danieladamzoltan.recipeservice.services;

import danieladamzoltan.recipeservice.models.FoodCategory;
import danieladamzoltan.recipeservice.models.Ingredient;
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

    public List<FoodCategory> findAllCategories(){ return foodCategoryRepository.findAll(); }
}
