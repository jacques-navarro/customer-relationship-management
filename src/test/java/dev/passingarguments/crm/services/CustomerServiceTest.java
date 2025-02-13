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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
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

    @DisplayName("Add user")
    @Test
    void testAddCustomer_whenCorrectCredentialsProvided_createCustomerAccount() {
        // Arrange
        Mockito.when(customerRepositoryMock
                        .save(any(Customer.class)))
                .thenReturn(customer);

        // Act
        Customer savedCustomer = customerService.addCustomer(customer);

        // Assert
        assertNotNull(savedCustomer, "saved customer should not be null");
        assertEquals(savedCustomer.getCustomerName(), customer.getCustomerName(),
                "saved customer with incorrect name");
        assertEquals(savedCustomer.getEmail(), customer.getEmail(),
                "saved customer with incorrect email");
        assertEquals(savedCustomer.getPhoneNumber(), customer.getPhoneNumber(),
                "saved customer with incorrect phone number");

        assertNotNull(savedCustomer.getAddress(), "saved address should not be null");
        assertEquals(savedCustomer.getAddress().getStreetName(), customer.getAddress().getStreetName(),
                "saved address with incorrect street name");
        assertEquals(savedCustomer.getAddress().getStreetNumber(), customer.getAddress().getStreetNumber(),
                "saved address with incorrect street number");
        assertEquals(savedCustomer.getAddress().getPostalCode(), customer.getAddress().getPostalCode(),
                "saved address with incorrect postal code");

        // Verify
        Mockito.verify(customerRepositoryMock, times(1)
                        .description("addCustomer method should only be called once"))
                .save(any(Customer.class));
    }

    @DisplayName("if addCustomer() method throws exception, a UserServiceException is thrown")
    @Test
    void testAddCustomer_whenAddMethodThrowsException_thenThrowUserServiceException() {
        // Arrange
        Mockito.when(customerRepositoryMock.save(any(Customer.class))).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(CustomerServiceException.class, () -> {
            customerService.addCustomer(customer);
        }, "should have thrown UserServiceException");

        // Verify
        Mockito.verify(customerRepositoryMock, times(1)
                        .description("addCustomer method should have only been called once"))
                .save(any(Customer.class));
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

    @Test
    void testUpdate_whenCustomerProvided_returnUpdatedCustomer() {
        // Arrange
        String name = "updatedName";
        String email = "updatedEmail";
        String phoneNumber = "updatedPhoneNumber";
        String streetName = "updatedStreetName";
        String streetNumber = "updatedStreetNumber";
        int postalCode = 99;

        customer.setCustomerName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.getAddress().setStreetName(streetName);
        customer.getAddress().setStreetNumber(streetNumber);
        customer.getAddress().setPostalCode(postalCode);

        // Act
        customerService.update(customer);

        // Assert
        assertNotNull(customer);
        assertEquals(name, customer.getCustomerName(), "updated name is incorrect");
        assertEquals(email, customer.getEmail(), "updated email is incorrect");
        assertEquals(phoneNumber, customer.getPhoneNumber(), "updated phone number is incorrect");

        assertNotNull(customer.getAddress());
        assertEquals(streetName, customer.getAddress().getStreetName(), "updated street name is incorrect");
        assertEquals(streetNumber, customer.getAddress().getStreetNumber(), "updated street number is incorrect");
        assertEquals(postalCode, customer.getAddress().getPostalCode(), "updated postal code is incorrect");

        // Verify
        Mockito.verify(customerRepositoryMock, times(1)
                        .description("save method should only be called once"))
                .save(any(Customer.class));
    }

    @DisplayName("Delete user by id")
    @Test
    void testDelete_whenEmployeeIsValid_thenDeleteEmployee() {
        // Arrange
        // checks if customer exists, throws CustomerServiceException if not included
        Mockito.when(customerRepositoryMock.findById(anyLong())).thenReturn(Optional.of(customer));
        doNothing().when(customerRepositoryMock).deleteById(anyLong());

        // Act & Assert
        boolean isDeleted = customerService.deleteById(anyLong());

        // Assert
        assertTrue(isDeleted, "should return true when customer is deleted");

        // Verify
        Mockito.verify(customerRepositoryMock, times(1)
                        .description("delete method should only be called once"))
                .deleteById(anyLong());
    }

    @DisplayName("Delete user by id - not found")
    @Test
    void testDelete_whenEmployeeIsNotFound_thenThrowUserServiceException() {
        // Arrange
        Mockito.when(customerRepositoryMock.findById(anyLong())).thenThrow(RuntimeException.class);

        // Act & assert
        assertThrows(CustomerServiceException.class, () -> {
            customerService.deleteById(anyLong());
        }, "should have thrown CustomerServiceException");

        // Verify
        Mockito.verify(customerRepositoryMock, times(1)
                        .description("delete method should only be called once"))
                .deleteById(anyLong());
    }

}