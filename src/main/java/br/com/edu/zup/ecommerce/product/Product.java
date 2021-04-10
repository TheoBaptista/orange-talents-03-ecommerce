package br.com.edu.zup.ecommerce.product;

import br.com.edu.zup.ecommerce.product.category.Category;
import br.com.edu.zup.ecommerce.product.feature.ProductFeature;
import br.com.edu.zup.ecommerce.product.feature.ProductFeatureDetailResponse;
import br.com.edu.zup.ecommerce.images.Image;
import br.com.edu.zup.ecommerce.product.question.Question;
import br.com.edu.zup.ecommerce.product.question.QuestionResponse;
import br.com.edu.zup.ecommerce.product.review.Review;
import br.com.edu.zup.ecommerce.product.review.ReviewResponse;
import br.com.edu.zup.ecommerce.user.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Product {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @Column(nullable = false) String name;
    private @Column(nullable = false) @PositiveOrZero @DecimalMin("10.00") BigDecimal price;
    private @Column(nullable = false) @Min(0) Integer quantity;
    private @Column(nullable = false) @Length(max = 10000) String description;
    private @Column(nullable = false) @CreationTimestamp LocalDate registerDate;
    @ManyToOne
    private @JoinColumn(nullable = false) Category category;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "product")
    private  List<ProductFeature> productFeatureList;

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
                   Category category, @NotNull User user) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
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

    public String emailOwnerName(){
        return this.user.getUsername();
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void addImageList(List<Image> imageList) {
        this.imageList.addAll(imageList);
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

    //1
    public List<ProductFeatureDetailResponse> mapToProductFeatureDetailResponseList(){
        return this.productFeatureList.stream().map(ProductFeatureDetailResponse::new).collect(Collectors.toList());
    }

    //1
    public List<String> mapToProductResponseImagesLink(){
        return this.imageList.stream().map(Image::getUri).collect(Collectors.toList());
    }

    //1
    public List<QuestionResponse> mapToQuestionResponseList(){
       return this.questionList.stream().map(QuestionResponse::new).collect(Collectors.toList());
    }

    //1
    public List<ReviewResponse> mapToReviewResponseList(){
        return this.reviewList.stream().map(ReviewResponse::new).collect(Collectors.toList());
    }

    public Integer getTotalReview(){
        return this.reviewList.size();
    }

    //1
    public Boolean decreaseStock(Integer quantity) {
        if (quantity <= this.quantity) {
            this.quantity -= quantity;
            return true;
        }
        return false;}
}
