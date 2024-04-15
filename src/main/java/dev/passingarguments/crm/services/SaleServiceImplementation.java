package dev.passingarguments.crm.services;

import dev.passingarguments.crm.entities.Sale;
import dev.passingarguments.crm.repositories.SaleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImplementation implements SaleService {

    private EntityManager entityManager;
    private SaleRepository saleRepository;

    @Autowired
    public SaleServiceImplementation(EntityManager entityManager, SaleRepository saleRepository) {
        this.entityManager = entityManager;
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> findAll() {
        TypedQuery<Sale> query = entityManager.createQuery("FROM Sale", Sale.class);
        return query.getResultList();
    }
}