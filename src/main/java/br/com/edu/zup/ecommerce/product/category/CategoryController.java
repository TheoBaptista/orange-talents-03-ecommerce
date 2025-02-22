package br.com.edu.zup.ecommerce.product.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/categories")
public class CategoryController {
    //1
    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional //1
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRequest newCategory){

        repository.save(newCategory.toCategory(repository));

        return ResponseEntity.ok().build();
    }

}
