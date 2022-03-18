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
@Table(name = "recipe_ingredient")
public class RecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "recipe_id", nullable = false)
//    private Recipe recipe;

    @Column(name = "amount", length = 4, nullable = false)
    private int amount;

//    @OneToOne()
//    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    @Column(name = "ingredient_id")
    private Long ingredientId;


}
