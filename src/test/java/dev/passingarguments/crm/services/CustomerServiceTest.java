package dev.passingarguments.crm.services;

import dev.passingarguments.crm.entities.Address;
import dev.passingarguments.crm.entities.Customer;
import dev.passingarguments.crm.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepositoryMock;

    @InjectMocks
    private CustomerServiceImplementation customerService;

    private Customer customer;
    public Address address;

    @BeforeEach
    void init() {
        address = new Address("sn1", "sn1", 11);
        customer = new Customer("sn1", "em1", "pn1", address);
    }

    @DisplayName("Find user by Id")
    @Test
    void testFindById_WhenIdProvided_returnCustomer() {

        Mockito.when(customerRepositoryMock.findById(anyLong())).thenReturn(Optional.of(customer));

        Customer storedCustomer = customerService.findById(anyLong());

        // Assert
        assertNotNull(storedCustomer, "findById(1L) should not have returned null");
        assertEquals(storedCustomer, customer, "findById(1L) returned the wrong customer");
        assertEquals(storedCustomer.getCustomerName(), customer.getCustomerName(),
                "findById(1L) retrieved the wrong customer name");
        assertEquals(storedCustomer.getEmail(), customer.getEmail(),
                "findById(1L) retrieved the wrong customer email");
        assertEquals(storedCustomer.getPhoneNumber(), customer.getPhoneNumber(),
                "findById(1L) retrieved the wrong customer phone number");

        assertEquals(storedCustomer.getAddress().getStreetName(), customer.getAddress().getStreetName(),
                "findById(1L) retrieved the wrong street name");
        assertEquals(storedCustomer.getAddress().getStreetNumber(), customer.getAddress().getStreetNumber(),
                "findById(1L) retrieved the wrong street number");
        assertEquals(storedCustomer.getAddress().getPostalCode(), customer.getAddress().getPostalCode(),
                "findById(1L) retrieved the wrong postal code");

        // Verify
        Mockito.verify(customerRepositoryMock, times(1)
                        .description("findById should only be called once"))
                .findById(anyLong());

        // Verify findById() method is only called once
        Mockito.verify(customerRepositoryMock, times(1)
                        .description("findById() should be called once"))
                .findById(anyLong());
    }

}
