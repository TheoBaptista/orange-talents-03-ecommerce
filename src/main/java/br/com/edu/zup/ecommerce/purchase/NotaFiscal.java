package br.com.edu.zup.ecommerce.purchase;

import br.com.edu.zup.ecommerce.shopping.Shopping;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class NotaFiscal implements SuccessPurchaseAction {
    @Override
    public void action(Shopping shopping) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("shoppingId",shopping.getId(),"clientId",shopping.getUser().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais",request,String.class);
    }
}
