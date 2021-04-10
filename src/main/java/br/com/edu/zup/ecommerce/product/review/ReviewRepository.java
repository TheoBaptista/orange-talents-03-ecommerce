package br.com.edu.zup.ecommerce.product.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    //1
    @Query("SELECT AVG(r.note) FROM Review r")
    Double getAverageReviewsNote();

}
