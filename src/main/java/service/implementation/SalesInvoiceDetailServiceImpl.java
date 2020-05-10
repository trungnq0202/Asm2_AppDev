package service.implementation;

import entity.SalesInvoiceDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.SalesInvoiceDetailService;

import java.util.List;

@Transactional
@Service
public class SalesInvoiceDetailServiceImpl implements SalesInvoiceDetailService {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<SalesInvoiceDetail> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM SalesInvoiceDetail").list();
    }

    @Override
    public SalesInvoiceDetail findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SalesInvoiceDetail where id=:id");
        query.setInteger("id", id);
        return (SalesInvoiceDetail) query.uniqueResult();
    }

    @Override
    public SalesInvoiceDetail save(SalesInvoiceDetail salesInvoiceDetail) {
        sessionFactory.getCurrentSession().save(salesInvoiceDetail);
        return salesInvoiceDetail;
    }

    @Override
    public SalesInvoiceDetail update(SalesInvoiceDetail salesInvoiceDetail) {
        sessionFactory.getCurrentSession().update(salesInvoiceDetail);
        return salesInvoiceDetail;
    }

    @Override
    public int delete(int id) {
        SalesInvoiceDetail salesInvoiceDetail = findById(id);
        sessionFactory.getCurrentSession().delete(salesInvoiceDetail);
        return id;
    }
}
