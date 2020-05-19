package service.implementation;

import model.DeliveryNoteDetail;
import model.SalesInvoice;
import model.SalesInvoiceDetail;
import model.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DeliveryNoteService;
import service.SalesInvoiceDetailService;
import service.SalesInvoiceService;

import java.util.ArrayList;
import java.util.Date;

@Transactional
@Service
public class SalesInvoiceServiceImpl  implements SalesInvoiceService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SalesInvoiceDetailService salesInvoiceDetailService;

    @Autowired
    private DeliveryNoteService deliveryNoteService;

    @Override
    public PaginatedList<SalesInvoice> findAll(int pageIndex, int pageSize) {
        PaginatedList<SalesInvoice> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM SalesInvoice");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from SalesInvoice").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public PaginatedList<SalesInvoice> findByDate(Date date, int pageIndex, int pageSize) {
        PaginatedList<SalesInvoice> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoice where date = :date");
        query.setDate("date", date);

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from SalesInvoice where date = :date")
                .setDate("date", date).uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public SalesInvoice findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoice where id=:id");
        query.setInteger("id", id);
        return (SalesInvoice) query.uniqueResult();
    }

    @Override
    public SalesInvoice save(SalesInvoice salesInvoice) {
        //Copy Date from Delivery Note to Sales Invoice
//        salesInvoice.setDate(deliveryNoteService.findById(salesInvoice.getDeliveryNote().getId()).getDate());
        salesInvoice.setSalesInvoiceDetails(new ArrayList<>());

        for (DeliveryNoteDetail deliveryNoteDetail: deliveryNoteService.findById(salesInvoice.getDeliveryNote().getId()).getDeliveryNoteDetails()){
            SalesInvoiceDetail salesInvoiceDetail = new SalesInvoiceDetail(0, salesInvoice,
                    deliveryNoteDetail.getProduct(), deliveryNoteDetail.getQuantity(),
                    deliveryNoteDetail.getProduct().getSelling_price() ,
                    deliveryNoteDetail.getQuantity() * deliveryNoteDetail.getProduct().getSelling_price());
            salesInvoice.getSalesInvoiceDetails().add(salesInvoiceDetail);
            salesInvoiceDetailService.save(salesInvoiceDetail);
        }
        sessionFactory.getCurrentSession().save(salesInvoice);
        return salesInvoice;
    }

    @Override
    public SalesInvoice update(SalesInvoice salesInvoice) {
        SalesInvoice savedSalesInvoice = findById(salesInvoice.getId());
//        int oldDeliveryNoteId = savedSalesInvoice.getDeliveryNote().getId();
        int newDeliveryNoteId = salesInvoice.getDeliveryNote().getId();

//        if (oldDeliveryNoteId != newDeliveryNoteId){
            for (SalesInvoiceDetail salesInvoiceDetail:savedSalesInvoice.getSalesInvoiceDetails())
                salesInvoiceDetailService.delete(salesInvoiceDetail.getId());
            savedSalesInvoice.getSalesInvoiceDetails().clear();

            for (DeliveryNoteDetail deliveryNoteDetail: deliveryNoteService.findById(newDeliveryNoteId).getDeliveryNoteDetails()){
                SalesInvoiceDetail salesInvoiceDetail = new SalesInvoiceDetail(0, salesInvoice,
                        deliveryNoteDetail.getProduct(), deliveryNoteDetail.getQuantity(),
                        deliveryNoteDetail.getProduct().getSelling_price() ,
                        deliveryNoteDetail.getQuantity() * deliveryNoteDetail.getProduct().getSelling_price());
                savedSalesInvoice.getSalesInvoiceDetails().add(salesInvoiceDetail);
                salesInvoiceDetailService.save(salesInvoiceDetail);
            }
//        }

        savedSalesInvoice.setDate(salesInvoice.getDate());
        savedSalesInvoice.setStaff(salesInvoice.getStaff());
        savedSalesInvoice.setCustomer(salesInvoice.getCustomer());
        savedSalesInvoice.setDeliveryNote(salesInvoice.getDeliveryNote());

        sessionFactory.getCurrentSession().update(savedSalesInvoice);
        return savedSalesInvoice;
    }

    @Override
    public int delete(int id) {
        SalesInvoice salesInvoice = findById(id);
        for (SalesInvoiceDetail salesInvoiceDetail: salesInvoice.getSalesInvoiceDetails())
            salesInvoiceDetailService.delete(salesInvoiceDetail.getId());
        sessionFactory.getCurrentSession().delete(salesInvoice);
        return id;
    }
}
