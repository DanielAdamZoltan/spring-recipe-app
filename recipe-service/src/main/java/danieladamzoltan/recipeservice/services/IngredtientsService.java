package danieladamzoltan.recipeservice.services;

import danieladamzoltan.recipeservice.exception.NotFoundException;
import danieladamzoltan.recipeservice.models.Ingredient;
import danieladamzoltan.recipeservice.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IngredtientsService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredtientsService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAllIngredientList(){ return ingredientRepository.findAll(); }

    public Ingredient findIngredientById(Long id){
        return ingredientRepository.findIngredientBy(id)
                .orElseThrow(() -> new NotFoundException("Ingredient by ID " + id + "was not found!"));
    }

    public Ingredient findIngredientByName(String name){
        return ingredientRepository.findIngredientByName(name)
                .orElseThrow(() -> new NotFoundException("Ingredient by Name " + name + "was not found!"));
    }
}
