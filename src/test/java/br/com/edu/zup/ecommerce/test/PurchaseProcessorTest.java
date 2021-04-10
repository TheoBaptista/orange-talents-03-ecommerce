package br.com.edu.zup.ecommerce.test;

import br.com.edu.zup.ecommerce.email.Email;
import br.com.edu.zup.ecommerce.email.EmailFake;
import br.com.edu.zup.ecommerce.gateway.*;
import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.product.category.Category;
import br.com.edu.zup.ecommerce.product.feature.ProductFeatureRequest;
import br.com.edu.zup.ecommerce.purchase.NotaFiscal;
import br.com.edu.zup.ecommerce.purchase.PurchaseProcessor;
import br.com.edu.zup.ecommerce.purchase.Ranking;
import br.com.edu.zup.ecommerce.purchase.SuccessPurchaseAction;
import br.com.edu.zup.ecommerce.shopping.Shopping;
import br.com.edu.zup.ecommerce.user.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class PurchaseProcessorTest {


    private PurchaseProcessor purchaseProcessor;

    @BeforeEach
    void setUp() {
        Email fake = new EmailFake();
        purchaseProcessor = new PurchaseProcessor(fake, Set.of(new Actions()));
    }

    @Test
    @DisplayName("não deveria processar as acões de sucesso de uma compra")
    void naoDeveriaProcessarAsAcoesDeSucessoDeCompra() {
        Shopping shopping = novaCompra();
        ReturnGatewayPurchase purchaseTransaction = new PagseguroRequest("1", PagSeguroStatus.ERROR);
        shopping.addTransaction(purchaseTransaction);

        purchaseProcessor.process(shopping);


    }

    @Test
    @DisplayName("não deveria processar as acões de sucesso de uma compra")
    void naoDeveriaFinalizarUmaCompraCasoFalhaNoPagamento() {
        Shopping shopping = novaCompra();
        ReturnGatewayPurchase purchaseTransaction = new PagseguroRequest("1", PagSeguroStatus.ERROR);
        shopping.addTransaction(purchaseTransaction);

        Assert.assertThrows(IllegalArgumentException.class, shopping::finishTheShopping);



    }

    @Test
    @DisplayName("deveria processar as acões de sucesso de uma compra")
    void deveriaProcessarAsAcoesDeSucessoDeCompra() {
        Shopping shopping = novaCompra();
        ReturnGatewayPurchase purchaseTransaction = new PagseguroRequest("1", PagSeguroStatus.SUCCESS);
        shopping.addTransaction(purchaseTransaction);

        purchaseProcessor.process(shopping);


    }

    private Shopping novaCompra(){
        Category category = new Category("teste");
        User user = new User("email@email.com", "123456");
        Collection<ProductFeatureRequest> caracteristicas = new ArrayList<>();

        Product produtoASerComprado = new Product("teste", BigDecimal.TEN, 100,
                "descricao" , category, user               );

        User comprador = new User("comprador@email.com",
                "senhaa");

        return new Shopping(produtoASerComprado,50,comprador,new Gateway(EnumGateway.PAGSEGURO,"www.pagseguro.com.br","/return-pagseguro"));
    }



}

