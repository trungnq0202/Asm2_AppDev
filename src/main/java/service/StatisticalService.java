package service;

import model.DeliveryNote;
import model.ReceivingNote;
import model.SalesInvoice;
import model.InventoryNote;
import model.PaginatedList;

import java.util.Date;
import java.util.List;

public interface StatisticalService {
    public PaginatedList<ReceivingNote> findReceivingNotesByTimePeriod(Date startDate, Date endDate, int pageIndex, int pageSize);
    public PaginatedList<DeliveryNote> findDeliveryNotesByTimePeriod(Date startDate, Date endDate, int pageIndex, int pageSize);
    public PaginatedList<SalesInvoice> findInvoicesByTimePeriod(Date startDate, Date endDate, int pageIndex, int pageSize);
    public PaginatedList<SalesInvoice> findInvoicesByCustomerAndStaffByTimePeriod(int customerId, int staffId, Date startDate, Date endDate, int pageIndex, int pageSize);
    public Float findRevenueByTimePeriod(Date startDate, Date endDate);
    public Float findRevenueByCustomerByTimePeriod(int customerId, Date startDate, Date endDate);
    public Float findRevenueByStaffByTimePeriod(int staffId, Date startDate, Date endDate);
    public List<InventoryNote> findInventoryStatus(Date startDate, Date endDate);
}
