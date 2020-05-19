package service;

import model.Customer;
import model.PaginatedList;

public interface CustomerService {
    public PaginatedList<Customer> findAll(int pageIndex, int pageSize);
    public Customer findById(int id);
    public PaginatedList<Customer> findByName(String name, int pageIndex, int pageSize);
    public PaginatedList<Customer> findByPhone(String name, int pageIndex, int pageSize);
    public PaginatedList<Customer> findByAddress(String name, int pageIndex, int pageSize);
    public Customer save(Customer customer);
    public Customer update(Customer customer);
    public int delete(int id);
}
