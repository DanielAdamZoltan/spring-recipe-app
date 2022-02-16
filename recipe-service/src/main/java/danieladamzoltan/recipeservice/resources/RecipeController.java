package danieladamzoltan.recipeservice.resources;

import danieladamzoltan.recipeservice.models.*;
import danieladamzoltan.recipeservice.services.*;
import danieladamzoltan.recipeservice.services.RecipeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RecipeController {

    private final IngredientService ingredientService;
    private final RecipeCategoryService recipeCategoryService;
    private final CuisineService cuisineService;
    private final RecipeService recipeService;
    private final RecipeIngredientService recipeIngredientService;
    private final RecipeStepService recipeStepService;

    @Autowired
    public RecipeController(
            IngredientService ingredtientService,
            RecipeCategoryService recipeCategoryService,
            CuisineService cuisineService, RecipeService recipeService,
            RecipeIngredientService recipeIngredientService,
            RecipeStepService recipeStepService) {
        this.ingredientService = ingredtientService;
        this.recipeCategoryService = recipeCategoryService;
        this.cuisineService = cuisineService;
        this.recipeService = recipeService;
        this.recipeIngredientService = recipeIngredientService;
        this.recipeStepService = recipeStepService;
    }

    
    //Ingredient

    @GetMapping("ingredient/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients(){
        List<Ingredient> ingredients = ingredientService.findAllIngredient();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @PostMapping("ingredient/add")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.addIngredient(ingredient);
        return new ResponseEntity<>(newIngredient, HttpStatus.CREATED);
    }


    @PutMapping("ingredient/update")
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient) {
        Ingredient updateIngredient = ingredientService.updateIngredient(ingredient);
        return new ResponseEntity<>(updateIngredient, HttpStatus.OK);
    }

    @DeleteMapping("ingredient/delete/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable("id") Long id) {
        ingredientService.deleteIngredientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
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

    //Recipe Category

    @GetMapping("category/all")
    public ResponseEntity<List<RecipeCategory>> getAllRecipeCategories(){
        List<RecipeCategory> RecipeCategories = recipeCategoryService.findAllCategories();
        return new ResponseEntity<>(RecipeCategories, HttpStatus.OK);
    }

    @PostMapping("category/add")
    public ResponseEntity<RecipeCategory> addRecipeCategory(@RequestBody RecipeCategory recipeCategory) {
        RecipeCategory newRecipeCategory = recipeCategoryService.addRecipeCategory(recipeCategory);
        return new ResponseEntity<>(newRecipeCategory, HttpStatus.CREATED);
    }


    @PutMapping("category/update")
    public ResponseEntity<RecipeCategory> updateRecipeCategory(@RequestBody RecipeCategory recipeCategory) {
        RecipeCategory updateRecipeCategory = recipeCategoryService.updateRecipeCategory(recipeCategory);
        return new ResponseEntity<>(updateRecipeCategory, HttpStatus.OK);
    }

    @DeleteMapping("category/delete/{id}")
    public ResponseEntity<?> deleteRecipeCategory(@PathVariable("id") Long id) {
        recipeCategoryService.deleteRecipeCategoryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("category/name")
//    public ResponseEntity<List<String>> getAllRecipeCategoriesName(String name){
//        List<String> recipeCategories = (List<String>) recipeCategoryService.findRecipeCategoryByName(name);
//        return new ResponseEntity<>(recipeCategories, HttpStatus.OK);
//    }

    //Cuisine

    @GetMapping("cuisine/all")
    public ResponseEntity<List<Cuisine>> getAllCuisines(){
        List<Cuisine> cuisines = cuisineService.findAllCuisines();
        return new ResponseEntity<>(cuisines, HttpStatus.OK);
    }

    @PostMapping("cuisine/add")
    public ResponseEntity<Cuisine> addCuisine(@RequestBody Cuisine cuisine) {
        Cuisine newCuisine = cuisineService.addCuisine(cuisine);
        return new ResponseEntity<>(newCuisine, HttpStatus.CREATED);
    }


    @PutMapping("cuisine/update")
    public ResponseEntity<Cuisine> updateCuisine(@RequestBody Cuisine cuisine) {
        Cuisine updateCuisine = cuisineService.updateCuisine(cuisine);
        return new ResponseEntity<>(updateCuisine, HttpStatus.OK);
    }

    @DeleteMapping("cuisine/delete/{id}")
    public ResponseEntity<?> deleteCuisine(@PathVariable("id") Long id) {
        cuisineService.deleteCuisineById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Recipe

    @GetMapping("recipe/all")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        List<Recipe> recipe = recipeService.findAllRecipes();
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PostMapping("recipe/add")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.addRecipe(recipe);
        return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
    }


    @PutMapping("recipe/update")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe) {
        Recipe updateRecipe = recipeService.updateRecipe(recipe);
        return new ResponseEntity<>(updateRecipe, HttpStatus.OK);
    }

    @DeleteMapping("recipe/delete/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id) {
        recipeService.deleteRecipeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Recipe Ingredient

    @GetMapping("recipe-ingredient/all")
    public ResponseEntity<List<RecipeIngredient>> getAllRecipeIngredients(){
        List<RecipeIngredient> recipeIngredient = recipeIngredientService.findAllRecipeIngredients();
        return new ResponseEntity<>(recipeIngredient, HttpStatus.OK);
    }

    @PostMapping("recipe-ingredient/add")
    public ResponseEntity<RecipeIngredient> addRecipeIngredient(@RequestBody RecipeIngredient recipeIngredient) {
        RecipeIngredient newRecipeIngredient = recipeIngredientService.addRecipeIngredient(recipeIngredient);
        return new ResponseEntity<>(newRecipeIngredient, HttpStatus.CREATED);
    }


    @PutMapping("recipe-ingredient/update")
    public ResponseEntity<RecipeIngredient> updateRecipeIngredient(@RequestBody RecipeIngredient recipeIngredient) {
        RecipeIngredient updateRecipeIngredient = recipeIngredientService.updateRecipeIngredient(recipeIngredient);
        return new ResponseEntity<>(updateRecipeIngredient, HttpStatus.OK);
    }

    @DeleteMapping("recipe-ingredient/delete/{id}")
    public ResponseEntity<?> deleteRecipeIngredient(@PathVariable("id") Long id) {
        recipeIngredientService.deleteRecipeIngredientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Recipe Step

    @GetMapping("recipe-step/all")
    public ResponseEntity<List<RecipeStep>> getAllRecipeSteps(){
        List<RecipeStep> recipeStep = recipeStepService.findAllRecipeSteps();
        return new ResponseEntity<>(recipeStep, HttpStatus.OK);
    }

    @PostMapping("recipe-step/add")
    public ResponseEntity<RecipeStep> addRecipeStep(@RequestBody RecipeStep recipeStep) {
        RecipeStep newRecipeStep = recipeStepService.addRecipeStep(recipeStep);
        return new ResponseEntity<>(newRecipeStep, HttpStatus.CREATED);
    }


    @PutMapping("recipe-step/update")
    public ResponseEntity<RecipeStep> updateRecipeStep(@RequestBody RecipeStep recipeStep) {
        RecipeStep updateRecipeStep = recipeStepService.updateRecipeStep(recipeStep);
        return new ResponseEntity<>(updateRecipeStep, HttpStatus.OK);
    }

    @DeleteMapping("recipe-step/delete/{id}")
    public ResponseEntity<?> deleteRecipeStep(@PathVariable("id") Long id) {
        recipeStepService.deleteRecipeStepById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
