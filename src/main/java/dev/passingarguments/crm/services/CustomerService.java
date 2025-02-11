package dev.passingarguments.crm.services;

import dev.passingarguments.crm.entities.Customer;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    List<Customer> findAll();

    Customer findById(Long id);

    void delete(Customer customer);

    Customer update(Customer customer);
}