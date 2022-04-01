package danieladamzoltan.recipeservice.services;

import danieladamzoltan.recipeservice.exception.NotFoundException;
//import danieladamzoltan.recipeservice.persistence.models.RecipeStep;
//import danieladamzoltan.recipeservice.persistence.dao.RecipeStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Service
//@Transactional
//public class RecipeStepService {
//
//    private final RecipeStepRepository recipeStepRepository;
//
//    @Autowired
//    public RecipeStepService(RecipeStepRepository recipeStepRepository) {
//        this.recipeStepRepository = recipeStepRepository;
//    }
//
//    public List<RecipeStep> findAllRecipeSteps(){ return recipeStepRepository.findAll(); }
//
//    public RecipeStep addRecipeStep(RecipeStep recipeStep) {
//        return recipeStepRepository.save(recipeStep);
//    }
//
//    public RecipeStep updateRecipeStep(RecipeStep recipeStep) {
//        return recipeStepRepository.save(recipeStep);
//    }
//
//    public RecipeStep findRecipeStepById(Long id) {
//        return recipeStepRepository.findRecipeStepById(id)
//                .orElseThrow(() -> new NotFoundException("Recipe Step by Id " + id + " was not found!"));
//    }
//
//    public void deleteRecipeStepById(Long id) {
//        recipeStepRepository.deleteRecipeStepById(id);
//    }
//
//
////    public RecipeStep findRecipeStepByName(String name){ return recipeStepRepository.findRecipeStepByName(name)
////            .orElseThrow(() -> new NotFoundException("Recipe Step by Name " + name + " was not found!"));
////    }
//}
