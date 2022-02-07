package danieladamzoltan.recipeservice.resources;

import danieladamzoltan.recipeservice.models.Cuisine;
import danieladamzoltan.recipeservice.services.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cuisine")
public class CuisineResource {

    private final CuisineService cuisineService;

    @Autowired
    public CuisineResource(CuisineService cuisineService) {
        this.cuisineService = cuisineService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cuisine>> getAllCuisines(){
        List<Cuisine> cuisines = cuisineService.findAllCuisines();
        return new ResponseEntity<>(cuisines, HttpStatus.OK);
    }
}
