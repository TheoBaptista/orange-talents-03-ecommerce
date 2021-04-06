package br.com.edu.zup.ecommerce.product.feature.util;

import br.com.edu.zup.ecommerce.product.dto.ProductRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueFeatureNameConstraintValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductRequest.class.isAssignableFrom(clazz);
    }

    @Override//make tests here
    public void validate(Object target, Errors errors) {

    ProductRequest productRequest = (ProductRequest) target;
    if (productRequest.haveEqualsFeatureName()){
        errors.rejectValue("productFeatureRequestList",null, "Features with the same name are not allowed");
    }
    }
}
