package service;

import entity.Product;
import entity.Staff;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(int id);
    public List<Product> findByName(String name);
    public Product save(Product product);
    public Product update(Product product);
    public int delete(int id);
}
