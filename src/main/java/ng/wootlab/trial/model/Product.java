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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 20000)
    private String description;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private String imageUrl;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @OneToMany(targetEntity = Image.class, mappedBy = "product")
    private List<Image> images;

    @OneToMany(targetEntity = Review.class, mappedBy = "product")
    private List<Review> reviews;

    @OneToMany(targetEntity = Video.class, mappedBy = "product")
    private List<Video> videos;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    public Product() { }

    public Product(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = 0.0;
    }

    public Product(String name, Double price, String description, Double rating) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
    }
}
