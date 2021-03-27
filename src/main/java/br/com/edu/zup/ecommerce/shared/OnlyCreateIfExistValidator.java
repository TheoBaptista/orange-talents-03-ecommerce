package br.com.edu.zup.ecommerce.shared;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class OnlyCreateIfExistValidator implements ConstraintValidator<OnlyCreateIfExist, Object> {

	private String field;
	private Class<?> clazz;
	private final EntityManager em;

	public OnlyCreateIfExistValidator(EntityManager em) {
		this.em = em;
	}

	@Override
	public void initialize(OnlyCreateIfExist onlyCreateIfExist) {
		this.clazz = onlyCreateIfExist.clazz();
		this.field = onlyCreateIfExist.field();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null) return true;


		String queryText = String.format("SELECT 1 FROM %s s WHERE upper(trim(s.%s))=upper(trim(:pvalue))",clazz.getName(),field);
		List<?> resultList = em.createQuery(queryText).setParameter("pvalue", value).getResultList();
		return !resultList.isEmpty();
	}

}
