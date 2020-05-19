package service;

import model.Product;
import model.PaginatedList;

public interface ProductService {
    public PaginatedList<Product> findAll(int pageIndex, int pageSize);
    public Product findById(int id);
    public PaginatedList<Product> findByName(String name, int pageIndex, int pageSize);
    public PaginatedList<Product> findByBrand(String brand, int pageIndex, int pageSize);
    public Product save(Product product);
    public Product update(Product product);
    public int delete(int id);
}
