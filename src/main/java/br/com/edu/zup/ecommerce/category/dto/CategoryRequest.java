package br.com.edu.zup.ecommerce.category.dto;

import br.com.edu.zup.ecommerce.category.model.Category;
import br.com.edu.zup.ecommerce.category.repository.CategoryRepository;
import br.com.edu.zup.ecommerce.shared.annotation.OnlyCreateIfExist;
import br.com.edu.zup.ecommerce.shared.annotation.UniqueValueConstraint;

import javax.validation.constraints.NotBlank;

public class

CategoryRequest {

    private final @NotBlank @UniqueValueConstraint(clazz = Category.class,field = "name") String name;
    private final @OnlyCreateIfExist(clazz = Category.class, field = "name") String parentCategoryName;


    public CategoryRequest(@NotBlank String name, String parentCategoryName) {
        this.name = name;
        this.parentCategoryName = parentCategoryName;
    }

    public String getName() {
        return name;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    // Tests this method
    public Category toCategory(CategoryRepository repository){
        Category category = new Category(this.name);
        if(this.parentCategoryName != null) category.setParentCategory(repository.findByName(parentCategoryName));
        return category;
    }

}
