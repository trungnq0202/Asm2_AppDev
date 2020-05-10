package service;


import entity.SalesInvoice;

import java.util.Date;
import java.util.List;

public interface SalesInvoiceService {
    public List<SalesInvoice> findAll();
    public SalesInvoice findById(int id);
    public List<SalesInvoice> findByCustomerAndStaffInATimePeriod(int customerId, int staffId, Date startDate, Date endDate);
    public SalesInvoice save(SalesInvoice salesInvoice);
    public SalesInvoice update(SalesInvoice salesInvoice);
    public int delete(int id);
}
