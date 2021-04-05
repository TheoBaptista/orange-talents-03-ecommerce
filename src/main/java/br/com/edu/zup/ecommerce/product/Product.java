package br.com.edu.zup.ecommerce.product;

import br.com.edu.zup.ecommerce.category.Category;
import br.com.edu.zup.ecommerce.images.Image;
import br.com.edu.zup.ecommerce.product.feature.ProductFeature;
import br.com.edu.zup.ecommerce.question.Question;
import br.com.edu.zup.ecommerce.review.Review;
import br.com.edu.zup.ecommerce.user.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @Column(nullable = false) String name;
    private @Column(nullable = false) @Positive @DecimalMin("10.00") BigDecimal price;
    private @Column(nullable = false) @Min(0) Integer quantity;
    private @Column(nullable = false) @Length(max = 10000) String description;
    private @Column(nullable = false) @CreationTimestamp LocalDate registerDate;
    @ManyToOne
    private @JoinColumn(nullable = false) Category category;
    @ManyToMany
    private @JoinColumn(nullable = false) List<ProductFeature> productFeatureList;

    private @NotNull @ManyToOne @JoinColumn(nullable = false)  User user;

    private @OneToMany @JoinColumn @Cascade(org.hibernate.annotations.CascadeType.MERGE) List<Image> imageList = new ArrayList<>();

    private @OneToMany(mappedBy = "product",fetch = FetchType.EAGER) @Cascade(org.hibernate.annotations.CascadeType.MERGE) List<Question> questionList = new ArrayList<>();

    private @OneToMany(mappedBy = "product",cascade = CascadeType.MERGE)  List<Review> reviewList;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = false)
    public Product() {
    }

    public Product(String name, @Positive @DecimalMin("10.00") BigDecimal price,
                   @Min(0) Integer quantity, @Length(max = 10000) String description,
                   Category category, List<ProductFeature> productFeatureList, @NotNull User user) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.productFeatureList = productFeatureList;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public Category getCategory() {
        return category;
    }

    public List<ProductFeature> getProductFeatureList() {
        return productFeatureList;
    }

    public User getUser() {
        return user;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public void addToQuestionList(Question question){
        this.questionList.add(question);
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
