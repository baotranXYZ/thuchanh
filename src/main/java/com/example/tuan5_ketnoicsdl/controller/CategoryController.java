package com.example.tuan5_ketnoicsdl.controller;

import com.example.tuan5_ketnoicsdl.entity.Book;
import com.example.tuan5_ketnoicsdl.entity.Category;
import com.example.tuan5_ketnoicsdl.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllCats(Model model){
        List<Category> cats = categoryService.getAllCategories();
        model.addAttribute("cats", cats);
        return "category/list";
    }
    @GetMapping("/add")
    public String addCategoryForm(Model model){
        model.addAttribute("cate", new Category());
        return "category/add";
    }
    @PostMapping("/add")
    public String addCat(@Valid @ModelAttribute("cate") Category category, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "category/add";
        }
        categoryService.addCat(category);
        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public String ediBookForm(@PathVariable(value = "id") Long id, Model model){
        Category categoryEdit = categoryService.getCategoryById(id);
        model.addAttribute("cate",categoryEdit);
        return "category/update";
    }
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable(value = "id") Long id,@Valid @ModelAttribute("cate") Category cateUpdate, BindingResult bindingResult,Model model) {
        if(bindingResult.hasErrors()){
            return "redirect:/categories/update/"+id;
        }
        categoryService.saveCategory(cateUpdate);
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}