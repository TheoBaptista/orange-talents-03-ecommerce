package br.com.edu.zup.ecommerce.gateway;

import br.com.edu.zup.ecommerce.purchase.PurchaseTransaction;
import br.com.edu.zup.ecommerce.shopping.Shopping;

public interface ReturnGatewayPurchase {

    PurchaseTransaction toPurchaseTransaction(Shopping shopping);

}
