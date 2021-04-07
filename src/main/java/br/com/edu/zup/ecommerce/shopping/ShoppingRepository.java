package br.com.edu.zup.ecommerce.shopping;

import br.com.edu.zup.ecommerce.shopping.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping,Long> {
}
