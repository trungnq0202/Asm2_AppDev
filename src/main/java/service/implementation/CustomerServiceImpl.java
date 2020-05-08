package service.implementation;

import entity.Customer;
import entity.Staff;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CustomerService;

import java.util.List;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Customer").list();
    }

    @Override
    public Customer findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where id=:id");
        query.setInteger("id", id);
        return (Customer)query.uniqueResult();
    }

    @Override
    public List<Customer> findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where name like :name");
        query.setString("name", "%"+name+"%");
        return query.list();
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
