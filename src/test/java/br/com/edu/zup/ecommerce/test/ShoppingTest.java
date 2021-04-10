package br.com.edu.zup.ecommerce.test;

import br.com.edu.zup.ecommerce.gateway.Gateway;
import br.com.edu.zup.ecommerce.gateway.PayPalRequest;
import br.com.edu.zup.ecommerce.gateway.ReturnGatewayPurchase;
import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.product.category.Category;
import br.com.edu.zup.ecommerce.shopping.Shopping;
import br.com.edu.zup.ecommerce.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ShoppingTest {

    /*
     * 1) aceitar uma transacao com sucesso
     * 2) não pode aceitar mais de uma transacao com sucesso	 *
     * 3) não pode aceitar transacoes com erro quando ja foi concluido com sucesso
     * 4) pode aceitar uma transacao com erro
     * 5) pode aceitar mais de uma transacao com erro
     * 6) pode aceitar uma transacao com erro e uma transacao com sucesso
     * 7) pode aceitar mais de uma transacao com erro e uma transacao com sucesso
     */

    @Test
    @DisplayName("Aceitar uma transação com sucesso")
    void deveriaAdicionarUmaTransacaoComSucesso(){
        Shopping shopping = novaCompra();
        ReturnGatewayPurchase purchaseTransaction = new PayPalRequest("1",1);

        shopping.addTransaction(purchaseTransaction);

        Assertions.assertEquals(1,shopping.successTransactions().size());


    }

    @Test
    @DisplayName("Não deve aceitar a mesma transação")
    void naoDeveAceitarAMesmaTransacao(){
        Shopping shopping = novaCompra();
        ReturnGatewayPurchase purchaseTransaction = new PayPalRequest("1",1);

        shopping.addTransaction(purchaseTransaction);

        Assertions.assertThrows(IllegalStateException.class, () ->
                shopping.addTransaction(purchaseTransaction));


    }

    @Test
    @DisplayName("Não pode aceitar mais de uma transação com sucesso")
    void naoDeveriaAceitarMaisDeUmaTransacaoComSucesso(){
        Shopping shopping = novaCompra();
        ReturnGatewayPurchase purchaseTransaction = new PayPalRequest("1",1);
        ReturnGatewayPurchase purchaseTransaction2 = new PayPalRequest("1",1);
        shopping.addTransaction(purchaseTransaction);

        Assertions.assertThrows(IllegalStateException.class, () ->
               shopping.addTransaction(purchaseTransaction2));
        Assertions.assertEquals(1,shopping.successTransactions().size());
    }

    @Test
    @DisplayName("não pode aceitar transacao com erro quando ja foi concluido com sucesso")
    void naoPodeAceitarTransacaoComErroQuandoJaFoiConcluidaComSucesso(){

        Shopping shopping = novaCompra();
        ReturnGatewayPurchase purchaseTransaction = new PayPalRequest("1",1);
        ReturnGatewayPurchase purchaseTransaction2 = new PayPalRequest("1",0);
        shopping.addTransaction(purchaseTransaction);

        Assertions.assertThrows(IllegalStateException.class, () ->
                shopping.addTransaction(purchaseTransaction2));

    }

    private Shopping novaCompra(){
        Category category = new Category("teste");
        User user = new User("email@email.com", "123456");


        Product produtoASerComprado = new Product("teste", BigDecimal.TEN, 100,
                "descricao" , category, user               );

        User comprador = new User("comprador@email.com",
                "senhaa");

        return new Shopping(produtoASerComprado,50,comprador,new Gateway());
    }
}
