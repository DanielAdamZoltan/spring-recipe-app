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
@Table(name="recipe")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 60, nullable = false)
    private String title;

    @Column(name = "uploader_id")
    private Long uploaderId;

    @Column(name = "image_path")
    private String imagePath;

//    1perc - 999perc
    @Column(name = "time", length = 3, nullable = false)
    private int time;

//    1-12
    @Column(name = "portion", length = 2, nullable = false)
    private int portion;

//  0 -> nagyon olcsó
//  1 -> olcsó
//  2 -> megfizethető
//  3 -> drága
    @Column(name = "price", length = 1, nullable = false)
    private int price;

//  0 -> könnyű
//  1 -> közepes
//  2 -> nehéz
    @Column(name = "difficulty", length = 1, nullable = false)
    private int difficulty;

//  from database
//    @OneToOne()
//    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @Column(name = "category_id")
    private Long categoryId;

//  from database
//    @OneToOne()
//    @JoinColumn(name = "cuisine_id", referencedColumnName = "id")
    @Column(name = "cuisine_id")
    private Long cuisineId;

//  0 -> reggeli
//  1 -> tízorai
//  2 -> ebéd
//  3 -> uzsonna
//  4 -> vacsora
    @Column(name = "part_of_the_day", length = 1, nullable = false)
    private int partOfTheDay;

//    0 -> tavasz
//    1 -> nyár
//    2 -> ősz
//    3 -> tél
    @Column(name = "season", length = 1, nullable = false)
    private int season;
//    @OneToMany(
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    @JoinColumn(name = "recipe_ingredient_id")
//    @OneToMany(mappedBy = "recipe")
//    @OneToMany
//    private List<RecipeIngredient> recipeIngredientId = new ArrayList<>();

    @Column(name = "recipe_ingredient_id ")
    private Long recipeIngredientId;
//    private List<RecipeIngredient> recipeIngredientId = new ArrayList<>();


//    @OneToMany(
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    @JoinColumn(name = "recipe_step_id")
//    @OneToMany(mappedBy = "recipe")
//    @OneToMany
    @Column(name = "recipe_step_id")
    private Long recipeStepId;
//    private List<RecipeStep> recipeStepId = new ArrayList<>();
}
