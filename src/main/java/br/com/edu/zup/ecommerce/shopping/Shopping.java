
package br.com.edu.zup.ecommerce.shopping;

import br.com.edu.zup.ecommerce.gateway.Gateway;
import br.com.edu.zup.ecommerce.gateway.PurchaseTransactionalStatus;
import br.com.edu.zup.ecommerce.gateway.ReturnGatewayPurchase;
import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.purchase.PurchaseTransaction;
import br.com.edu.zup.ecommerce.user.User;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Shopping {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
    @Positive
    @Column(nullable = false)
    private Integer quantity;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShoppingStatus status;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Gateway gateway;
    @OneToMany(mappedBy = "shopping",cascade = CascadeType.MERGE)
    private Set<PurchaseTransaction> transactions = new HashSet<>();

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = true)
    public Shopping() {
    }

    public Shopping(Product product, @Positive Integer quantity, User user, Gateway gateway) {
        this.id = UUID.randomUUID().toString();
        this.product = product;
        this.quantity = quantity;
        this.user = user;
        this.status = ShoppingStatus.INITIATED;
        this.gateway = gateway;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public User getUser() {
        return user;
    }

    public void finishTheShopping(){
        Assert.notEmpty(successTransactions(),"Uma compra não pode estar finalizada se não teve sucesso no pagamento!");
        this.status = ShoppingStatus.FINISHED;
    }

    public ShoppingStatus getStatus() {
        return status;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public String shoppingEmailFrom() {
        return "vendas@mercadolivre.com.br";
    }

    public String messageAfterSuccessPurchase(){
        return String.format("Agradecemos pela compra. Estamos enviando as informações do produto.Produto - %s Quantidade - %s Descrição - %s",
                this.product.getName(),this.quantity,this.product.getDescription());
    }

    public String linkToGatewayPayment(){
        return this.gateway.uriResponse(this.id.toString());
    }

    public String shoppingEmailMessage() {
        return String.format("Parabéns, seu produto %s está quase sendo vendido. Quantidade: %s", product.getName(), this.quantity);
    }

    public String emailAddressOwnerProduct() {
        return product.emailOwnerName();
    }

    public String emailAddressClient(){
        return this.user.getUsername();
    }

    public User productOwner(){
        return this.product.getUser();
    }

    public PurchaseTransactionalStatus addTransaction(ReturnGatewayPurchase request){
        PurchaseTransaction purchaseTransaction = request.toPurchaseTransaction(this);

        Assert.state(!this.transactions.contains(purchaseTransaction),"There is already a transaction equal to that processed");

        Assert.state(successTransactions().isEmpty(),"This purchase has already been successfully completed");

        this.transactions.add(purchaseTransaction);

        return purchaseTransaction.getStatus();
    }

    public Set<PurchaseTransaction> successTransactions(){

        Set<PurchaseTransaction> transactionSet = this.transactions.stream().filter(PurchaseTransaction::statusIsSuccess).collect(Collectors.toSet());

        Assert.isTrue(transactionSet.size() <=1,"You have more than two successful transactionSet");


        return transactionSet;
    }


    public Boolean notSuccessfullyProcessed(){
        return successTransactions().isEmpty();
    }

}