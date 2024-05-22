package com.jaewon.jpa.controller;

import com.jaewon.jpa.entity.Customer;
import com.jaewon.jpa.entity.Review;
import com.jaewon.jpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public String customers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customerList";
    }

    @GetMapping("/customer/reviews")
    public String customer(Model model, @RequestParam Long id) {
        List<Review> reviews = customerService.getReviewsByCustomerId(id);
        model.addAttribute("reviews", reviews);
        return "reviewList";
    }
}
