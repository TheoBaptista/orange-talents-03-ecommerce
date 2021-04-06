package br.com.edu.zup.ecommerce.product.feature.dto;

import br.com.edu.zup.ecommerce.product.feature.model.ProductFeature;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ProductFeatureRequest {

    private final @NotBlank String featureName;
    private final @NotBlank String featureDescription;

    public ProductFeatureRequest(@NotBlank String featureName, @NotBlank String featureDescription) {
        this.featureName = featureName;
        this.featureDescription = featureDescription;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFeatureRequest that = (ProductFeatureRequest) o;
        return featureName.equals(that.featureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(featureName);
    }

    public ProductFeature toProductFeature(){
        return new ProductFeature(featureName,featureDescription);
    }
}
