package org.example.database;

import org.example.logging.Logger;
import org.example.model.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvoiceDal implements IInvoiceRepository{
    private static Logger logger = Logger.getInstance(InvoiceDal.class);
    @Override
    public void insert(Invoice invoice) {
        String sql = "INSERT INTO invoices (InvoiceNo, StockCode, Description, Quantity, InvoiceDate, UnitPrice, CustomerID, Country) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(
                Connection conn = MysqlDbContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, invoice.getInvoiceNo());
            ps.setString(2, invoice.getStockCode());
            ps.setString(3, invoice.getDescription());
            ps.setString(4, invoice.getQuantity());
            ps.setString(5, invoice.getInvoiceDate());
            ps.setString(6, invoice.getUnitPrice());
            ps.setString(7, invoice.getCustomerID());
            ps.setString(8, invoice.getCountry());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteAll() {
        String sql = "delete from invoices";
        logger.info("delete operation started");
        try(
                Connection conn = MysqlDbContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.executeUpdate();
            logger.info("deleted from invoices table");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
