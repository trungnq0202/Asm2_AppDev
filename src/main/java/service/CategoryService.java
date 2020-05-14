package service;
import entity.Category;
import helper.pagination.PaginatedList;

import java.util.List;

public interface CategoryService {
    public PaginatedList<Category> findAll(int pageIndex, int pageSize);
    public Category findById(int id);
    public PaginatedList<Category> findByName(String name, int pageIndex, int pageSize);
    public Category save(Category model);
    public Category update(Category model);
    public int delete(int id);
}
