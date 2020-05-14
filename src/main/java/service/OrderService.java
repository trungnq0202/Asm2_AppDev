package service;

import entity.Order;
import helper.pagination.PaginatedList;

import java.util.Date;
import java.util.List;

public interface OrderService {
    public PaginatedList<Order> findAll(int pageIndex, int pageSize);
    public PaginatedList<Order> findByDate(Date date, int pageIndex, int pageSize);
    public Order findById(int id);
    public Order save(Order order);
    public Order update(Order order);
    public int delete(int id);
}
