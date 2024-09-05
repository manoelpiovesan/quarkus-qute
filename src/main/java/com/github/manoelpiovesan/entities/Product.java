package com.github.manoelpiovesan.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

/*
 * Author: Manoel
 */
@Entity
@Table(name = "product")
public class Product extends AbstractEntity {

    @Column(name = "name")
    public String name;

    @Column(name = "price")
    public Double price;

    @Column(name = "description")
    public String description;

    @Transactional
    public static Product create(String name, Double price, String description) {
        Product product = new Product();
        product.name = name;
        product.price = price;
        product.description = description;
        product.persist();

        return product;
    }
}
