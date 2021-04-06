package br.com.edu.zup.ecommerce.error;

import java.time.Instant;
import java.util.List;

public class ObjectError {

    private String message;
    private Integer status;
    private Instant instant;
    private List<FieldErrors> errors;

    public ObjectError(String message, Integer status, List<FieldErrors> errors) {
        this.message = message;
        this.status = status;
        this.instant = Instant.now();
        this.errors = errors;
    }

    public ObjectError(String message, Integer status) {
        this.message = message;
        this.status = status;
        this.instant = Instant.now();
    }

    public ObjectError() {
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Instant getInstant() {
        return this.instant;
    }

    public List<FieldErrors> getErrors() {
        return errors;
    }

}
