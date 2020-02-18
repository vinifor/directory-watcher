/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinifor.directorywatcher.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author vinif
 */
public class Item {

    private Long id;
    private BigDecimal quantity;
    private BigDecimal price;

    protected Item(Long id, BigDecimal quantity, BigDecimal price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getTotal(){
        return quantity.multiply(price);
    }
    
    public static List<Item> listFromString(String line) {
        return Arrays.asList(line.split(","))
                .stream()
                .map(Item::fromString)
                .collect(Collectors.toList());
    }
    
    public static Item fromString(String line) {
        String[] splitLine = line.split("-");
        return new Builder()
                .setId(Long.parseLong(splitLine[0]))
                .setQuantity(new BigDecimal(splitLine[1]))
                .setPrice(new BigDecimal(splitLine[2]))
                .build();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.quantity);
        hash = 53 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", quantity=" + quantity + ", price=" + price + '}';
    }

    public static class Builder {

        private Long id;
        private BigDecimal quantity;
        private BigDecimal price;

        public Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Item build() {
            return new Item(id, quantity, price);
        }
    }
}
