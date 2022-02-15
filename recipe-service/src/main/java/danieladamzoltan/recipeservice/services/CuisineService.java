package danieladamzoltan.recipeservice.services;

import danieladamzoltan.recipeservice.exception.NotFoundException;
import danieladamzoltan.recipeservice.models.Cuisine;
import danieladamzoltan.recipeservice.repositories.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CuisineService {

    private final CuisineRepository cuisineRepository;

    @Autowired
    public CuisineService(CuisineRepository cuisineRepository) {
        this.cuisineRepository = cuisineRepository;
    }


    public Cuisine addCuisine(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public Cuisine updateCuisine(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public Cuisine findCuisineById(Long id) {
        return cuisineRepository.findCuisineById(id)
                .orElseThrow(() -> new NotFoundException("Cuisine by Id " + id + " was not found!"));
    }

    public void deleteCuisineById(Long id) {
        cuisineRepository.deleteCuisineById(id);
    }

    public List<Cuisine> findAllCuisines(){ return cuisineRepository.findAll(); }

    public List<String> findCuisineByName(String name){ return cuisineRepository.findCuisineByName(name)
            .orElseThrow(() -> new NotFoundException("Ingredient by ID " + name + "was not found!"));
    }


}
