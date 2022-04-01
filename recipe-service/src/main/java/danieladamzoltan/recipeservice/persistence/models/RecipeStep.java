package danieladamzoltan.recipeservice.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Table(name = "RECIPE_STEP")
//public class RecipeStep implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

//  mappedBy = "recipeStep"
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "steps", referencedColumnName = "id")
//    private Set<Step> steps;

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

//}
