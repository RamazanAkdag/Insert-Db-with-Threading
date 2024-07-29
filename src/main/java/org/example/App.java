package org.example;

import org.example.csv.CsvReader;
import org.example.database.IInvoiceRepository;
import org.example.database.InvoiceDal;
import org.example.logging.Logger;
import org.example.model.Invoice;
import org.example.thread.InsertThreadManager;
import org.example.tracking.ProgressBar;


import java.io.FileNotFoundException;
import java.util.List;



public class App 
{

    public static void main(String[] args) throws FileNotFoundException {
        CsvReader csvReader = new CsvReader();
        List<Invoice> invoices = csvReader.readFileByLineToInvoice("online-retail-dataset.csv");

        IInvoiceRepository invoiceDal = new InvoiceDal();
        invoiceDal.deleteAll();

        ProgressBar progressBar = new ProgressBar(invoices.size(), App.class);

        Logger.getInstance(App.class).info("Normally insert started...");
        long startTime = System.currentTimeMillis();
        for (Invoice invoice : invoices) {
            invoiceDal.insert(invoice);
            progressBar.increment();
        }
        long endTime = System.currentTimeMillis();
        Logger.getInstance(App.class).info("Normally inserted in : " + (endTime - startTime) / 1000.0 + " seconds");

        invoiceDal.deleteAll();

        // Thread kullanarak ekleme işlemi
        InsertThreadManager insertThreadManager = new InsertThreadManager();
        progressBar = new ProgressBar(invoices.size(), App.class);
        Logger.getInstance(App.class).info("threaded insertin started...");
        startTime = System.currentTimeMillis();
        insertThreadManager.saveInvoicesToDatabase(invoices, progressBar);
        endTime = System.currentTimeMillis();
        Logger.getInstance(App.class).info("inserted with threading in: " + (endTime - startTime) / 1000.0 + " seconds");
    }

}
