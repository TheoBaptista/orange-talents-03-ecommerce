package br.com.edu.zup.ecommerce.question.controller;

import br.com.edu.zup.ecommerce.product.model.Product;
import br.com.edu.zup.ecommerce.product.repository.ProductRepository;
import br.com.edu.zup.ecommerce.question.dto.QuestionRequest;
import br.com.edu.zup.ecommerce.question.dto.QuestionsResponse;
import br.com.edu.zup.ecommerce.question.model.Question;
import br.com.edu.zup.ecommerce.question.repository.QuestionRepository;
import br.com.edu.zup.ecommerce.question.util.Email;
import br.com.edu.zup.ecommerce.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    private final QuestionRepository repository;
    private final ProductRepository productRepository;
    private final Email email;

    public QuestionController(QuestionRepository repository, ProductRepository productRepository, Email email) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.email = email;
    }

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<?> createQuestion(@PathVariable("id") Long productId,
                                            @RequestBody @Valid QuestionRequest questionRequest, @AuthenticationPrincipal User user) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = optionalProduct.get();

        Question question = questionRequest.toQuestion(user, product);

        repository.save(question);

        email.send(user.getUsername(),product.getName(), question.getTitle());

        product.addToQuestionList(question);

        return ResponseEntity.ok().body(QuestionsResponse.build(product));
    }

}
