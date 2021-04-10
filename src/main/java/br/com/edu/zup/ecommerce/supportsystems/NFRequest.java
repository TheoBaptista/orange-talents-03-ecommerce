package br.com.edu.zup.ecommerce.supportsystems;

import javax.validation.constraints.NotBlank;

public class NFRequest {

    @NotBlank
    private String shoppingId;
    @NotBlank
    private String clientId;

    public NFRequest(String shoppingId, String clientId) {
        this.shoppingId = shoppingId;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "NFRequest{" +
                "shoppingId=" + shoppingId +
                ", clientId=" + clientId +
                '}';
    }

}
