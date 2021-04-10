package br.com.edu.zup.ecommerce.gateway;

public enum PagSeguroStatus {
    SUCCESS, ERROR;

    //1
    public PurchaseTransactionalStatus normalized() {
        if(this.equals(SUCCESS)) {
            return PurchaseTransactionalStatus.SUCCESS;
        }

        return PurchaseTransactionalStatus.ERROR;
    }
}
