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
@Table(name = "cuisines")
public class Cuisine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @OneToOne(mappedBy = "cuisines")
//    @OneToOne
//    private Recipe recipe;

    @Column(name = "cuisine_name", length = 12)
    private String name;
}
