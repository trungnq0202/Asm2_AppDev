package service;

import entity.DeliveryNote;
import entity.ReceivingNote;
import entity.SalesInvoice;
import helper.model.InventoryNote;
import helper.pagination.PaginatedList;

import java.util.Date;
import java.util.List;

public interface AdditionalService {
    public PaginatedList<ReceivingNote> findReceivingNotesByTimePeriod(Date startDate, Date endDate, int pageIndex, int pageSize);
    public PaginatedList<DeliveryNote> findDeliveryNotesByTimePeriod(Date startDate, Date endDate, int pageIndex, int pageSize);
    public PaginatedList<SalesInvoice> findInvoicesByTimePeriod(Date startDate, Date endDate, int pageIndex, int pageSize);
    public PaginatedList<SalesInvoice> findInvoicesByCustomerAndStaffByTimePeriod(int customerId, int staffId, Date startDate, Date endDate, int pageIndex, int pageSize);
    public Float findRevenueByTimePeriod(Date startDate, Date endDate);
    public Float findRevenueByCustomerByTimePeriod(int customerId, Date startDate, Date endDate);
    public Float findRevenueByStaffByTimePeriod(int staffId, Date startDate, Date endDate);
    public List<InventoryNote> findInventoryStatus(Date startDate, Date endDate);
}
