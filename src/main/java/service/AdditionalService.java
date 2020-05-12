package service;

import entity.SalesInvoice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface AdditionalService {
    public List<SalesInvoice> findInvoicesByCustomerAndStaffInATimePeriod(int customerId, int staffId, Date startDate, Date endDate);
    public Float findRevenueInATimePeriod(Date startDate, Date endDate);
    public Float findRevenueByCustomerInATimePeriod(int customerId, Date startDate, Date endDate);
    public Float findRevenueByStaffInATimePeriod(int staffId, Date startDate, Date endDate);
    public HashMap<HashMap<Integer, String>, HashMap<String, Integer>> findInventoryStatus(Date startDate, Date endDate);
}
