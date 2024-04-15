package dev.passingarguments.crm.controllers;

import dev.passingarguments.crm.entities.Customer;
import dev.passingarguments.crm.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/display_add_customer_form")
    public String displayAddCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "add_customer_form";
    }

    @PostMapping("/customers/add_customer")
    public String addCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:display_add_customer_form";
    }

    @GetMapping("/customers/display_customers")
    public String displayCustomers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "display_customers";
    }
}