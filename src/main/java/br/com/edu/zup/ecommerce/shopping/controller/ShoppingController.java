package br.com.edu.zup.ecommerce.shopping.controller;

import br.com.edu.zup.ecommerce.product.repository.ProductRepository;
import br.com.edu.zup.ecommerce.shopping.dto.ShoppingRequest;
import br.com.edu.zup.ecommerce.shopping.gateway.GatewayRepository;
import br.com.edu.zup.ecommerce.user.model.User;
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

    public ShoppingController(ProductRepository productRepository, GatewayRepository gatewayRepository) {
        this.productRepository = productRepository;
        this.gatewayRepository = gatewayRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> buyProduct(@RequestBody @Valid ShoppingRequest shoppingRequest, @AuthenticationPrincipal User customer){

            // buscar o gateway com as info
            // buscar o produto
            // verificar se tem estoque
            // abater o estoque
            // build uma compra
            // build o url builder e devolve
            return ResponseEntity.ok().body(shoppingRequest);
    }

}
