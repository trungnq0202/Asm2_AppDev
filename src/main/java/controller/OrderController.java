package controller;

import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "orders/all", method = RequestMethod.GET)
    public List<Order> getAllOrders(){
        return orderService.findAll();
    }

    @RequestMapping(path = "orders/{id}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable int id){
        return orderService.findById(id);
    }

    @RequestMapping(path = "orders", method = RequestMethod.POST)
    public Order addOrder(@RequestBody Order order){
        return orderService.save(order);
    }

    @RequestMapping(path = "orders", method = RequestMethod.PUT)
    public Order updateOrder(@RequestBody Order order){
        return orderService.update(order);
    }

    @RequestMapping(path = "orders/{id}", method = RequestMethod.DELETE)
    public int deleteOrder(@PathVariable int id){
        return orderService.delete(id);
    }

}
