package ng.wootlab.trial.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
    private String product;

    @Column(name = "product_image_url", nullable = false)
    private String productNameUrl;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    public Cart() { }

    public Cart(String product, String productNameUrl, Double price) {
        this.product = product;
        this.productNameUrl = productNameUrl;
        this.price = price;
        this.quantity = 1;
    }

    public Cart(String product, String productNameUrl, Double price, Integer quantity) {
        this.product = product;
        this.productNameUrl = productNameUrl;
        this.price = price;
        this.quantity = quantity;
    }
}
