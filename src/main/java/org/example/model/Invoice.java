package org.example.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice {
    private String invoiceNo;
    private String stockCode;
    private String description;
    private String quantity;
    private String invoiceDate;
    private String unitPrice;
    private String customerID;
    private String  country;

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNo='" + invoiceNo + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", unitPrice=" + unitPrice +
                ", customerID=" + customerID +
                ", country='" + country + '\'' +
                '}';
    }

    public Invoice(String invoiceNo, String stockCode, String description, String quantity, String invoiceDate, String unitPrice, String customerID, String country) {
        this.invoiceNo = invoiceNo;
        this.stockCode = stockCode;
        this.description = description;
        this.quantity = quantity;
        this.invoiceDate = convertToDateFormat(invoiceDate);
        this.unitPrice = unitPrice;
        this.customerID = customerID;
        this.country = country;
    }
    private String convertToDateFormat(String dateStr) {
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            Date date = originalFormat.parse(dateStr);
            SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

