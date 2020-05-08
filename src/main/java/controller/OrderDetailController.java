package controller;

import entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(path = "order_details/all", method = RequestMethod.GET)
    public List<OrderDetail> getAllOrderDetails(){
        return orderDetailService.findAll();
    }

    @RequestMapping(path = "order_details/{id}", method = RequestMethod.GET)
    public OrderDetail getOrderDetailById(@PathVariable int id){
        return orderDetailService.findById(id);
    }

    @RequestMapping(path = "order_details", method = RequestMethod.POST)
    public OrderDetail addOrderDetail(@RequestBody OrderDetail staff){
        return orderDetailService.save(staff);
    }

    @RequestMapping(path = "order_details", method = RequestMethod.PUT)
    public OrderDetail updateOrderDetail(@RequestBody OrderDetail staff){
        return orderDetailService.update(staff);
    }

    @RequestMapping(path = "order_details/{id}", method = RequestMethod.DELETE)
    public int deleteOrderDetail(@PathVariable int id){
        return orderDetailService.delete(id);
    }
}
