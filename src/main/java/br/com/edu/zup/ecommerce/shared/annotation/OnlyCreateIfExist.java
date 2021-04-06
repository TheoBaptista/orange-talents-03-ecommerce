package br.com.edu.zup.ecommerce.shared.annotation;

import br.com.edu.zup.ecommerce.shared.validator.OnlyCreateIfExistValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= OnlyCreateIfExistValidator.class)
public @interface OnlyCreateIfExist {

	Class<?> clazz();
	
	String field();
	
	
	String message() default "There is no such this {field} in the system";
	
	
	Class<?>[] groups() default { };
	
	
	Class<? extends Payload>[] payload() default { };


	
	
}
