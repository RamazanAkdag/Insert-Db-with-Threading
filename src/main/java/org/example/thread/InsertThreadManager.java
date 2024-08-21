package org.example.thread;

import org.example.database.IInvoiceRepository;
import org.example.database.InvoiceDal;
import org.example.logging.Logger;
import org.example.model.Invoice;
import org.example.tracking.ProgressBar;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InsertThreadManager {
    IInvoiceRepository invoiceRepository;
    private static Logger logger = Logger.getInstance(InsertThreadManager.class);

    public InsertThreadManager(){
        invoiceRepository = new InvoiceDal();
    }
    public void saveInvoicesToDatabase(List<Invoice> invoices, ProgressBar progressBar) {
        int numberOfThreads = 30; 
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        for (Invoice invoice : invoices) {
            executor.submit(() -> {
                IInvoiceRepository invoiceDal = new InvoiceDal();
                invoiceDal.insert(invoice);
                progressBar.increment();
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
