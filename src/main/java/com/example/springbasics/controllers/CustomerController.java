package com.example.springbasics.controllers;

import com.example.springbasics.model.Customer;
import com.example.springbasics.repository.CustomerRepository;
import com.example.springbasics.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/get")
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public String createCustomer (@RequestBody Customer customers) {
        Customer mCustomer = new Customer();
        mCustomer.setFirstName(customers.getFirstName());
        mCustomer.setLastName(customers.getLastName());
        mCustomer.setEmail(customers.getEmail());
        mCustomer.setPassword(customers.getPassword());
        customerRepository.save(mCustomer);

        String token = jwtTokenProvider.createToken(customers.getFirstName());
        return token;
    }

}
