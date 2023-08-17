package com.systems.userdashboardbackend.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private double productPrice;
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "is_stocked")
    private Boolean isStocked;
    @Column(name = "min_quantity")
    private Integer minQuantity;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
