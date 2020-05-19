package service.implementation;

import model.*;
import model.InventoryNote;
import model.InventoryNoteDetail;
import model.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.StatisticalService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<ReceivingNote> findReceivingNotesByTimePeriod(Date startDate, Date endDate, int pageIndex, int pageSize) {
        PaginatedList<ReceivingNote> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote where date >= :startDate and date <= :endDate");
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from ReceivingNote where date >= :startDate and date <= :endDate")
                .setDate("startDate", startDate).setDate("endDate", endDate)
                .uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public PaginatedList<DeliveryNote> findDeliveryNotesByTimePeriod(Date startDate, Date endDate, int pageIndex, int pageSize) {
        PaginatedList<DeliveryNote> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote where date >= :startDate and date <= :endDate");
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from DeliveryNote where date >= :startDate and date <= :endDate")
                .setDate("startDate", startDate).setDate("endDate", endDate)
                .uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public PaginatedList<SalesInvoice> findInvoicesByTimePeriod(Date startDate, Date endDate, int pageIndex, int pageSize) {
        PaginatedList<SalesInvoice> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoice where date >= :startDate and date <= :endDate");
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from SalesInvoice where date >= :startDate and date <= :endDate")
                .setDate("startDate", startDate).setDate("endDate", endDate)
                .uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public PaginatedList<SalesInvoice> findInvoicesByCustomerAndStaffByTimePeriod(int customerId, int staffId, Date startDate, Date endDate, int pageIndex, int pageSize) {
        PaginatedList<SalesInvoice> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoice where " +
                "staff_id=:staffId and customer_id=:customerId and date>=:startDate and date<=:endDate ");
        query.setInteger("customerId", customerId);
        query.setInteger("staffId", staffId);
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from SalesInvoice where " +
                "staff_id=:staffId and customer_id=:customerId and date>=:startDate and date<=:endDate ")
                .setInteger("customerId", customerId).setInteger("staffId", staffId)
                .setDate("startDate", startDate).setDate("endDate", endDate)
                .uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public Float findRevenueByTimePeriod(Date startDate, Date endDate) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoice where date>=:startDate and date<=:endDate");
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        float res = 0;
        for (SalesInvoice salesInvoice: (List<SalesInvoice>)query.list()){
            for (SalesInvoiceDetail salesInvoiceDetail: salesInvoice.getSalesInvoiceDetails()){
                res += salesInvoiceDetail.getTotalValue();
            }
        }
        return res;
    }

    @Override
    public Float findRevenueByCustomerByTimePeriod(int customerId, Date startDate, Date endDate) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoice where date>=:startDate and date<=:endDate " +
                                                                                    "and customer=:customerId");
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        query.setInteger("customerId", customerId);
        float res = 0;
        for (SalesInvoice salesInvoice: (List<SalesInvoice>)query.list()){
            for (SalesInvoiceDetail salesInvoiceDetail: salesInvoice.getSalesInvoiceDetails()){
                res += salesInvoiceDetail.getTotalValue();
            }
        }
        return res;
    }

    @Override
    public Float findRevenueByStaffByTimePeriod(int staffId, Date startDate, Date endDate) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoice where date>=:startDate and date<=:endDate " +
                                                                                    "and staff=:staffId");
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        query.setInteger("staffId", staffId);
        float res = 0;
        for (SalesInvoice salesInvoice: (List<SalesInvoice>)query.list()){
            for (SalesInvoiceDetail salesInvoiceDetail: salesInvoice.getSalesInvoiceDetails()){
                res += salesInvoiceDetail.getTotalValue();
            }
        }
        return res;
    }

    @Override
    public List<InventoryNote> findInventoryStatus(Date startDate, Date endDate) {
        List<InventoryNote> inventory = new ArrayList<>();
        inventory = updateInventoryStatusOfReceivedProductsByTimePeriod(startDate, endDate, inventory);
        inventory = updateInventoryStatusOfDeliveredProductsByTimePeriod(startDate, endDate, inventory);
        for (InventoryNote inventoryNote:inventory){
            InventoryNoteDetail inventoryNoteDetail = inventoryNote.getInventoryDetail();
            int received = inventoryNoteDetail.getReceived();
            int delivered = inventoryNoteDetail.getDelivered();
            inventoryNoteDetail.setBalance(received - delivered);
        }
        return inventory;
    }

    public List<InventoryNote> updateInventoryStatusOfReceivedProductsByTimePeriod
            (Date startDate, Date endDate, List<InventoryNote> inventory){

        //Get all receiving notes within the required period of time
        List<ReceivingNote> receivingNotes = sessionFactory.getCurrentSession().createQuery("from ReceivingNote where date >= :startDate and date <= :endDate")
                .setDate("startDate", startDate).setDate("endDate", endDate).list();
        for (ReceivingNote receivingNote : receivingNotes){
            for (ReceivingNoteDetail receivingNoteDetail : receivingNote.getReceivingNoteDetails()){
                Product tempProduct = receivingNoteDetail.getProduct();
                if (!checkExistingProductInInventory(inventory, tempProduct.getId())){
                    InventoryNoteDetail inventoryNoteDetail = new InventoryNoteDetail(tempProduct.getName(), receivingNoteDetail.getQuantity(), 0, 0);
                    InventoryNote inventoryNote = new InventoryNote(tempProduct.getId(), inventoryNoteDetail);
                    inventory.add(inventoryNote);
                } else {
                    InventoryNote savedInventoryNote = findInventoryNote(inventory, tempProduct.getId());
                    int oldReceivedQuantity = savedInventoryNote.getInventoryDetail().getReceived();
                    savedInventoryNote.getInventoryDetail().setReceived(oldReceivedQuantity + receivingNoteDetail.getQuantity());
                }
            }
        }
        return inventory;
    }

    public List<InventoryNote> updateInventoryStatusOfDeliveredProductsByTimePeriod
            (Date startDate, Date endDate, List<InventoryNote> inventory){

        //Get all receiving notes within the required period of time
        List<DeliveryNote> deliveryNotes = sessionFactory.getCurrentSession().createQuery("from DeliveryNote  where date >= :startDate and date <= :endDate")
                .setDate("startDate", startDate).setDate("endDate", endDate).list();
        for (DeliveryNote deliveryNote : deliveryNotes){
            for (DeliveryNoteDetail deliveryNoteDetail : deliveryNote.getDeliveryNoteDetails()){
                Product tempProduct = deliveryNoteDetail.getProduct();
                if (!checkExistingProductInInventory(inventory, tempProduct.getId())){
                    InventoryNoteDetail inventoryNoteDetail = new InventoryNoteDetail(tempProduct.getName(), 0, deliveryNoteDetail.getQuantity(), 0);
                    InventoryNote inventoryNote = new InventoryNote(tempProduct.getId(), inventoryNoteDetail);
                    inventory.add(inventoryNote);
                } else {
                    InventoryNote savedInventoryNote = findInventoryNote(inventory, tempProduct.getId());
                    int oldReceivedQuantity = savedInventoryNote.getInventoryDetail().getDelivered();
                    savedInventoryNote.getInventoryDetail().setDelivered(oldReceivedQuantity + deliveryNoteDetail.getQuantity());
                }
            }
        }
        return inventory;
    }

    boolean checkExistingProductInInventory(List<InventoryNote> inventoryNote, int productId){
        for (InventoryNote inventoryNoteItem : inventoryNote){
            if (inventoryNoteItem.getProductId() == productId) return true;
        }
        return false;
    }

    InventoryNote findInventoryNote(List<InventoryNote> inventory, int productId){
        for (InventoryNote inventoryNote: inventory)
            if (inventoryNote.getProductId() == productId) return inventoryNote;
            return null;
    }
}
