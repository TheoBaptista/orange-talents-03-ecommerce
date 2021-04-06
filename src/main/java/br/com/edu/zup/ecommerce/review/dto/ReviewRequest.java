package br.com.edu.zup.ecommerce.review.dto;

import br.com.edu.zup.ecommerce.product.model.Product;
import br.com.edu.zup.ecommerce.review.model.Review;
import br.com.edu.zup.ecommerce.user.model.User;

import javax.validation.constraints.*;

public class ReviewRequest {

    private final @NotBlank String title;
    private final @NotBlank @Size(max = 500) String description;
    private final @NotNull @Positive @Min(1) @Max(5) Integer note;

    public ReviewRequest(@NotBlank String title, @NotBlank @Size(max = 500) String description, @NotNull @Min(1) @Max(5) Integer note) {
        this.title = title;
        this.description = description;
        this.note = note;
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

    public Review toReview(Product product, User user){
       return new Review(this.title,this.description,this.note,product,user);
    }
}
