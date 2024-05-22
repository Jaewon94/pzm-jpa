package com.jaewon.jpa.service;

import com.jaewon.jpa.entity.Customer;
import com.jaewon.jpa.entity.Review;
import com.jaewon.jpa.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findOneByUsernameAndPassword(Customer customer) {
        Optional<Customer> optional = customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public Customer findOneByUsername(String username) {
        Optional<Customer> optional = customerRepository.findByUsername(username);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public List<Customer> findByAgeGreaterThanEqual(int age) {
        return customerRepository.findByAgeGreaterThanEqual(age);
    }

    public List<Customer> getRatingTable(String rating) {
        return customerRepository.getRatingTable(rating);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public List<Review> getReviewsByCustomerId(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found"));
        return customer.getReviews();
    }
}
