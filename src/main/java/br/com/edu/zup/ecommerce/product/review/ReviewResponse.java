package br.com.edu.zup.ecommerce.product.review;

public class ReviewResponse {

    private final String title;
    private final String description;
    private final Integer note;

    public ReviewResponse(Review review) {
        this.title = review.getTitle();
        this.description = review.getDescription();
        this.note = review.getNote();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNote() {
        return note;
    }
}
