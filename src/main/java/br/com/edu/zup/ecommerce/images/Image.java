package br.com.edu.zup.ecommerce.images;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

@Entity
public class Image {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @Column(nullable = false) @URL String uri;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = false)
    public Image() {
    }

    public Image(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
