package br.com.edu.zup.ecommerce.category.repository;

import br.com.edu.zup.ecommerce.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    //make tests here
    Category findByName(String name);

}
