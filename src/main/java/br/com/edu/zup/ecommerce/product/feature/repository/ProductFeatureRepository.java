package br.com.edu.zup.ecommerce.product.feature.repository;

import br.com.edu.zup.ecommerce.product.feature.model.ProductFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeatureRepository extends JpaRepository<ProductFeature,Long> {
}
