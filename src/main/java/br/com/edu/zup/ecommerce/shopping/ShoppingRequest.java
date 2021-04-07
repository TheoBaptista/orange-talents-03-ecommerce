package br.com.edu.zup.ecommerce.shopping;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.shared.OnlyCreateIfExist;
import br.com.edu.zup.ecommerce.gateway.Gateway;
import br.com.edu.zup.ecommerce.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ShoppingRequest {

    @NotNull
    @OnlyCreateIfExist(field = "id", clazz = Product.class)
    private final Long productId;
    @Positive
    @NotNull
    private final Integer quantity;
    @NotBlank
    @OnlyCreateIfExist(field = "type", clazz = Gateway.class)
    private final String enumGateway;

    public ShoppingRequest(@NotBlank Long productId, @Positive @NotNull Integer quantity, @NotNull String enumGateway) {
        this.productId = productId;
        this.quantity = quantity;
        this.enumGateway = enumGateway.toUpperCase();
    }

    public Long getProductId() {

        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getEnumGateway() {
        return enumGateway;
    }

    public Shopping toNewShopping(Gateway gateway, User user, Product product) {
        return new Shopping(product, this.quantity, user, gateway);
    }
}
