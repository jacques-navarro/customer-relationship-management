package dev.passingarguments.crm.services;

import dev.passingarguments.crm.entities.Customer;
import dev.passingarguments.crm.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();

    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        customerRepository.save(customer);
    }
}