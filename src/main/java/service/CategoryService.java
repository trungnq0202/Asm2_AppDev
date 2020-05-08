package service;
import entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll();
    public Category findById(int id);
    public List<Category> findByName(String name);
    public Category save(Category model);
    public Category update(Category model);
    public int delete(int id);
}
