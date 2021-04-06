package br.com.edu.zup.ecommerce.product.dto;

import br.com.edu.zup.ecommerce.product.model.Product;
import br.com.edu.zup.ecommerce.product.feature.dto.ProductFeatureDetailResponse;
import br.com.edu.zup.ecommerce.question.dto.QuestionResponse;
import br.com.edu.zup.ecommerce.review.dto.ReviewResponse;

import java.math.BigDecimal;
import java.util.List;

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


    public ProductDetailResponse(Product product, Double noteAverage) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.noteAverage = getAverageReview(noteAverage);
        this.totalReview = product.getTotalReview();
        this.productFeatureList = product.mapToProductFeatureDetailResponseList();
        this.imagesLinkList = product.mapToProductResponseImagesLink();
        this.questionResponseList = product.mapToQuestionResponseList();
        this.reviewResponseList = product.mapToReviewResponseList();
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

    private Double getAverageReview(Double averageReview){
        if (averageReview == null){
            return 0.0;
        }
        return averageReview;
    }

}
