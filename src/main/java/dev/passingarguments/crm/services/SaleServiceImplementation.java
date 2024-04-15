package dev.passingarguments.crm.services;

import dev.passingarguments.crm.entities.Sale;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImplementation implements SaleService {

    private EntityManager entityManager;

    @Autowired
    public SaleServiceImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Sale> findAll() {
        return null;
    }
}