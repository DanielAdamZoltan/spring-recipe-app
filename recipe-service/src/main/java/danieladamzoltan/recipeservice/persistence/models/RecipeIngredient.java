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
@Table(name = "RECIPE_INGREDIENT")
public class RecipeIngredient implements Serializable {

    //    @ManyToOne
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipeIngredient;

//    @ManyToOne
//    @JoinColumn(name = "recipe_id", nullable = false)
//    private Recipe recipe;
//    hiba
    // lenght = 4
    @Column(name = "amount", length = 4, nullable = false)
    private int amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit unitId;

//    @OneToOne()
//    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
//    @Column(name = "ingredient_id")
    @ManyToOne
    private Ingredient Ingredient;


}
