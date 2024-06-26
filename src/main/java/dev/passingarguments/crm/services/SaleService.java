package dev.passingarguments.crm.services;

import dev.passingarguments.crm.entities.Sale;

import java.util.List;

public interface SaleService {

    List<Sale> findAll();

    void save(Sale sale);

    Sale findById(Long id);

    void update(Sale sale);

    void delete(Sale sale);
}