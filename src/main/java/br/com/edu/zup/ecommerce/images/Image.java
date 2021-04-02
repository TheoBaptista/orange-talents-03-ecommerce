package br.com.edu.zup.ecommerce.images;

import javax.persistence.*;

@Entity
public class Image {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @Column(nullable = false) String uri;

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
