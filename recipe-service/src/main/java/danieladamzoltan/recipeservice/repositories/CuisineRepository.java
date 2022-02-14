package danieladamzoltan.recipeservice.repositories;

import danieladamzoltan.recipeservice.models.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

    void deleteCuisineById(Long id);

    Optional<Cuisine> findCuisineById(Long id);

    @Query("SELECT name FROM Cuisine ORDER BY name")
    List<String> getCuisineByName();
}
