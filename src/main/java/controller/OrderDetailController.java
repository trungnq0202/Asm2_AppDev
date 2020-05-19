package controller;

import entity.OrderDetail;
import helper.pagination.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OrderDetailService;

@RestController
@RequestMapping(path = "/")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(path = "order_details/all", method = RequestMethod.GET)
    public PaginatedList<OrderDetail> getAllOrderDetails(@RequestParam int pageIndex, @RequestParam int pageSize){
        return orderDetailService.findAll(pageIndex, pageSize);
    }

//    @RequestMapping(path = "order_details/{id}", method = RequestMethod.GET)
//    public OrderDetail getOrderDetailById(@PathVariable int id){
//        return orderDetailService.findById(id);
//    }

//    @RequestMapping(path = "order_details", method = RequestMethod.POST)
//    public OrderDetail addOrderDetail(@RequestBody OrderDetail orderDetail){
//        return orderDetailService.save(orderDetail);
//    }

//    @RequestMapping(path = "order_details", method = RequestMethod.PUT)
//    public OrderDetail updateOrderDetail(@RequestBody OrderDetail orderDetail){
//        return orderDetailService.update(orderDetail);
//    }

    @RequestMapping(path = "order_details/{id}", method = RequestMethod.DELETE)
    public int deleteOrderDetail(@PathVariable int id){
        return orderDetailService.delete(id);
    }

}
