package ng.wootlab.trial.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @NotNull(message = "First name is required.")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "Surname is required.")
    @Column(nullable = false)
    private String surname;

    @NotNull(message = "Email is required.")
    @Email(message = "Provide a correct email address.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Password is required.")
    @Column(nullable = false)
    private String password;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @OneToMany(targetEntity = Shipping.class, mappedBy = "customer")
    private List<Shipping> shippings;

    @OneToMany(targetEntity = Cart.class, mappedBy = "customer")
    private List<Cart> carts;

    @OneToMany(targetEntity = Orders.class, mappedBy = "customer")
    private List<Orders> orders;

    @ManyToOne(targetEntity = Role.class)
    private Role role;

    public Customer() { }

    public Customer(String firstName, String surname, String email, String password) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
