package br.com.edu.zup.ecommerce.error;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    private final MessageSource messageSource;

    public ErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorValidationForm> validationHandler(MethodArgumentNotValidException exception){

        List<ErrorValidationForm> errorForms = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach( e -> {

            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());

            errorForms.add(new ErrorValidationForm(e.getField(), message));
        });
        return errorForms;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorValidationForm dataIntegrityViolation(DataIntegrityViolationException exception){
        return new ErrorValidationForm("Login","Esse login já existe no nosso sistema!");
    }

}
