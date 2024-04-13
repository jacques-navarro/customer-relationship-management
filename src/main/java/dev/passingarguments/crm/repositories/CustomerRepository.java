package dev.passingarguments.crm.repositories;

import dev.passingarguments.crm.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}