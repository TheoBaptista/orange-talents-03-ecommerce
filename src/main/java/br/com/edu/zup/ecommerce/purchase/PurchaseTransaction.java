package br.com.edu.zup.ecommerce.purchase;

import br.com.edu.zup.ecommerce.gateway.PurchaseTransactionalStatus;
import br.com.edu.zup.ecommerce.shopping.Shopping;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PurchaseTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String idTransaction;
    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private PurchaseTransactionalStatus status;
    @CreationTimestamp @Column(nullable = false)
    private LocalDateTime registrationDate;

    @ManyToOne @JoinColumn(nullable = false)
    private Shopping shopping;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = true)
    public PurchaseTransaction() {
    }

    public PurchaseTransaction(String idTransaction, PurchaseTransactionalStatus status, Shopping shopping) {
        this.idTransaction = idTransaction;
        this.status = status;
        this.shopping = shopping;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public PurchaseTransactionalStatus getStatus() {
        return status;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public Shopping getShopping() {
        return shopping;
    }

    public Boolean statusIsSuccess(){
        return this.status.equals(PurchaseTransactionalStatus.SUCCESS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseTransaction that = (PurchaseTransaction) o;
        return Objects.equals(idTransaction, that.idTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaction);
    }
}
