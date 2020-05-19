package controller;

import entity.Customer;
import helper.pagination.PaginatedList;
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
    public PaginatedList<Customer> getAllCustomers(@RequestParam int pageIndex, @RequestParam int pageSize){
        return customerService.findAll(pageIndex, pageSize);
    }

//    @RequestMapping(path = "customers/{id}", method = RequestMethod.GET)
//    public Customer getCustomerById(@PathVariable int id){
//        return customerService.findById(id);
//    }

    @RequestMapping(path = "customers/by_name", method = RequestMethod.GET)
    public PaginatedList<Customer> getCustomersByName(@RequestParam String name, @RequestParam int pageIndex, @RequestParam int pageSize){
        return customerService.findByName(name, pageIndex, pageSize);
    }

    @RequestMapping(path = "customers/by_phone", method = RequestMethod.GET)
    public PaginatedList<Customer> getCustomersByPhone(@RequestParam String phone, @RequestParam int pageIndex, @RequestParam int pageSize){
        return customerService.findByPhone(phone, pageIndex, pageSize);
    }

    @RequestMapping(path = "customers/by_address", method = RequestMethod.GET)
    public PaginatedList<Customer> getCustomersByAddress(@RequestParam String address, @RequestParam int pageIndex, @RequestParam int pageSize){
        return customerService.findByAddress(address, pageIndex, pageSize);
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
