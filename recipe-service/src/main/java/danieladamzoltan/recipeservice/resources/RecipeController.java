package danieladamzoltan.recipeservice.resources;

import danieladamzoltan.recipeservice.models.Cuisine;
import danieladamzoltan.recipeservice.models.Ingredient;
import danieladamzoltan.recipeservice.models.RecipeCategory;
import danieladamzoltan.recipeservice.services.CuisineService;
import danieladamzoltan.recipeservice.services.FoodCategoryService;
import danieladamzoltan.recipeservice.services.IngredtientService;
import danieladamzoltan.recipeservice.services.RecipeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class RecipeController {

    private final IngredtientService ingredtientService;
    private final RecipeCategoryService recipeCategoryService;
    private final CuisineService cuisineService;

    @Autowired
    public RecipeController(IngredtientService ingredtientService, FoodCategoryService foodCategoryService, CuisineService cuisineService) {
        this.ingredtientService = ingredtientService;
        this.foodCategoryService = foodCategoryService;
        this.cuisineService = cuisineService;
    }

    @GetMapping("ingredient/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients(){
        List<Ingredient> ingredients = ingredtientService.findAllIngredientList();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("ingredient/find/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") Long id){
        Ingredient ingredient = ingredtientService.findIngredientById(id);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

//    @GetMapping("ingredient/find/{name}")
//    public ResponseEntity<Ingredient> getIngredientByName(@PathVariable("name") String name){
//        Ingredient ingredient = ingredtientService.findIngredientByName(name);
//        return new ResponseEntity<>(ingredient, HttpStatus.OK);
//    }

    @GetMapping("category/all")
    public ResponseEntity<List<RecipeCategory>> getAllFoodCategories(){
        List<RecipeCategory> foodCategories = foodCategoryService.findAllCategories();
        return new ResponseEntity<>(foodCategories, HttpStatus.OK);
    }

    @GetMapping("category/name")
    public ResponseEntity<List<String>> getAllRecipeCategoriesName(){
        List<String> recipeCategories = recipeCategoryService.getAllRecipeCategoriesName();
        return new ResponseEntity<>(recipeategories, HttpStatus.OK);
    }

    @GetMapping("cuisine/all")
    public ResponseEntity<List<Cuisine>> getAllCuisines(){
        List<Cuisine> cuisines = cuisineService.findAllCuisines();
        return new ResponseEntity<>(cuisines, HttpStatus.OK);
    }

}
