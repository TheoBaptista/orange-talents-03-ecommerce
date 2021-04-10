package br.com.edu.zup.ecommerce.purchase;

import br.com.edu.zup.ecommerce.gateway.PagseguroRequest;
import br.com.edu.zup.ecommerce.gateway.PayPalRequest;
import br.com.edu.zup.ecommerce.gateway.PurchaseTransactionalStatus;
import br.com.edu.zup.ecommerce.gateway.ReturnGatewayPurchase;
import br.com.edu.zup.ecommerce.shopping.Shopping;
import br.com.edu.zup.ecommerce.shopping.ShoppingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {

    private final ShoppingRepository repository;
    private final PurchaseProcessor purchaseProcessor;

    public PurchaseController(ShoppingRepository repository, PurchaseProcessor purchaseProcessor) {
        this.repository = repository;
        this.purchaseProcessor = purchaseProcessor;
    }


    @PostMapping("/return-paypal/{id}")
    public ResponseEntity<?> paypalPurchase(@PathVariable("id") Long shoppingId, @RequestBody @Valid PayPalRequest payPalRequest) {
       return init(shoppingId,payPalRequest);
    }

    @PostMapping("/return-pagseguro/{id}")
    public ResponseEntity<?> pagseguroPurchase(@PathVariable("id") Long shoppingId, @RequestBody @Valid PagseguroRequest pagseguroRequest) {
        return init(shoppingId,pagseguroRequest);
    }

    @Transactional
    private ResponseEntity<?> init(Long shoppingId, ReturnGatewayPurchase returnGatewayPurchase){

        Optional<Shopping> optionalShopping = repository.findById(shoppingId);
        if(optionalShopping.isPresent()) {

            Shopping shopping = optionalShopping.get();

            PurchaseTransactionalStatus status = shopping.addTransaction(returnGatewayPurchase);

            repository.save(shopping);

            purchaseProcessor.process(shopping);

            return ResponseEntity.ok().body(status);
        }
        return ResponseEntity.notFound().build();
    }

}
