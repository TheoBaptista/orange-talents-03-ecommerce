package br.com.edu.zup.ecommerce.purchase;

import br.com.edu.zup.ecommerce.email.Email;
import br.com.edu.zup.ecommerce.shopping.Shopping;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PurchaseProcessor {

    private Email email;
    private Set<SuccessPurchaseAction> actionSet;


    public PurchaseProcessor(Email email, Set<SuccessPurchaseAction> actionSet) {
        this.email = email;
        this.actionSet = actionSet;
    }

    public void process(Shopping shopping){

        if(shopping.notSuccessfullyProcessed()){

            email.send(
             shopping.shoppingEmailFrom(),
             shopping.emailAddressClient(),
             String.format("Erro no pagamento da sua compra entre no link e tente novamente: %s",shopping.linkToGatewayPayment()));
            return;
        }

        shopping.finishTheShopping();
        email.send(shopping.shoppingEmailFrom(),shopping.emailAddressClient(), shopping.messageAfterSuccessPurchase());
        actionSet.forEach( event -> event.action(shopping));
    }


}
