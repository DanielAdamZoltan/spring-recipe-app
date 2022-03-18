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
@Table(name = "recipe_step")
public class RecipeStep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "recipe_id", nullable = false)
//    private Recipe recipe;

    @Column(name = "step_number", length = 2)
    private int stepNumber;

    @Column(name = "step_instructions")
    private String stepInstructions;

}
