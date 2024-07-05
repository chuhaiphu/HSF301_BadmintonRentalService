package hsf.HSF301_BigAssignment.pojo;

import jakarta.persistence.*;

import java.util.List;
@Entity
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
