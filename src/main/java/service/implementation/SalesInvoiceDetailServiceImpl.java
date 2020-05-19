package service.implementation;

import model.SalesInvoiceDetail;
import model.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.SalesInvoiceDetailService;

@Transactional
@Service
public class SalesInvoiceDetailServiceImpl implements SalesInvoiceDetailService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<SalesInvoiceDetail> findAll(int pageIndex, int pageSize) {
        PaginatedList<SalesInvoiceDetail> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM SalesInvoiceDetail");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from SalesInvoiceDetail").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
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
