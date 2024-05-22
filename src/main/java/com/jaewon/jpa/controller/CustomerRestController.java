package com.jaewon.jpa.controller;

import com.jaewon.jpa.entity.Customer;
import com.jaewon.jpa.repository.CustomerQueryDSLRepository;
import com.jaewon.jpa.repository.CustomerRepository;
import com.jaewon.jpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerRestController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final CustomerQueryDSLRepository queryDSLRepository;

    @GetMapping("/customer/{age}")
    public ResponseEntity<?> getByAge(@PathVariable int age) {
        List<Customer> customers = customerRepository.findByAge(age);
        return new ResponseEntity<>(customers, HttpStatus.OK);

    }
    
    @PostMapping("/customer")
    public ResponseEntity<?> findOneByUsernameAndPassword(@RequestBody Customer customer) {
        Customer cus = customerService.findOneByUsernameAndPassword(customer);

        return new ResponseEntity<>(cus, HttpStatus.OK);

    }

    @GetMapping("/customer/user/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customer/age/{age}")
    public ResponseEntity<?> findOneByAgeGreaterThenEqual(@PathVariable int age) {
        List<Customer> customers = customerRepository.findByAgeGreaterThanEqual(age);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customer/rating/{rating}")
    public ResponseEntity<?> getRating(@PathVariable String rating) {
//        List<Customer> clist = customerRepository.getRating(rating);
        List<Customer> clist = queryDSLRepository.getRating(rating);
        return new ResponseEntity<>(clist, HttpStatus.OK);

    }
    @GetMapping("/customer/rating2/{rating}")
    public ResponseEntity<?> getRatingTable(@PathVariable String rating) {
        List<Customer> clist = customerRepository.getRatingTable(rating);
        return new ResponseEntity<>(clist, HttpStatus.OK);

    }

    @PostMapping("/customer/save")
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        Customer result = queryDSLRepository.getUsernameAndPassword(customer.getUsername(), customer.getPassword());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
