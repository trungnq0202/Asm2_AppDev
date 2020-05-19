package service;
import model.Category;
import model.PaginatedList;

public interface CategoryService {
    public PaginatedList<Category> findAll(int pageIndex, int pageSize);
    public Category findById(int id);
    public PaginatedList<Category> findByName(String name, int pageIndex, int pageSize);
    public Category save(Category model);
    public Category update(Category model);
    public int delete(int id);
}
