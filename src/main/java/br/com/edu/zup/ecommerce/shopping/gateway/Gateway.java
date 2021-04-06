package br.com.edu.zup.ecommerce.shopping.gateway;

import javax.persistence.*;

@Entity
public class Gateway {

    @Id
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EnumGateway  type;
    @Column(nullable = false, unique = true)
    private String url;

    public Gateway() {
    }

    public EnumGateway getEnumGateway() {
        return type;
    }

    public String getUrlGateway() {
        return url;
    }
}
