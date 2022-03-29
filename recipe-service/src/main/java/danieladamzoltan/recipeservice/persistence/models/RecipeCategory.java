package danieladamzoltan.recipeservice.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "RECIPE_CATEGORY")
public class RecipeCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

//    @OneToOne(mappedBy = "recipe_categories")
//    @OneToOne
//    private Recipe recipe;

    @Column(name = "name", length = 16, unique = true, nullable = false)
    private String name;

}
