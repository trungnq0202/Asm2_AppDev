package service.implementation;

import entity.SalesInvoice;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.AdditionalService;

import java.util.Date;
import java.util.List;


@Transactional
@Service
public class AdditionalServiceImpl implements AdditionalService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<SalesInvoice> findInvoicesByCustomerAndStaffInATimePeriod(int customerId, int staffId, Date startDate, Date endDate) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoice where " +
                "staff_id=:staffId and customer_id=:customerId and date>=:startDate and date<=:endDate ");
        query.setInteger("customerId", customerId);
        query.setInteger("staffId", staffId);
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        return query.list();
    }


    @Override
    public Double findRevenueInATimePeriod(Date startDate, Date endDate) {
//        Query query = sessionFactory.getCurrentSession().createQuery("select sum(SID.totalValue) " +
//                                                                                "from SalesInvoice SI, SalesInvoiceDetail SID " +
//                                                                                "where SID.salesInvoice=:SI.id " +
//                                                                                  "and SI.date>=:startDate " +
//                                                                                  "and SI.date<=:endDate");
        Query query = sessionFactory.getCurrentSession().createQuery("select sum(totalValue) from SalesInvoiceDetail");
//        query.setDate("startDate", startDate);
//        query.setDate("endDate", endDate);
        return (Double) query.list().get(0);
    }


}
