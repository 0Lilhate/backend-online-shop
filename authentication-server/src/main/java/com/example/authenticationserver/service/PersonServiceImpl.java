package com.example.authenticationserver.service;


import com.example.authenticationserver.domain.Person;
import com.example.authenticationserver.domain.Role;
import com.example.authenticationserver.repository.PersonRepository;
import com.example.authenticationserver.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    @Override
    public Person savePerson(Person person) {

        return personRepository.save(person);
    }

    @Override
    public Role saveRoel(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void addRoleToPerson(String email, String roleName) {
        Person person = personRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        person.getRoles().add(role);
    }

    @Override
    public Person getPerson(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }
}
