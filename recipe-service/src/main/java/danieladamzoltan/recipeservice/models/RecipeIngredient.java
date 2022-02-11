package danieladamzoltan.recipeservice.models;

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
@Table(name = "recipe_ingredients")
public class RecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long RecipeIngredientId;

//    @ManyToOne
//    @JoinColumn(name = "recipe_id", nullable = false)
//    private Recipe recipe;

    @Column(name = "amount", length = 4, nullable = false)
    private int amount;

//    @Column(name = "ingredient_id")
    @OneToOne()
//    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Ingredient id;


}
