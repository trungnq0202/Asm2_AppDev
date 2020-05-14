package service;


import entity.OrderDetail;
import helper.pagination.PaginatedList;

import java.util.List;

public interface OrderDetailService {
    public PaginatedList<OrderDetail> findAll(int pageIndex, int pageSize);
    public OrderDetail findById(int id);
    public OrderDetail save(OrderDetail orderDetail);
    public OrderDetail update(OrderDetail orderDetail);
    public int delete(int id);
}
