/*
 * Class: Produto
 * Version: 1.2.4
 * Author: Mário Oliveira
 */

package com.exemple.products;

import java.io.Serializable;

public class Product implements Serializable {

    Integer id;
    String name;
    Boolean active;
    String description;
    Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.active = true;
        this.description = "This product doesn't have a description.";
        this.price = price;
    }

    public Product(Integer id, String name, Boolean active, String description, Double price) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) { this.active = active; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
