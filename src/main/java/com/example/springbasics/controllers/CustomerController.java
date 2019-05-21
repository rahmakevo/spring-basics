package com.example.springbasics.controllers;

import com.example.springbasics.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static int customerId = 0;
    private static List<Customer> customer = new ArrayList<>();

    @GetMapping("/customers/get")
    public ResponseEntity <List<Customer>> getCustomers() {
        Customer mCustomer = new Customer();
        mCustomer.setId(String.valueOf(customerId));
        mCustomer.setFirstName("FirstName "+ customerId);
        mCustomer.setLastName("LastName "+ customerId);
        customer.add(mCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/customers/create")
    public ResponseEntity<Customer> createCustomer (@RequestBody Customer customers) {
        customers.setId(String.valueOf(customerId));
        customer.add(customers);
        return new ResponseEntity(customer, HttpStatus.OK);
    }

}
