package br.com.edu.zup.ecommerce.product.feature;

import br.com.edu.zup.ecommerce.product.ProductRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueFeatureNameConstraintValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        // 1
    ProductRequest productRequest = (ProductRequest) target;
    if (productRequest.haveEqualsFeatureName()){
        errors.rejectValue("productFeatureRequestList",null, "Features with the same name are not allowed");
    }
    }
}
