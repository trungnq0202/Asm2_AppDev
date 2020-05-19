package service;

import model.SalesInvoiceDetail;
import model.PaginatedList;

public interface SalesInvoiceDetailService {
    public PaginatedList<SalesInvoiceDetail> findAll(int pageIndex, int pageSize);
    public SalesInvoiceDetail findById(int id);
    public SalesInvoiceDetail save(SalesInvoiceDetail salesInvoiceDetail);
    public SalesInvoiceDetail update(SalesInvoiceDetail salesInvoiceDetail);
    public int delete(int id);
}
