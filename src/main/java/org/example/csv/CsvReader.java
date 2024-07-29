package org.example.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.logging.Logger;
import org.example.model.Invoice;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


public class CsvReader {
    private static Logger logger = Logger.getInstance(CsvReader.class);
    public List<Invoice> readFileByLineToInvoice(String fileName) {
        logger.info("Reading file... " + fileName);
        List<Invoice> invoices = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] values;
            int lineCount = 0;

            // Read header
            if ((values = csvReader.readNext()) != null) {
                logger.info("File's header : " + String.join(",", values));
            }

            // Read lines
            while ((values = csvReader.readNext()) != null) {
                lineCount++;
                if (values.length != 8) {
                    logger.warn("Incorrect number of columns in line " + lineCount + ": " + String.join(",", values));
                    continue;
                }


                Invoice invoice = new Invoice(
                        values[0],
                        values[1],
                        values[2],
                        values[3],
                        values[4],
                        values[5],
                        values[6],
                        values[7]
                );
                invoices.add(invoice);
            }
            logger.info("reading ended :" + fileName);


        } catch (IOException | CsvValidationException e) {
            logger.error("Error reading file: " + e.getMessage());
        }
        return invoices;

    }
}
