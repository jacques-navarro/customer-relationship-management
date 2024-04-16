package dev.passingarguments.crm.controllers;

import dev.passingarguments.crm.entities.Customer;
import dev.passingarguments.crm.entities.Sale;
import dev.passingarguments.crm.repositories.SaleRepository;
import dev.passingarguments.crm.services.CustomerService;
import dev.passingarguments.crm.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SaleController {

    private SaleService saleService;
    private SaleRepository saleRepository;
    private CustomerService customerService;

    @Autowired
    public SaleController(SaleService saleService, SaleRepository saleRepository, CustomerService customerService) {
        this.saleService = saleService;
        this.saleRepository = saleRepository;
        this.customerService = customerService;
    }

    @GetMapping("/sales")
    public String findAll(Model model) {
        List<Sale> sales = saleService.findAll();
        model.addAttribute("sales", sales);
        return "display_sales";
    }

    @GetMapping("/sales/{customerId}")
    public String findByCustomerId(@PathVariable("customerId") Long customerId, Model model) {
        List<Sale> salesByCustomerId = saleRepository.findByCustomerId(customerId);
        model.addAttribute("salesByCustomerId", salesByCustomerId);
        model.addAttribute("customerId", customerId);
        return "display_sales_by_customer_id";
    }

    @GetMapping("/sales/add/{customerId}")
    public String displayAddSaleForm(@PathVariable("customerId") Long customerId, Model model) {
        Sale sale = new Sale();
        Customer customer = customerService.findById(customerId);
        sale.setCustomer(customer);
        model.addAttribute("sale", sale);
        return "add_sale_form";
    }

    @PostMapping("/sales")
    public String addSale(@ModelAttribute Sale sale) {
        Long customerId = sale.getCustomer().getId();
        saleService.save(sale);
        return "redirect:/sales/" + customerId;
    }
}