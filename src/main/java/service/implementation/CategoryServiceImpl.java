package service.implementation;

import model.Category;
import model.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryServiceImpl implements service.CategoryService {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public PaginatedList<Category> findAll(int pageIndex, int pageSize) {
        PaginatedList<Category> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Category");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Category").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public Category findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Category where id=:id");
        query.setInteger("id", id);
        return (Category) query.uniqueResult();
    }

    @Override
    public PaginatedList<Category> findByName(String name, int pageIndex, int pageSize) {
        PaginatedList<Category> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Category where name like :name");
        query.setString("name", "%"+name+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Category where name like :name")
                .setString("name", "%"+name+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public Category save(Category category) {
        System.out.println("cate ser" + category.getId());
        sessionFactory.getCurrentSession().save(category);
        return category;
    }

    @Override
    public Category update(Category category) {
        sessionFactory.getCurrentSession().update(category);
        return category;
    }

    @Override
    public int delete(int id) {
        Category category = findById(id);
        sessionFactory.getCurrentSession().delete(category);
        return id;
    }
}
