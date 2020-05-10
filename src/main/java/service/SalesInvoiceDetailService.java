package service;

import entity.SalesInvoice;
import entity.SalesInvoiceDetail;

import java.util.List;

public interface SalesInvoiceDetailService {
    public List<SalesInvoiceDetail> findAll();
    public SalesInvoiceDetail findById(int id);
    public SalesInvoiceDetail save(SalesInvoiceDetail salesInvoiceDetail);
    public SalesInvoiceDetail update(SalesInvoiceDetail salesInvoiceDetail);
    public int delete(int id);
}
