package service.implementation;

import entity.Order;
import entity.OrderDetail;
import helper.pagination.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.OrderService;

import java.util.Date;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<Order> findAll(int pageIndex, int pageSize) {
        PaginatedList<Order> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Order");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Order").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public PaginatedList<Order> findByDate(Date date, int pageIndex, int pageSize) {
        PaginatedList<Order> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Order where date = :date");
        query.setDate("date", date);

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Order where date = :date")
                .setDate("date", date).uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public Order findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Order where id=:id");
        query.setInteger("id", id);
        return (Order) query.uniqueResult();
    }

    @Override
    public Order save(Order order) {
        for (OrderDetail orderDetail:order.getOrderDetails()){
            orderDetail.setOrder(order);
        }
        sessionFactory.getCurrentSession().save(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        for (OrderDetail orderDetail:order.getOrderDetails()){
            orderDetail.setOrder(order);
        }
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
