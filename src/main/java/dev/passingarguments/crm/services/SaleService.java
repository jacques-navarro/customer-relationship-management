package dev.passingarguments.crm.services;

import dev.passingarguments.crm.entities.Sale;

import java.util.List;

public interface SaleService {

    List<Sale> findAll();
}