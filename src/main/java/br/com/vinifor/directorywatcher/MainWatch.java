/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinifor.directorywatcher;

/**
 *
 * @author vinif
 */
import br.com.vinifor.directorywatcher.reader.FileInReader;
import br.com.vinifor.directorywatcher.utils.Constants;
import br.com.vinifor.directorywatcher.writer.FileOutWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainWatch {

    public static void watchDirectoryPath(Path path) {
        try {
            WatchService service = path.getFileSystem().newWatchService();
            path.register(service, ENTRY_CREATE);
            while (true) {
                WatchKey key = service.take();

                key.pollEvents().stream()
                        .map(watchEvent -> watchEvent.context().toString())
                        .map(FileInReader::readDocument)
                        .forEach(FileOutWriter::writeReport);
                if (!key.reset()) {
                    break; // loop
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(MainWatch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        File dir = new File(Constants.IN_DIRECTOTY_PATH);
        watchDirectoryPath(dir.toPath());
    }
}
