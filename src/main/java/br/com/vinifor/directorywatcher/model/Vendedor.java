/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinifor.directorywatcher.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author vinif
 */
public class Vendedor implements Serializable {

    private String CPF;
    private String name;
    private BigDecimal salary;

    protected Vendedor(String CPF, String name, BigDecimal salary) {
        this.CPF = CPF;
        this.name = name;
        this.salary = salary;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
    public static Vendedor fromString(String line){
        String[] splitLine = line.split("ç");
        return new Builder()
                .setCPF(splitLine[0])
                .setName(splitLine[1])
                .setSalary(new BigDecimal(splitLine[2]))
                .build();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.CPF);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.salary);
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
        final Vendedor other = (Vendedor) obj;
        if (!Objects.equals(this.CPF, other.CPF)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.salary, other.salary)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vendedor{"
                + "CPF=" + CPF
                + ", name=" + name
                + ", salary=" + salary
                + '}';
    }

    public static class Builder {

        private String CPF;
        private String name;
        private BigDecimal salary;

        public Builder() {
        }

        public Builder setCPF(String CPF) {
            this.CPF = CPF;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSalary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public Vendedor build() {
            return new Vendedor(CPF, name, salary);
        }
    }
}
