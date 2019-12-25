package ng.wootlab.trial.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Shipping {

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

    @NotNull(message = "Shipping address is required.")
    @Column(nullable = false, length = 10000)
    private String address;

    @NotNull(message = "City is required.")
    @Column(nullable = false)
    private String city;

    @NotNull(message = "State is required.")
    @Column(nullable = false)
    private String state;

    @NotNull(message = "Email address is required.")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "Phone number is required.")
    @Column(nullable = false)
    private String phone;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Shipping() { }

    public Shipping(String firstName, String surname, String address, String city, String state,
                    String email, String phone) {
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phone = phone;
    }
}
