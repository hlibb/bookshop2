package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllBy();

    //@Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = ?1")
    //User findByEmailFetchRoles(String email);

    User findByName(String name);

    @Query(value = "SELECT * FROM users WHERE name LIKE %:name%", nativeQuery = true)
    List<User> findByNameContaining(@Param("name") String name);

    List<User> findBySurname(String surname);

    List<User> findByAge(short age);

    List<User> findByCity(String city);

    boolean existsByName(String name);

    User findByEmail(String username);
}
