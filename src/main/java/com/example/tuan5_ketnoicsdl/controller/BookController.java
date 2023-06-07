package com.example.tuan5_ketnoicsdl.controller;
import com.example.tuan5_ketnoicsdl.daos.Item;
import com.example.tuan5_ketnoicsdl.entity.Book;
import com.example.tuan5_ketnoicsdl.entity.Category;
import com.example.tuan5_ketnoicsdl.repository.IBookRepository;
import com.example.tuan5_ketnoicsdl.repository.ICategoryRepository;
import com.example.tuan5_ketnoicsdl.services.BookService;
import com.example.tuan5_ketnoicsdl.services.CartService;
import com.example.tuan5_ketnoicsdl.services.CategoryService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Autowired
    private IBookRepository iBookRepository;


    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result,Model model ) {
        if(result.hasErrors())
        {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String ediBookForm(@PathVariable(value = "id") Long id,Model model){
        Book booksEdit = bookService.getBookById(id);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("book",booksEdit);
        return "book/update";
    }
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable(value = "id") Long id,@Valid @ModelAttribute("book") Book bookUpdate, BindingResult bindingResult,Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "redirect:/books/update/"+id;

        }
        bookService.updateBook(bookUpdate);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam("keyword") String keyword, Model model) {
        List<Book> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "book/list";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session,
                            @RequestParam long id,
                            @RequestParam String name,
                            @RequestParam double price,
                            @RequestParam(defaultValue = "1") int quantity)
    {
        var cart = cartService.getCart(session);
        cart.addItems(new Item(id, name, price, quantity));
        cartService.updateCart(session, cart);
        return "redirect:/books";
    }



}
