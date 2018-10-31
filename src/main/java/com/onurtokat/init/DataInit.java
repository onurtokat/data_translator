package com.onurtokat.init;

import com.onurtokat.config.MongoConfig;
import com.onurtokat.domain.DataDomain;
import com.onurtokat.repositories.DataRepository;
import com.onurtokat.utility.HeaderConverter;
import com.onurtokat.utility.VendorConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * DataInit provides data loading when application initialization
 * and new files uploading.
 * <p>
 * When new files are uploaded, fileLoader method checks the repository
 * whether it is empty. In case of it is not empty, created collection
 * is dropped from the mongodb and recreated again.
 *
 * @author onurtokat
 */
@Component
public class DataInit implements ApplicationRunner {

    private static DataRepository dataRepository;
    // Static data loading path
    public static final String INITIAL_FILE_PATH = "initialData/data.txt";
    private static DataDomain data = new DataDomain();

    @Autowired
    public DataInit(DataRepository dataRepository) {
        DataInit.dataRepository = dataRepository;
    }

    /**
     * overridden run method always runs when application
     * initialization. It triggers fileLoader method with
     * static data path
     *
     * @param args arguments
     */
    @Override
    public void run(ApplicationArguments args) {
        fileLoader(INITIAL_FILE_PATH);
    }

    /**
     * @param path static data file path
     */
    public static void fileLoader(String path) {

        // dataRepository is cleaned before new loading
        if (dataRepository.count() > 0) {
            try {
                data.getDetails().clear();
                HeaderConverter.getHeaders().clear();
                VendorConverter.getVendors().clear();
                MongoConfig.dropCollection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //reload config files
        HeaderConverter.init();
        VendorConverter.init();

        List<String> headers = new ArrayList<>();
        boolean firstLine = true;//firstLine is used for headers

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.forName("UTF-8"))) {
            String currentLine = null;
            try {
                readLine:
                while ((currentLine = reader.readLine()) != null) {//while there is content on the current line
                    String[] splitted = currentLine.split("\\t");
                    for (int i = 0; i < splitted.length; i++) {
                        if (firstLine) {
                            if (headers.contains(HeaderConverter.getHeaderName(splitted[i]))) {
                                throw new DataException("Data file contains duplicate column name: " +
                                        HeaderConverter.getHeaderName(splitted[i]));
                            } else {
                                headers.add(HeaderConverter.getHeaderName(splitted[i]));//Collect header fields
                            }
                        } else {
                            if (headers == null) {
                                throw new DataException("There is no header in source data file!");
                            }
                            if (i == 0 && !VendorConverter.isMatchingWithVendors(splitted[i])) {
                                continue readLine;

                            } else if (i == 0 && VendorConverter.isMatchingWithVendors(splitted[i])) {
                                data.setId(VendorConverter.getVendorName(splitted[i]));
                            } else {
                                if (!headers.get(i).equals("NA")) {
                                    data.getDetails().add(splitted[i]);
                                }
                            }
                        }
                    }
                    if (firstLine) {
                        firstLine = false;
                        continue readLine;
                    }
                    dataRepository.save(data);
                    data.getDetails().clear();//clear list for new line
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new DataException("One of the data file's fields are not compatible format for data abstraction", e.getCause());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new DataFileNotFoundException("Data file does not exist in the defined path", ex.getCause());
        }
    }
}