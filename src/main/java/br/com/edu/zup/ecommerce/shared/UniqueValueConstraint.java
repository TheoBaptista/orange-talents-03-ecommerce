package br.com.edu.zup.ecommerce.shared;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniqueValueValidator.class)
public @interface UniqueValueConstraint {

	Class<?> clazz();
	
	String field();
	
	//tem que colocar a mensagem parameter caso contrario tomarei exception
	String message() default "Já existe esse {field} cadastrado no nosso sistema"; // colocoquei generica mas tenho que mudar para uma melhor
	
	// tambem tem que ter o groups paramater caso contrario tomarei outra exception
	Class<?>[] groups() default { };
	
	// mesma coisa que as anteriores
	Class<? extends Payload>[] payload() default { };
	
}
