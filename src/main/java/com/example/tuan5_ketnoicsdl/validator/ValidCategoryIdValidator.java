package com.example.tuan5_ketnoicsdl.validator;
import com.example.tuan5_ketnoicsdl.entity.Category;
import com.example.tuan5_ketnoicsdl.validator.annotation.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context){
        return category != null && category.getId() !=null;
    }
}
