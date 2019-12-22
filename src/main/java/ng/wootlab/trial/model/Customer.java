package ng.wootlab.trial.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(targetEntity = Billing.class, mappedBy = "customer")
    private List<Billing> billings;

    @OneToMany(targetEntity = Cart.class, mappedBy = "customer")
    private List<Cart> carts;

    @OneToMany(targetEntity = Order.class, mappedBy = "customer")
    private List<Order> orders;

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
