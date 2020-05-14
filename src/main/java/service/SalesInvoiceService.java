package service;


import entity.SalesInvoice;
import helper.pagination.PaginatedList;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface SalesInvoiceService {
    public PaginatedList<SalesInvoice> findAll(int pageIndex, int pageSize);
    public PaginatedList<SalesInvoice> findByDate(Date date, int pageIndex, int pageSize);
    public SalesInvoice findById(int id);
    public SalesInvoice save(SalesInvoice salesInvoice);
    public SalesInvoice update(SalesInvoice salesInvoice);
    public int delete(int id);
}
