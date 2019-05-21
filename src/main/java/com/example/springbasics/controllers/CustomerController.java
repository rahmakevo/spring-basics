package com.example.springbasics.controllers;

import com.example.springbasics.model.Customer;
import com.example.springbasics.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers/get")
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/customers/create")
    public String createCustomer (@RequestBody Customer customers) {
        Customer mCustomer = new Customer();
        mCustomer.setFirstName(customers.getFirstName());
        mCustomer.setLastName(customers.getLastName());
        mCustomer.setEmail(customers.getEmail());
        mCustomer.setPassword(customers.getPassword());
        customerRepository.save(mCustomer);
        return "saved";
    }

}
