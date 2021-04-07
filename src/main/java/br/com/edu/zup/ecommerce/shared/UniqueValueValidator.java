package br.com.edu.zup.ecommerce.shared;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class UniqueValueValidator implements ConstraintValidator<UniqueValueConstraint, Object > {

	private String field;
	private Class<?> clazz;
	private final EntityManager em;
		
	public UniqueValueValidator(EntityManager em) {
		this.em = em;
	}

	@Override
	public void initialize(UniqueValueConstraint constraintAnnotation) {
		this.clazz = constraintAnnotation.clazz();
		this.field = constraintAnnotation.field();
	}
	
	@Override//make tests here
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null) return true;
		String queryText = String.format("SELECT 1 FROM %s s WHERE upper(trim(s.%s))=upper(trim(:pvalue))",clazz.getName(),field);
		return em.createQuery(queryText).setParameter("pvalue", value).getResultList().isEmpty();

	}

}
