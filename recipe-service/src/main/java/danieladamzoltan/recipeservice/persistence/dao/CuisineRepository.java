package danieladamzoltan.recipeservice.persistence.dao;

import danieladamzoltan.recipeservice.persistence.models.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

    void deleteCuisineById(Long id);

    Optional<Cuisine> findCuisineById(Long id);

//    @Query("SELECT name FROM Cuisine ORDER BY name")
//    List<String> findCuisineByName();

    Optional<Cuisine> findCuisineByName(String name);
}
