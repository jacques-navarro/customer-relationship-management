package dev.passingarguments.crm.controllers;

import dev.passingarguments.crm.entities.Sale;
import dev.passingarguments.crm.repositories.SaleRepository;
import dev.passingarguments.crm.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SaleController {

    private SaleService saleService;
    private SaleRepository saleRepository;

    @Autowired
    public SaleController(SaleService saleService, SaleRepository saleRepository) {
        this.saleService = saleService;
        this.saleRepository = saleRepository;
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
        return "display_sales_by_customer_id";
    }
}