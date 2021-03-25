package br.com.edu.zup.ecommerce.error;

public class ErrorValidationForm {

    private final String field;
    private final String message;

    public ErrorValidationForm(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
