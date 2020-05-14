package service.implementation;

import entity.OrderDetail;
import helper.pagination.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.OrderDetailService;


@Transactional
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<OrderDetail> findAll(int pageIndex, int pageSize) {
        PaginatedList<OrderDetail> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM OrderDetail");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from OrderDetail").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public OrderDetail findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from OrderDetail where id=:id");
        query.setInteger("id", id);
        return (OrderDetail) query.uniqueResult();
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        sessionFactory.getCurrentSession().save(orderDetail);
        return orderDetail;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        sessionFactory.getCurrentSession().update(orderDetail);
        return orderDetail;
    }


    @Override
    public int delete(int id) {
        OrderDetail orderDetail = findById(id);
        sessionFactory.getCurrentSession().delete(orderDetail);
        return id;
    }
}
