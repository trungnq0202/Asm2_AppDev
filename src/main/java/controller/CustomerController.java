package controller;

import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "customers/all", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        return customerService.findAll();
    }

    @RequestMapping(path = "customers/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable int id){
        return customerService.findById(id);
    }

    @RequestMapping(path = "customers", method = RequestMethod.GET)
    public List<Customer> getCustomersByName(@RequestParam String name){
        return customerService.findByName(name);
    }

    @RequestMapping(path = "customers", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @RequestMapping(path = "customers", method = RequestMethod.PUT)
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.update(customer);
    }

    @RequestMapping(path = "customers/{id}", method = RequestMethod.DELETE)
    public int deleteCustomer(@PathVariable int id){
        return customerService.delete(id);
    }

}
