package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Select:
     * BY FIELDS
     * */

    @Query(value = "SELECT * FROM books WHERE title LIKE %:title%", nativeQuery = true)
    List<Book> findByTitleContaining(String title);

    @Query(value = "SELECT * FROM books WHERE author LIKE %:author%", nativeQuery = true)
    List<Book> findByAuthorContaining(String author);

    // TODO: implement query
    List<Book> findBooksByCategory(String category);

    @Query(value = "SELECT * FROM books WHERE description LIKE %:description%", nativeQuery = true)
    List<Book> findBooksByDescriptionContaining(String description);

    /**
     * In this section will be the search completed with sorting:
     * BY PRICE
     * */

    List<Book> findBooksByPrice(float price);

    @Query(value = "SELECT * FROM books WHERE price > :priceFrom", nativeQuery = true)
    List<Book> findBooksByPriceFrom(float priceFrom);

    @Query(value = "SELECT * FROM books WHERE price > :priceFrom AND price < :priceTo", nativeQuery = true)
    List<Book> findBooksByPriceFromTo(float priceFrom, float priceTo);

    @Query(value = "SELECT * FROM books WHERE price < :priceTo", nativeQuery = true)
    List<Book> findBooksByPriceTo(float priceTo);
}
