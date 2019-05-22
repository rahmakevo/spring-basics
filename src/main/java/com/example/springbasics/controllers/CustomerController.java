package com.example.springbasics.controllers;

import com.example.springbasics.config.TokenHandler;
import com.example.springbasics.model.Customer;
import com.example.springbasics.model.Login;
import com.example.springbasics.model.Token;
import com.example.springbasics.repository.CustomerRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private Token tokenModel;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/get")
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public String createCustomer (@RequestBody Customer customers) {
        JSONObject object = new JSONObject();
        Customer mCustomer = new Customer();
        if (!customers.getFirstName().isEmpty() || !customers.getLastName().isEmpty() || !customers.getEmail().isEmpty() || !customers.getPassword().isEmpty()) {
            mCustomer.setFirstName(customers.getFirstName());
            mCustomer.setLastName(customers.getLastName());
            mCustomer.setEmail(customers.getEmail());
            mCustomer.setPassword(customers.getPassword());
            customerRepository.save(mCustomer);
            try {
                object.put("status", "200");
                object.put("message", "User Added Successfully");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            try {
                object.put("status", "200");
                object.put("message", "Kindly confirm that you have entered all details correctly");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return String.valueOf(object);
    }

    @PostMapping("/signin")
    public String loginCustomer(@RequestParam String firstName, String password) {
        JSONObject object = new JSONObject();
        if (password != null || !password.isEmpty() && firstName != null || !firstName.isEmpty()) {
            String token = TokenHandler.createToken(password, firstName, "token", 36000);
            long nowMillis = System.currentTimeMillis();
            long expMillis = nowMillis + 36000;

            // save token to model
            Token mTokenModel = new Token();
            mTokenModel.setToken(token);
            mTokenModel.setExpTime(expMillis);

            customerRepository.findByfirstName(firstName).forEach(name -> {
                if (name.equals(firstName)) {
                    try {
                        object.put("status", "200");
                        object.put("message", token);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        object.put("status", "200");
                        object.put("message", "Login not successful");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        return String.valueOf(object);
    }

}
