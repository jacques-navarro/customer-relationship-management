package dev.passingarguments.crm.services;

import dev.passingarguments.crm.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    private EntityManager entityManager;

    @Autowired
    public CustomerServiceImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("FROM Customer", Customer.class);
        return query.getResultList();
    }
}