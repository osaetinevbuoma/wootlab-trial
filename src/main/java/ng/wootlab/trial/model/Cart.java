package ng.wootlab.trial.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @Column(nullable = false)
    private Integer productId;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private String productImageUrl;

    @Column(nullable = false)
    private Double price;

    @Min(1)
    @Column(nullable = false)
    private Integer quantity;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    public Cart() { }

    public Cart(Integer productId, String product, String productImageUrl, Double price) {
        this.productId = productId;
        this.product = product;
        this.productImageUrl = productImageUrl;
        this.price = price;
        this.quantity = 1;
    }

    public Cart(Integer productId, String product, String productImageUrl, Double price,
                Integer quantity) {
        this.productId = productId;
        this.product = product;
        this.productImageUrl = productImageUrl;
        this.price = price;
        this.quantity = quantity;
    }
}
