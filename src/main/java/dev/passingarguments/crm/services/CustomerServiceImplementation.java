package dev.passingarguments.crm.services;

import dev.passingarguments.crm.entities.Customer;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImplementation implements CustomerService {

    private EntityManager entityManager;

    @Autowired
    public CustomerServiceImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addCustomer(Customer customer) {
        entityManager.persist(customer);
    }
}