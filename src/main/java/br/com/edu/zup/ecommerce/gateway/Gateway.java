package br.com.edu.zup.ecommerce.gateway;

import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;

@Entity
public class Gateway {

    @Id
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EnumGateway  type;
    @Column(nullable = false, unique = true)
    private String url;
    @Column(nullable = false)
    private String path;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = false)
    public Gateway() {
    }

    public Gateway(EnumGateway type, String url, String path) {
        this.type = type;
        this.url = url;
        this.path = path;
    }

    public EnumGateway getEnumGateway() {
        return type;
    }

    public String getUrlGateway() {
        return url;
    }
    //1
    public String uriResponse(String id) {
         return UriComponentsBuilder.fromUriString("?buyerId={id}&redirectUrl={urlRetorno}/{idShop}")
                 .host(this.url).scheme("https").buildAndExpand(id,this.path,id).toUriString();
    }
}
