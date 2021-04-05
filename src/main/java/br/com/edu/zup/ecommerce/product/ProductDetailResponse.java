package br.com.edu.zup.ecommerce.product;

import br.com.edu.zup.ecommerce.images.Image;
import br.com.edu.zup.ecommerce.product.feature.ProductFeatureDetailResponse;
import br.com.edu.zup.ecommerce.question.QuestionResponse;
import br.com.edu.zup.ecommerce.review.ReviewResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDetailResponse {

    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Integer quantity;
    private final Double noteAverage;
    private final Integer totalReview;
    private final List<ProductFeatureDetailResponse> productFeatureList;
    private final List<String> imagesLinkList;
    private final List<QuestionResponse> questionResponseList;
    private final List<ReviewResponse> reviewResponseList;


    public ProductDetailResponse(Product product,Double noteAverage) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.noteAverage = noteAverage;
        this.totalReview = product.getReviewList().size();
        this.productFeatureList = product.getProductFeatureList().stream().map(ProductFeatureDetailResponse::new).collect(Collectors.toList());
        this.imagesLinkList = product.getImageList().stream().map(Image::getUri).collect(Collectors.toList());
        this.questionResponseList = product.getQuestionList().stream().map(QuestionResponse::new).collect(Collectors.toList());
        this.reviewResponseList = product.getReviewList().stream().map(ReviewResponse::new).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getNoteAverage() {
        return noteAverage;
    }

    public Integer getTotalReview() {
        return totalReview;
    }

    public List<ProductFeatureDetailResponse> getProductFeatureList() {
        return productFeatureList;
    }

    public List<String> getImagesLinkList() {
        return imagesLinkList;
    }

    public List<QuestionResponse> getQuestionResponseList() {
        return questionResponseList;
    }

    public List<ReviewResponse> getReviewResponseList() {
        return reviewResponseList;
    }
}
