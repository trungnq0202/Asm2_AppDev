package service;

import entity.Order;

import java.util.List;

public interface OrderService {
    public List<Order> findAll();
    public Order findById(int id);
    public Order save(Order order);
    public Order update(Order order);
    public int delete(int id);
}
