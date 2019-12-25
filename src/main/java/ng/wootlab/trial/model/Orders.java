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
public class Orders {

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
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    private String transactionReference;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    @ManyToOne(targetEntity = Shipping.class)
    private Shipping shipping;

    public Orders() { }

    public Orders(Integer productId, String product, String productImageUrl, Integer quantity,
                  Double price) {
        this.productId = productId;
        this.product = product;
        this.productImageUrl = productImageUrl;
        this.quantity = quantity;
        this.price = price;
    }

    public Orders(Integer productId, String product, String productImageUrl, Integer quantity,
                  Double price, String transactionReference) {
        this.productId = productId;
        this.product = product;
        this.productImageUrl = productImageUrl;
        this.quantity = quantity;
        this.price = price;
        this.transactionReference = transactionReference;
    }
}
