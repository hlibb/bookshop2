package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByTitleContaining(String title) {
        return bookRepository.findByTitleContaining(title);}

    public List<Book> findByAuthorContaining(String author) {return bookRepository.findByAuthorContaining(author);}

    public List<Book> findBooksByCategory(String category) {return bookRepository.findBooksByCategory(category);}

    public List<Book> findByDescriptionContaining(String description) {return bookRepository.findBooksByDescriptionContaining(description);}

    public List<Book> findBooksByPriceFrom(float priceFrom) {
        return bookRepository.findBooksByPriceFrom(priceFrom);
    }

    public List<Book> findBooksByPriceFromTo(float priceFrom, float priceTo) {
        return bookRepository.findBooksByPriceFromTo(priceFrom, priceTo);
    }

    public List<Book> findBooksByPriceTo(float priceTo) {
        return bookRepository.findBooksByPriceTo(priceTo);
    }
}
