package br.com.edu.zup.ecommerce.shared;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= UniqueValueValidator.class)
public @interface UniqueValueConstraint {

	Class<?> clazz();
	
	String field();
	

	String message() default "This {field} already exists registered in the system";
	

	Class<?>[] groups() default { };
	

	Class<? extends Payload>[] payload() default { };
	
}
