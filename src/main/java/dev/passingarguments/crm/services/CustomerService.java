package dev.passingarguments.crm.services;

import dev.passingarguments.crm.entities.Customer;

import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer);

    List<Customer> findAll();
}