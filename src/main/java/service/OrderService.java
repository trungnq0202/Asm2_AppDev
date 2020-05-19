package service;

import model.Order;
import model.PaginatedList;

import java.util.Date;

public interface OrderService {
    public PaginatedList<Order> findAll(int pageIndex, int pageSize);
    public PaginatedList<Order> findByDate(Date date, int pageIndex, int pageSize);
    public Order findById(int id);
    public Order save(Order order);
    public Order update(Order order);
    public int delete(int id);
}
