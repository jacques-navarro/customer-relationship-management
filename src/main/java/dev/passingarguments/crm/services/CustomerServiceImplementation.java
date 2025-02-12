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
        Customer createdCustomer = null;

        try {
            createdCustomer = customerRepository.save(customer);
        } catch (RuntimeException e) {
            throw new CustomerServiceException(e.getMessage());
        }

        return createdCustomer;
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
    public boolean deleteById(long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerServiceException("customer not found"));

        if (customer != null) {
            customerRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }
}