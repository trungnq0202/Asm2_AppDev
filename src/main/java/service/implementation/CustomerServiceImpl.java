package service.implementation;

import model.Customer;
import model.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CustomerService;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<Customer> findAll(int pageIndex, int pageSize) {
        PaginatedList<Customer> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Customer");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Customer").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public Customer findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where id=:id");
        query.setInteger("id", id);
        return (Customer)query.uniqueResult();
    }

    @Override
    public PaginatedList<Customer> findByName(String name, int pageIndex, int pageSize) {
        PaginatedList<Customer> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where name like :name");
        query.setString("name", "%"+name+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Customer where name like :name")
                .setString("name", "%"+name+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public PaginatedList<Customer> findByPhone(String name, int pageIndex, int pageSize) {
        PaginatedList<Customer> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where phone like :phone");
        query.setString("phone", "%"+name+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Customer where phone like :phone")
                .setString("phone", "%"+name+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public PaginatedList<Customer> findByAddress(String name, int pageIndex, int pageSize) {
        PaginatedList<Customer> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where address like :address");
        query.setString("address", "%"+name+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Customer where address like :address")
                .setString("address", "%"+name+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public Customer save(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        sessionFactory.getCurrentSession().update(customer);
        return customer;
    }

    @Override
    public int delete(int id) {
        Customer customer = findById(id);
        sessionFactory.getCurrentSession().delete(customer);
        return id;
    }
}
