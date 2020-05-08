package service.implementation;

import entity.Order;
import entity.OrderDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.OrderDetailService;

import java.util.List;

@Transactional
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<OrderDetail> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM OrderDetail").list();
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
