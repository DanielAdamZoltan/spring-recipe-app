package danieladamzoltan.recipeservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;

    @Column(name = "recipe_title", length = 60, nullable = false)
    private String title;

    private Long uploaderId;
    private String imagePath;

    @Column(name = "time", length = 4, nullable = false)
    private int time;

    @Column(name = "portion", length = 2, nullable = false)
    private int portion;

//  0 -> nagyon olcsó
//  1 -> olcsó
//  2 -> megfizethető
//  3 -> drága
    @Column(name = "price", length = 1, nullable = false)
    private int price;

    @Column(name = "difficulty", length = 1, nullable = false)
    private int difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private FoodCategory categoryId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuisine_id", referencedColumnName = "id")
    private Cuisine cuisineId;

    @Column(name = "part_of_the_day", length = 1, nullable = false)
    private int partOfTheDay;

    @Column(name = "season", length = 1, nullable = false)
    private int season;

    @Column(name = "recipe_ingredient_id")
//    @OneToMany(mappedBy = "recipe")
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "recipe_ingredient_id")
    private List<RecipeIngredient> recipeIngredientId = new ArrayList<>();
}
