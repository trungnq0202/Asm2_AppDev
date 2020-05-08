package service;


import entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    public List<OrderDetail> findAll();
    public OrderDetail findById(int id);
    public OrderDetail save(OrderDetail orderDetail);
    public OrderDetail update(OrderDetail orderDetail);
    public int delete(int id);
}
