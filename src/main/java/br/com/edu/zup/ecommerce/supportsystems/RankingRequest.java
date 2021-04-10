package br.com.edu.zup.ecommerce.supportsystems;

import javax.validation.constraints.NotBlank;

public class RankingRequest {

    @NotBlank
    private String shoppingId;
    @NotBlank
    private String productOwnerId;

    public RankingRequest(String shoppingId, String productOwnerId) {
        this.shoppingId = shoppingId;
        this.productOwnerId = productOwnerId;
    }

    @Override
    public String toString() {
        return "RankingRequest{" +
                "shoppingId=" + shoppingId +
                ", productOwnerId=" + productOwnerId +
                '}';
    }
}
