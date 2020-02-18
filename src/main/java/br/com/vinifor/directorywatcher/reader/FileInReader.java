/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinifor.directorywatcher.reader;

import br.com.vinifor.directorywatcher.model.Cliente;
import br.com.vinifor.directorywatcher.model.Venda;
import br.com.vinifor.directorywatcher.model.Vendedor;
import br.com.vinifor.directorywatcher.report.Report;
import br.com.vinifor.directorywatcher.utils.Constants;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author vinif
 */
public class FileInReader {

    public static Report readDocument(String fileName) {
        Report report = new Report(fileName);
        try (Stream<String> stream = Files.lines(Paths.get(Constants.IN_DIRECTOTY_PATH.concat(fileName)))) {
            stream.forEach(line -> readline(line, report));
        } catch (IOException ex) {
            Logger.getLogger(FileInReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;
    }

    private static void readline(String line, Report report) {
        switch (line.substring(0, 3)) {
            case "001":
                report.addVendedor(Vendedor.fromString(line.substring(4)));
                break;
            case "002":
                report.addCliente(Cliente.fromString(line.substring(4)));
                break;
            case "003":
                report.addVenda(Venda.fromString(line.substring(4)));
                break;
            default:
                break;
        }
    }
}
