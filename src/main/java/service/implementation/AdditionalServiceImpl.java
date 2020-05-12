package service.implementation;

import entity.SalesInvoice;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.AdditionalService;

import java.util.Date;
import java.util.HashMap;
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
    public Float findRevenueInATimePeriod(Date startDate, Date endDate) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select sum(sid.totalvalue) " +
                                                                                   "from sales_invoice si, sales_invoice_detail sid " +
                                                                                   "where sid.salesinvoice_id = si.id " +
                                                                                   "and si.date >= :startDate and si.date <= :endDate");
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        return (Float) query.list().get(0);
    }

    @Override
    public Float findRevenueByCustomerInATimePeriod(int customerId, Date startDate, Date endDate) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select sum(sid.totalvalue) " +
                                                                                  "from sales_invoice si, sales_invoice_detail sid " +
                                                                                  "where sid.salesinvoice_id = si.id " +
                                                                                  "and si.date >= :startDate and si.date <= :endDate " +
                                                                                  "and si.customer_id = :customerId");
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        query.setInteger("customerId", customerId);
        return (Float) query.list().get(0);
    }

    @Override
    public Float findRevenueByStaffInATimePeriod(int staffId, Date startDate, Date endDate) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select sum(sid.totalvalue) " +
                                                                                  "from sales_invoice si, sales_invoice_detail sid " +
                                                                                  "where sid.salesinvoice_id = si.id " +
                                                                                  "and si.date >= :startDate and si.date <= :endDate " +
                                                                                  "and si.staff_id = :staffId");
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        query.setInteger("staffId", staffId);
        return (Float) query.list().get(0);
    }

    @Override
    public HashMap<HashMap<Integer, String>, HashMap<String, Integer>> findInventoryStatus(Date startDate, Date endDate) {
        return null;
    }


}
