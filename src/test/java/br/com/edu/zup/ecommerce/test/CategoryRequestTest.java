package br.com.edu.zup.ecommerce.test;

import br.com.edu.zup.ecommerce.product.category.Category;
import br.com.edu.zup.ecommerce.product.category.CategoryRepository;
import br.com.edu.zup.ecommerce.product.category.CategoryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRequestTest {

    @Autowired
    private CategoryRepository repository;

    public CategoryRequestTest() {
    }

    @BeforeEach
    void setUp() {
        repository.save(new Category("BRINQUEDO"));
    }

    @Test
    public void testaMetodoToCategoryComStringNull() {


        CategoryRequest categoryRequest = new CategoryRequest("EDUCATIVO",null);
        categoryRequest.toCategory(repository);
        assertEquals(null,categoryRequest.getParentCategoryName());

    }

    @Test
    public void testaMetodoToCategoryComString() {


        CategoryRequest categoryRequest = new CategoryRequest("EDUCATIVO","BRINQUEDO");
        categoryRequest.toCategory(repository);
        assertEquals("BRINQUEDO",categoryRequest.getParentCategoryName());

    }
}
