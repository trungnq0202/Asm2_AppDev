package service.implementation;

import entity.Category;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CategoryServiceImpl implements service.CategoryService {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Category> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Category").list();
    }

    @Override
    public Category findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Category where id=:id");
        query.setInteger("id", id);
        return (Category) query.uniqueResult();
    }

    @Override
    public List<Category> findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Category where name like :name");
        query.setString("name", "%"+name+"%");
        return query.list();
    }

    @Override
    public Category save(Category category) {
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
