package danieladamzoltan.recipeservice.repositories;

import danieladamzoltan.recipeservice.models.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

    @Query("SELECT name FROM Cuisine ORDER BY name")
    List<String> getCuisineByName();
}
