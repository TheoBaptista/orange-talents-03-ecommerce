package br.com.edu.zup.ecommerce.test;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.product.category.Category;
import br.com.edu.zup.ecommerce.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

public class ProductTest {


    private Category category;
    private User user;

    @BeforeEach
    void setUp() {
        category = new Category("teste");
        user = new User("email@email.com", "123456");

    }


    @DisplayName("Verifica o método de abate estoque")
    @ParameterizedTest
    @CsvSource({ "1,1,true", "1,2,false", "4,2,true", "1,5,false" })
    void verificaMetodoQueAbateEstoque(Integer quantity, Integer decreaseQuantity, Boolean expectedResult) {

        Product product = new Product("teste", BigDecimal.TEN, quantity,
                "descricao" , category, user);

        Boolean result = product.decreaseStock(decreaseQuantity);
        Assertions.assertEquals(expectedResult, result);

    }
}
