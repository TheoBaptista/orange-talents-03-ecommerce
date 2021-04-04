package br.com.edu.zup.ecommerce.question;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.product.ProductRepository;
import br.com.edu.zup.ecommerce.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    private final QuestionRepository repository;
    private final ProductRepository productRepository;

    public QuestionController(QuestionRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<?> createQuestion(@PathVariable("id") Long productId, @RequestBody @Valid QuestionRequest questionRequest) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Product product = optionalProduct.get();

        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Question question = questionRequest.toQuestion(user, product);
        repository.save(question);

        question.publish(List.of(new SendEmailAfterQuestion(question,user.getUsername())));
        product.addToQuestionList(question);

        return ResponseEntity.ok(QuestionsResponse.build(product));
    }

}
