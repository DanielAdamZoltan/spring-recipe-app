package danieladamzoltan.recipeservice.services;

import danieladamzoltan.recipeservice.exception.NotFoundException;
import danieladamzoltan.recipeservice.persistence.models.Ingredient;
import danieladamzoltan.recipeservice.persistence.dao.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }


    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteIngredientById(id);
    }


    public Ingredient findIngredientByName(String name){ return ingredientRepository.findIngredientByName(name)
            .orElseThrow(() -> new NotFoundException("Ingredient by Name " + name + "was not found!"));
    }

    public List<Ingredient> findAllIngredient(){ return ingredientRepository.findAll(); }

    public Ingredient findIngredientById(Long id){
        return ingredientRepository.findIngredientById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient by ID " + id + "was not found!"));
    }
}
