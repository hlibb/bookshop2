package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.model.Book;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Transactional
    public Order createOrder(OrderDto orderDTO) {
        Order order = new Order();

        try {
            User userToAdd = userRepository.findById(orderDTO.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            order.setUser(userToAdd); // user
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }

        List<Book> booksToAdd = new ArrayList<>();
        List<Long> bookIds = orderDTO.getBookIds();
        bookIds.forEach(s -> {
            var bookToAdd = bookRepository.findById(s);
            if (bookToAdd.isPresent()) {
                booksToAdd.add(bookToAdd.get());
            } else throw new EntityNotFoundException("Book not found");
        });
        order.setBooks(booksToAdd); // books

        order.setOrderStatus(OrderStatus.ORDERED); // status

        float totalPrice = booksToAdd.stream().map(Book::getPrice).reduce(0f, Float::sum);
        order.setOrderAmount(totalPrice); // price

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        order.setDate(formattedDateTime); // date

        String orderNumberCreated = "#" +
                formattedDateTime.replaceAll("[-: ]", "") +
                order.getUser().getId();
        order.setOrderNumber(orderNumberCreated); // orderNumber
        orderRepository.save(order);

        return orderRepository.getOrderByOrderNumber(order.getOrderNumber());
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
