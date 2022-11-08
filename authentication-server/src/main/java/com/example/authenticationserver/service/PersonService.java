package com.example.authenticationserver.service;


import com.example.authenticationserver.domain.Person;
import com.example.authenticationserver.domain.Role;

import java.util.List;

public interface PersonService {
    Person savePerson(Person person);
    Role saveRoel(Role role);
    List<Role> getRoles();
    void addRoleToPerson(String email, String roleName);
    Person getPerson(String email);
    List<Person> getPersons();
}
