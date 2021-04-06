package br.com.edu.zup.ecommerce.question.dto;

import br.com.edu.zup.ecommerce.question.model.Question;

public class QuestionResponse {

    private final String question;

    public QuestionResponse(Question question) {
        this.question = question.getTitle();
    }

    public String getTitle() {
        return question;
    }
}
