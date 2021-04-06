package br.com.edu.zup.ecommerce.question.dto;

import br.com.edu.zup.ecommerce.product.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionsResponse {

    private final String productName;
    private final List<QuestionResponse> questionList;

    public QuestionsResponse(String productName, List<QuestionResponse> questionList) {
        this.productName = productName;
        this.questionList = questionList;
    }

    public String getProductName() {
        return productName;
    }

    public List<QuestionResponse> getQuestionList() {
        return questionList;
    }

    public static QuestionsResponse build(Product product){
        return new QuestionsResponse(product.getName(),product.getQuestionList().stream().map(QuestionResponse::new).collect(Collectors.toList()));
    }

}
