package service;

import entity.SalesInvoice;

import java.util.Date;
import java.util.List;

public interface AdditionalService {
    public List<SalesInvoice> findInvoicesByCustomerAndStaffInATimePeriod(int customerId, int staffId, Date startDate, Date endDate);
    public Double findRevenueInATimePeriod(Date startDate, Date endDate);
}
