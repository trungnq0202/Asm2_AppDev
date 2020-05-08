package service;

import entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> findAll();
    public Customer findById(int id);
    public List<Customer> findByName(String name);
    public Customer save(Customer customer);
    public Customer update(Customer customer);
    public int delete(int id);
}
