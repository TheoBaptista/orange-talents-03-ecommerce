package br.com.edu.zup.ecommerce.images.repository;

import br.com.edu.zup.ecommerce.images.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
