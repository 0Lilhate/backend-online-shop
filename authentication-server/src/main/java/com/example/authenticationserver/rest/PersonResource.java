//package com.example.authenticationserver.rest;
//
//
//import com.example.authenticationserver.domain.Person;
//import com.example.authenticationserver.domain.Role;
//import com.example.authenticationserver.service.PersonService;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//public class PersonResource {
//    private final PersonService personService;
//
//    @GetMapping("api/person")
//    public ResponseEntity<List<Person>> getPersons(){
//        return ResponseEntity.ok().body(personService.getPersons());
//    }
//
//    @PostMapping("api/person")
//    public ResponseEntity<Person> savePersons(@RequestBody Person person){
//        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
//    }
//
//    @PostMapping("api/role")
//    public ResponseEntity<Role> saveRole(@RequestBody Role role){
//        return new ResponseEntity<>(personService.saveRoel(role), HttpStatus.CREATED);
//    }
//
//    @GetMapping("api/role")
//    public ResponseEntity<List<Role>> getRoles(){
//        return ResponseEntity.ok().body(personService.getRoles());
//    }
//
//    @PostMapping("api/role/addtoperson")
//    public ResponseEntity<?> addRoleToPerson(@RequestBody RoleToPersonFrom from){
//        personService.addRoleToPerson(from.getEmailPerson(), from.getRoleName());
//        return ResponseEntity.ok().build();
//    }
//
//
//}
//
//@Data
//class RoleToPersonFrom{
//    private String emailPerson;
//    private String roleName;
//}
