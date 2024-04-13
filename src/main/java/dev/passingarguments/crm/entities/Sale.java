package dev.passingarguments.crm.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "product_name", length = 50)
    private String productName;
    @Column(name = "quantity_sold")
    private Integer quantitySold;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    public Sale() {
    }

    public Sale(String productName, Integer quantitySold, LocalDate transactionDate) {
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantitySold=" + quantitySold +
                ", transactionDate=" + transactionDate +
                '}';
    }
}