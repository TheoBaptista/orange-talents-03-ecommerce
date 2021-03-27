package br.com.edu.zup.ecommerce.category;

import br.com.edu.zup.ecommerce.shared.OnlyCreateIfExist;
import br.com.edu.zup.ecommerce.shared.UniqueValueConstraint;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {

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
