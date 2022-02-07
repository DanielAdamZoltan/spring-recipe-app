package danieladamzoltan.recipeservice.services;

import danieladamzoltan.recipeservice.models.Cuisine;
import danieladamzoltan.recipeservice.repositories.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CuisineService {

    private final CuisineRepository cuisineRepository;

    @Autowired
    public CuisineService(CuisineRepository cuisineRepository) {
        this.cuisineRepository = cuisineRepository;
    }

    public List<Cuisine> findAllCuisines(){ return cuisineRepository.findAll(); }


}
