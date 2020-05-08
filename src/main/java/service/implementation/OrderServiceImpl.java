package service.implementation;

import entity.Order;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.OrderService;

import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Order").list();
    }

    @Override
    public Order findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Order where id=:id");
        query.setInteger("id", id);
        return (Order) query.uniqueResult();
    }

    @Override
    public Order save(Order order) {
        sessionFactory.getCurrentSession().save(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        sessionFactory.getCurrentSession().update(order);
        return order;
    }

    @Override
    public int delete(int id) {
        Order order = findById(id);
        sessionFactory.getCurrentSession().delete(order);
        return id;
    }
}
