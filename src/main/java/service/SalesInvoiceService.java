package service;


import model.SalesInvoice;
import model.PaginatedList;

import java.util.Date;

public interface SalesInvoiceService {
    public PaginatedList<SalesInvoice> findAll(int pageIndex, int pageSize);
    public PaginatedList<SalesInvoice> findByDate(Date date, int pageIndex, int pageSize);
    public SalesInvoice findById(int id);
    public SalesInvoice save(SalesInvoice salesInvoice);
    public SalesInvoice update(SalesInvoice salesInvoice);
    public int delete(int id);
}
