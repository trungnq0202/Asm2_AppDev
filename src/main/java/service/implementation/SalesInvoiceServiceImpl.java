package service.implementation;

import entity.DeliveryNoteDetail;
import entity.SalesInvoice;
import entity.SalesInvoiceDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DeliveryNoteDetailService;
import service.DeliveryNoteService;
import service.SalesInvoiceDetailService;
import service.SalesInvoiceService;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class SalesInvoiceServiceImpl  implements SalesInvoiceService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DeliveryNoteDetailService deliveryNoteDetailService;

    @Autowired
    private SalesInvoiceDetailService salesInvoiceDetailService;

    @Autowired
    private DeliveryNoteService deliveryNoteService;

    @Override
    public List<SalesInvoice> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM SalesInvoice").list();
    }

    @Override
    public SalesInvoice findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoice where id=:id");
        query.setInteger("id", id);
        return (SalesInvoice) query.uniqueResult();
    }

    @Override
    public List<SalesInvoice> findByCustomerAndStaffInATimePeriod(int customerId, int staffId, Date startDate, Date endDate) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoice where " +
                "staff_id=:staffId and customer_id=:customerId and date>=:startDate and date<=:endDate ");
        query.setInteger("customerId", customerId);
        query.setInteger("staffId", staffId);
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        return query.list();
    }


    @Override
    public SalesInvoice save(SalesInvoice salesInvoice) {
        //Copy Date from Delivery Note to Sales Invoice
        salesInvoice.setDate(deliveryNoteService.findById(salesInvoice.getDeliveryNote().getId()).getDate());
        sessionFactory.getCurrentSession().save(salesInvoice);

        //Get List of Delivery Details through Delivery Note id
        List<DeliveryNoteDetail> deliveryNoteDetailList = deliveryNoteDetailService.findAllByDeliveryNoteId(salesInvoice.getDeliveryNote().getId());

        //Add list of Order detail to Receiving Note Detail
        for (DeliveryNoteDetail deliveryNoteDetail:deliveryNoteDetailList){
            SalesInvoiceDetail salesInvoiceDetail = new SalesInvoiceDetail(0, salesInvoice,
                    deliveryNoteDetail.getProduct(), deliveryNoteDetail.getQuantity(),
                    deliveryNoteDetail.getProduct().getSelling_price() ,
                    deliveryNoteDetail.getQuantity() * deliveryNoteDetail.getProduct().getSelling_price());
            salesInvoiceDetailService.save(salesInvoiceDetail);
        }

        return salesInvoice;
    }

    @Override
    public SalesInvoice update(SalesInvoice salesInvoice) {
        sessionFactory.getCurrentSession().update(salesInvoice);
        return salesInvoice;
    }

    @Override
    public int delete(int id) {
        SalesInvoice salesInvoice = findById(id);
        sessionFactory.getCurrentSession().delete(salesInvoice);
        return id;
    }
}
