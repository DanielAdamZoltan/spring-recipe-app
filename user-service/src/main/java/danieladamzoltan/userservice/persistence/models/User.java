package danieladamzoltan.userservice.persistence.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
//    implements Serializable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "enabled")
    private boolean enabled;


    public User(String email, String password, String firstName, String lastName) {
        super();
        this.enabled=false;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        //modified
    }

//    public User() {
//        super();
//        this.enabled=false;
//    }
}
