package br.com.edu.zup.ecommerce.gateway;

import br.com.edu.zup.ecommerce.purchase.PurchaseTransaction;
import br.com.edu.zup.ecommerce.shopping.Shopping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagseguroRequest implements ReturnGatewayPurchase {


    @NotBlank
    private final String idTransaction;
    @NotNull
    private final PagSeguroStatus status;

    public PagseguroRequest(String idTransaction, PagSeguroStatus status) {
        this.idTransaction = idTransaction;
        this.status = status;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public PagSeguroStatus getStatus() {
        return status;
    }

    @Override
    public PurchaseTransaction toPurchaseTransaction(Shopping shopping) {
        return new PurchaseTransaction(idTransaction,status.normalized(),shopping);
    }
}
