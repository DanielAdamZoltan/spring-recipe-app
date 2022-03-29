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
@Table(name = "RECIPE_STEP")
public class RecipeStep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long stepId;

//    @ManyToOne
//    @JoinColumn(name = "id", nullable = false)
//    @Id
//    @GeneratedValue
//    private Recipe recipeStep;
//
//    // lenght = 2
//    @Column(name = "step_number", length = 2, nullable = false)
//    private int stepNumber;
//
//    @Column(name = "step_instructions", nullable = false)
//    private String stepInstructions;

}
