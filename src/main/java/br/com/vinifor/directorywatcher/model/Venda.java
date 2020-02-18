/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinifor.directorywatcher.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author vinif
 */
public class Venda {

    private String id;
    private String salesmanName;
    private List<Item> items;

    protected Venda(String id, String salesmanName, List<Item> items) {
        this.id = id;
        this.salesmanName = salesmanName;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public BigDecimal getTotal(){
        return items.stream()
                .map(Item::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Venda fromString(String line) {
        String[] splitLine = line.split("ç");
        return new Builder()
                .setId(splitLine[0])
                .setItems(Item.listFromString(splitLine[1]
                        .replace("[", "")
                        .replace("]", "")))
                .setSalesmanName(splitLine[2])
                .build();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.salesmanName);
        hash = 37 * hash + Objects.hashCode(this.items);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.salesmanName, other.salesmanName)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", salesmanName=" + salesmanName + ", items=" + items + '}';
    }

    public static class Builder {

        private String id;
        private String salesmanName;
        private List<Item> items;

        public Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setSalesmanName(String salesmanName) {
            this.salesmanName = salesmanName;
            return this;
        }

        public Builder setItems(List<Item> items) {
            this.items = items;
            return this;
        }

        public Venda build() {
            return new Venda(id, salesmanName, items);
        }
    }
}
