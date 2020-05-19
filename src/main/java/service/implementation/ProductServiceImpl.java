package service.implementation;

import model.Product;
import model.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ProductService;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<Product> findAll(int pageIndex, int pageSize) {
        PaginatedList<Product> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Product");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Product").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public Product findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product where id=:id");
        query.setInteger("id", id);
        return (Product) query.uniqueResult();
    }

    @Override
    public PaginatedList<Product> findByName(String name, int pageIndex, int pageSize) {
        PaginatedList<Product> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Product where name like :name");
        query.setString("name", "%"+name+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Product where name like :name")
                .setString("name", "%"+name+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public PaginatedList<Product> findByBrand(String brand, int pageIndex, int pageSize) {
        PaginatedList<Product> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Product where brand like :brand");
        query.setString("brand", "%"+brand+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Product where brand like :brand")
                .setString("brand", "%"+brand+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
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
