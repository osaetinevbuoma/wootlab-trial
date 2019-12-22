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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @Column(nullable = false)
    private String reviewer;

    @Column(name = "reviewer_image_url", nullable = false)
    private String reviewerImageUrl;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false, length = 30000)
    private String review;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    public Review() { }

    public Review(String reviewer, String reviewerImageUrl, String review) {
        this.reviewer = reviewer;
        this.reviewerImageUrl = reviewerImageUrl;
        this.review = review;
        this.rating = 0.0;
    }

    public Review(String reviewer, String reviewerImageUrl, Double rating, String review) {
        this.reviewer = reviewer;
        this.reviewerImageUrl = reviewerImageUrl;
        this.rating = rating;
        this.review = review;
    }
}
