package dev.passingarguments.crm.repositories;

import dev.passingarguments.crm.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}