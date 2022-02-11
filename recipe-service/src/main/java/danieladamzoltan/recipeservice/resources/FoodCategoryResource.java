package danieladamzoltan.recipeservice.resources;

import danieladamzoltan.recipeservice.models.RecipeCategory;
import danieladamzoltan.recipeservice.services.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class FoodCategoryResource {

    private final FoodCategoryService foodCategoryService;

    @Autowired
    public FoodCategoryResource(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecipeCategory>> getAllFoodCategories(){
        List<RecipeCategory> foodCategories = foodCategoryService.findAllCategories();
        return new ResponseEntity<>(foodCategories, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<String>> getAllFoodCategoriesName(){
        List<String> foodCategories = foodCategoryService.getAllCategoriesName();
        return new ResponseEntity<>(foodCategories, HttpStatus.OK);
    }
}
