package br.com.edu.zup.ecommerce.shopping;

import br.com.edu.zup.ecommerce.email.Email;
import br.com.edu.zup.ecommerce.gateway.EnumGateway;
import br.com.edu.zup.ecommerce.gateway.Gateway;
import br.com.edu.zup.ecommerce.gateway.GatewayRepository;
import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.product.ProductRepository;
import br.com.edu.zup.ecommerce.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {

    private final ProductRepository productRepository;
    private final GatewayRepository gatewayRepository;
    private final ShoppingRepository shoppingRepository;
    private final Email email;

    public ShoppingController(ProductRepository productRepository,
                              GatewayRepository gatewayRepository,
                              ShoppingRepository shoppingRepository, Email email) {
        this.productRepository = productRepository;
        this.gatewayRepository = gatewayRepository;
        this.shoppingRepository = shoppingRepository;
        this.email = email;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> buyProduct(@RequestBody @Valid ShoppingRequest shoppingRequest, @AuthenticationPrincipal User customer) {

        Product product = productRepository.findById(shoppingRequest.getProductId()).get();

        if (product.decreaseStock(shoppingRequest.getQuantity())){

            Gateway gateway = gatewayRepository.findGatewayByType(EnumGateway.valueOf(shoppingRequest.getEnumGateway()));

            product.decreaseStock(shoppingRequest.getQuantity());

            Shopping newShopping = shoppingRequest.toNewShopping(gateway, customer, product);

            shoppingRepository.save(newShopping);

            email.send(newShopping.shoppingEmailFrom(), newShopping.emailAddressOwnerProduct(), newShopping.shoppingEmailMessage());


            return ResponseEntity.status(HttpStatus.FOUND).body(newShopping.linkToGatewayPayment());
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

}

