/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinifor.directorywatcher.writer;

import br.com.vinifor.directorywatcher.report.Report;
import br.com.vinifor.directorywatcher.utils.Constants;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinif
 */
public class FileOutWriter {

    public static void writeReport(Report report) {
        try (FileWriter writer = new FileWriter(Constants.OUT_DIRECTOTY_PATH.concat(report.getFileName()), false)) {
            writer.write("Relatório de importação do arquivo \"" + report.getFileName() + "\"");
            writer.write("\r\n");
            writer.write("\r\n");
            writer.write("Quantidade de clientes: " + report.quantidadeCliente());
            writer.write("\r\n");
            writer.write("Quantidade de vendedores: " + report.quantidadeVendedor());
            writer.write("\r\n");
            writer.write("ID da venda mais cara: " + report.vendaMaisCara());
            writer.write("\r\n");
            writer.write("Pior vendedor: " + report.piorVendedor());
        } catch (IOException ex) {
            Logger.getLogger(FileOutWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
