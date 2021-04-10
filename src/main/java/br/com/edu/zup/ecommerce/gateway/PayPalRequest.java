package br.com.edu.zup.ecommerce.gateway;

import br.com.edu.zup.ecommerce.purchase.PurchaseTransaction;
import br.com.edu.zup.ecommerce.shopping.Shopping;

import javax.validation.constraints.*;

public class PayPalRequest implements ReturnGatewayPurchase {

    @NotNull
    private final String idTransaction;

    @NotNull @PositiveOrZero @Min(0) @Max(1)
    private final Integer statusTransaction;

    public PayPalRequest(@NotNull String idTransaction, @NotNull @Positive @Min(0) @Max(1) Integer statusTransaction) {
        this.idTransaction = idTransaction;
        this.statusTransaction = statusTransaction;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public Integer getStatusTransaction() {
        return statusTransaction;
    }

    @Override //1
    public PurchaseTransaction toPurchaseTransaction(Shopping shopping) {
        PurchaseTransactionalStatus status = this.statusTransaction == 0 ? PurchaseTransactionalStatus.ERROR
                : PurchaseTransactionalStatus.SUCCESS;
        return new PurchaseTransaction(this.idTransaction,status,shopping);
    }
}
