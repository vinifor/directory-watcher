/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinifor.directorywatcher.report;

import br.com.vinifor.directorywatcher.model.Cliente;
import br.com.vinifor.directorywatcher.model.Venda;
import br.com.vinifor.directorywatcher.model.Vendedor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author vinif
 */
public class Report {

    private final String fileName;
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Vendedor> vendedores = new ArrayList<>();
    private final List<Venda> vendas = new ArrayList<>();

    public Report(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void addVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void addVenda(Venda venda) {
        vendas.add(venda);
    }

    public int quantidadeCliente() {
        return clientes.size();
    }

    public int quantidadeVendedor() {
        return vendedores.size();
    }

    public String vendaMaisCara() {
        return vendas.stream()
                .sorted(Comparator.comparing(Venda::getTotal).reversed())
                .findFirst()
                .get()
                .getId();
    }

    public String piorVendedor() {
        vendas.stream()
                .collect(Collectors.groupingBy(
                        Venda::getSalesmanName, Collectors.reducing(
                                BigDecimal.ZERO, Venda::getTotal, BigDecimal::add)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .findFirst()
                .get()
                .getKey();

        return vendas.stream()
                .collect(Collectors.groupingBy(
                        Venda::getSalesmanName, Collectors.reducing(
                                BigDecimal.ZERO, Venda::getTotal, BigDecimal::add)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .findFirst()
                .get()
                .getKey();
    }

    @Override
    public String toString() {
        return "Report{" + "clientes=" + clientes + ", vendedores=" + vendedores + ", vendas=" + vendas + '}';
    }

}
