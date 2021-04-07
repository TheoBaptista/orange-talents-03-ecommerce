package br.com.edu.zup.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p.quantity FROM Product p WHERE p.id = :pid")
    Optional<Integer> getQuantityById(@Param("pid") Long productId);
}
