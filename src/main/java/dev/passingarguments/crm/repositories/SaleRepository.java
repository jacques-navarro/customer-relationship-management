package dev.passingarguments.crm.repositories;

import dev.passingarguments.crm.entities.Sale;
import org.springframework.data.repository.CrudRepository;

public interface SaleRepository extends CrudRepository<Sale, Long> {
}