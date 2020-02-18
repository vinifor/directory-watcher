/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinifor.directorywatcher.model;

import java.util.Objects;

/**
 *
 * @author vinif
 */
public class Cliente {

    private String CNPJ;
    private String name;
    private String businessArea;

    protected Cliente(String CNPJ, String name, String businessArea) {
        this.CNPJ = CNPJ;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
    
    public static Cliente fromString(String line){
        String[] splitLine = line.split("ç");
        return new Builder()
                .setCNPJ(splitLine[0])
                .setName(splitLine[1])
                .setBusinessArea(splitLine[2])
                .build();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.CNPJ);
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.businessArea);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.CNPJ, other.CNPJ)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.businessArea, other.businessArea)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "CNPJ=" + CNPJ + ", name=" + name + ", businessArea=" + businessArea + '}';
    }

    public static class Builder {

        private String CNPJ;
        private String name;
        private String businessArea;

        public Builder() {
        }

        public Builder setCNPJ(String CNPJ) {
            this.CNPJ = CNPJ;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBusinessArea(String businessArea) {
            this.businessArea = businessArea;
            return this;
        }

        public Cliente build() {
            return new Cliente(CNPJ, name, businessArea);
        }
    }
}
