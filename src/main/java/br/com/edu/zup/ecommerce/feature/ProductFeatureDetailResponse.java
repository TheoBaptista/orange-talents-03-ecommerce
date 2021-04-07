package br.com.edu.zup.ecommerce.feature;

public class ProductFeatureDetailResponse {

    private final String name;
    private final String description;

    public ProductFeatureDetailResponse(ProductFeature productFeature) {
        this.name = productFeature.getName();
        this.description = productFeature.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
