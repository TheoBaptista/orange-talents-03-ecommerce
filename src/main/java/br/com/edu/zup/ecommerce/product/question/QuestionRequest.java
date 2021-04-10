package br.com.edu.zup.ecommerce.product.question;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class QuestionRequest {

    private final @NotNull String title;

    @JsonCreator
    public QuestionRequest(@NotNull @JsonProperty("title") String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Question toQuestion(User user, Product product){
        return new Question(this.title,user,product);
    }
}
