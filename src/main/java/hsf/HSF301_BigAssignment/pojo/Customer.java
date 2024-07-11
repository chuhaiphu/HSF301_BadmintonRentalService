package hsf.HSF301_BigAssignment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "status")
    @JsonIgnore
    private Boolean status;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @CreationTimestamp
    private LocalDate createDate;
    @Column(name = "update_date", insertable = false)
    @UpdateTimestamp
    private LocalDate updateDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;
}
