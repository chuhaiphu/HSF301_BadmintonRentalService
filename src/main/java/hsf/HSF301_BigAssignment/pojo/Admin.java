package hsf.HSF301_BigAssignment.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "createBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Court> courts;

}
