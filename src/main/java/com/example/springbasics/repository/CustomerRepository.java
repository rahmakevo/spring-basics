package com.example.springbasics.repository;

import com.example.springbasics.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service(value = "CustomerRepository")
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
