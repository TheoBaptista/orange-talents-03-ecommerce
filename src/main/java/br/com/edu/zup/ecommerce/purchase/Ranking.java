package br.com.edu.zup.ecommerce.purchase;

import br.com.edu.zup.ecommerce.shopping.Shopping;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class Ranking implements SuccessPurchaseAction {

    @Override
    public void action(Shopping shopping) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("shoppingId",shopping.getId(),"productOwnerId",shopping.productOwner().getId());
        restTemplate.postForEntity("http://localhost:8080/ranking",request,String.class);
    }


}
