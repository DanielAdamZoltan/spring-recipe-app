package danieladamzoltan.recipeservice.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "RECIPE")
public class Recipe implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 60, nullable = false, unique = true)
    private String title;

    @Column(name = "user_id", nullable = false)
    private Long userId;
//
    @Column(name = "image_path", nullable = false)
    private String imagePath;

//    1perc - 999perc
    @Column(name = "time", length = 3, nullable = false)
    private int time;

//    1-12
    @Column(name = "dose", length = 2, nullable = false)
    private int dose;

//  n -> nagyon olcsó
//  o -> olcsó
//  m -> megfizethető
//  d -> drága
    @Column(name = "price", length = 1, nullable = false)
    private String price;

//  e -> könnyű
//  m -> közepes
//  h -> nehéz
    @Column(name = "difficulty", length = 1, nullable = false)
    private String difficulty;

//  from database
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private RecipeCategory categoryId;

//  from database
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuisine_id", referencedColumnName = "id")
    private Cuisine cuisineId;

//  r -> reggeli
//  t -> tízorai
//  e -> ebéd
//  u -> uzsonna
//  v -> vacsora
    @Column(name = "part_of_the_day", length = 1, nullable = false)
    private String partOfTheDay;

//    t -> tavasz
//    n -> nyár
//    o -> ősz
//    t -> tél
    @Column(name = "season", length = 1, nullable = false)
    private String season;
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

    //    @JoinTable(name = "recipe_step_id", joinColumns = @JoinColumn( name = "id"),
//    inverseJoinColumns = @JoinColumn(name = "recipe_step_id")
//    )
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_step_id", referencedColumnName = "id")
    private RecipeStep recipeStepId;


//    @OneToMany(mappedBy = "recipeStep")
//    private Set<RecipeStep> recipeStepId;

}
