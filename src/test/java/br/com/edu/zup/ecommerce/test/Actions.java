package br.com.edu.zup.ecommerce.test;

import br.com.edu.zup.ecommerce.purchase.SuccessPurchaseAction;
import br.com.edu.zup.ecommerce.shopping.Shopping;

public class Actions implements SuccessPurchaseAction {
    @Override
    public void action(Shopping shopping) {
        System.out.println("Ações estão sendo executadas");
    }
}
