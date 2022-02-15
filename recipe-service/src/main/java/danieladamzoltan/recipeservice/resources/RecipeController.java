package danieladamzoltan.recipeservice.resources;

import danieladamzoltan.recipeservice.models.Cuisine;
import danieladamzoltan.recipeservice.models.Ingredient;
import danieladamzoltan.recipeservice.models.RecipeCategory;
import danieladamzoltan.recipeservice.services.*;
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
    private final RecipeService recipeService;
    private final RecipeIngredientService recipeIngredientService;
    private final RecipeStepService recipeStepService;

    @Autowired
    public RecipeController(
            IngredtientService ingredtientService,
            RecipeCategoryService recipeCategoryService,
            CuisineService cuisineService, RecipeService recipeService,
            RecipeIngredientService recipeIngredientService,
            RecipeStepService recipeStepService) {
        this.ingredtientService = ingredtientService;
        this.recipeCategoryService = recipeCategoryService;
        this.cuisineService = cuisineService;
        this.recipeService = recipeService;
        this.recipeIngredientService = recipeIngredientService;
        this.recipeStepService = recipeStepService;
    }




    @GetMapping("ingredient/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients(){
        List<Ingredient> ingredients = ingredtientService.findAllIngredient();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

//    @GetMapping("ingredient/find/{id}")
//    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") Long id){
//        Ingredient ingredient = ingredtientService.findIngredientById(id);
//        return new ResponseEntity<>(ingredient, HttpStatus.OK);
//    }

//    @GetMapping("ingredient/find/{name}")
//    public ResponseEntity<Ingredient> getIngredientByName(@PathVariable("name") String name){
//        Ingredient ingredient = ingredtientService.findIngredientByName(name);
//        return new ResponseEntity<>(ingredient, HttpStatus.OK);
//    }

    @GetMapping("category/all")
    public ResponseEntity<List<RecipeCategory>> getAllRecipeCategories(){
        List<RecipeCategory> RecipeCategories = recipeCategoryService.findAllCategories();
        return new ResponseEntity<>(RecipeCategories, HttpStatus.OK);
    }

//    @GetMapping("category/name")
//    public ResponseEntity<List<String>> getAllRecipeCategoriesName(String name){
//        List<String> recipeCategories = (List<String>) recipeCategoryService.findRecipeCategoryByName(name);
//        return new ResponseEntity<>(recipeCategories, HttpStatus.OK);
//    }

    @GetMapping("cuisine/all")
    public ResponseEntity<List<Cuisine>> getAllCuisines(){
        List<Cuisine> cuisines = cuisineService.findAllCuisines();
        return new ResponseEntity<>(cuisines, HttpStatus.OK);
    }

}
