package com.example.authenticationserver.repository;



import com.example.authenticationserver.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByEmail(String email);

}
