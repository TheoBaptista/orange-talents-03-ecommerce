package br.com.edu.zup.ecommerce.shopping;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.gateway.Gateway;
import br.com.edu.zup.ecommerce.user.User;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
public class Shopping {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @ManyToOne @JoinColumn(nullable = false)
    private  Product product;
    @Positive @Column(nullable = false)
    private  Integer quantity;
    @ManyToOne @JoinColumn(nullable = false)
    private  User user;
    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private ShoppingStatus status;
    @ManyToOne @JoinColumn(nullable = false)
    private  Gateway gateway;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = true)
    public Shopping() {
    }

    public Shopping(Product product, @Positive Integer quantity, User user, Gateway gateway) {
        this.product = product;
        this.quantity = quantity;
        this.user = user;
        this.status = ShoppingStatus.INITIATED;
        this.gateway = gateway;
    }

    public Long getId() {
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

    public ShoppingStatus getStatus() {
        return status;
    }

    public Gateway getGateway() { return gateway;}

    public String shoppingEmailFrom(){
        return "vendas@mercadolivre.com.br";
    }

    public String shoppingEmailMessage(){
       return  String.format("Parabéns, seu produto %s está quase sendo vendido. Quantidade: %s",product.getName(),this.quantity);
    }

    public String shoppingEmailTo(){
        return this.product.getName();
    }

}
