package danieladamzoltan.recipeservice.resources;

import danieladamzoltan.recipeservice.models.Ingredient;
import danieladamzoltan.recipeservice.services.IngredtientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientResource {

    private final IngredtientService ingredtientService;

    @Autowired
    public IngredientResource(IngredtientService ingredtientService) {
        this.ingredtientService = ingredtientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients(){
        List<Ingredient> ingredients = ingredtientService.findAllIngredientList();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") Long id){
        Ingredient ingredient = ingredtientService.findIngredientById(id);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

//    @GetMapping("/find/{name}")
//    public ResponseEntity<Ingredient> getIngredientByName(@PathVariable("name") String name){
//        Ingredient ingredient = ingredtientService.findIngredientByName(name);
//        return new ResponseEntity<>(ingredient, HttpStatus.OK);
//    }


}
