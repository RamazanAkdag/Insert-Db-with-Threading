package org.example.database;

import org.example.model.Invoice;

public interface IInvoiceRepository {
    public void insert(Invoice invoice);

    public void deleteAll();
}
