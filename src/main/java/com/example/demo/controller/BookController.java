package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-api")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    //admin api
    @PostMapping("/add")
    public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
        if (book == null) {
            return ResponseEntity.badRequest().build();
        }
    return ResponseEntity.ok(bookService.save(book));
    }

    //user api

    @GetMapping("/get-by-title")
    public List<Book> getByTitleContaining(@RequestParam("t") String title) {
        var requestedTitle = title.replaceAll("[+]", " ");
        return bookService.findByTitleContaining(requestedTitle);
    }

    @GetMapping("/get-by-author")
    public ResponseEntity<List<Book>> getByAuthorContaining(@RequestParam("a") String author) {
        var requestedAuthor = author.replaceAll("[+]", " ");
        return ResponseEntity.ok(bookService.findByAuthorContaining(requestedAuthor));
    }

    @GetMapping("/get-by-category")
    public ResponseEntity<List<Book>> getByCategory(@RequestParam("c") String category) {
        return ResponseEntity.ok(bookService.findBooksByCategory(category));
    }

    @GetMapping("/get-by-description")
    public ResponseEntity<List<Book>> getByDescriptionContaining(@RequestParam("d") String description) {
        return ResponseEntity.ok(bookService.findByDescriptionContaining(description));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/get-by-price-from/{price-from}")
    public ResponseEntity<List<Book>> findBooksByPriceFrom(@PathVariable("price-from") float priceFrom) {
        return ResponseEntity.ok(bookService.findBooksByPriceFrom(priceFrom));
    }

    @GetMapping("/get-by-price-from-to/{price-from}-{price-to}")
    public ResponseEntity<List<Book>> findBooksByPriceFromTo(@PathVariable("price-from") float priceFrom,
                                             @PathVariable("price-to") float priceTo) {
        return ResponseEntity.ok(bookService.findBooksByPriceFromTo(priceFrom, priceTo));
    }

    @GetMapping("/get-by-price-to/{price-to}")
    public ResponseEntity<List<Book>> findBooksByPriceTo(@PathVariable("price-to") float priceTo) {
        return ResponseEntity.ok(bookService.findBooksByPriceTo(priceTo));
    }
}
