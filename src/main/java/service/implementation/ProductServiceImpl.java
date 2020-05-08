package service.implementation;

import entity.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ProductService;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Product").list();
    }

    @Override
    public Product findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product where id=:id");
        query.setInteger("id", id);
        return (Product) query.uniqueResult();
    }

    @Override
    public List<Product> findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product where name like :name");
        query.setString("name", "%"+name+"%");
        return query.list();
    }

    @Override
    public Product save(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        sessionFactory.getCurrentSession().update(product);
        return product;
    }

    @Override
    public int delete(int id) {
        Product product = findById(id);
        sessionFactory.getCurrentSession().delete(product);
        return id;
    }
}
