package service.implementation;

import entity.Staff;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import service.StaffService;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Staff> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Staff").list();
    }

    @Override
    public Staff findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff where id=:id");
        query.setInteger("id", id);
        return (Staff)query.uniqueResult();
    }

    public List<Staff> findByName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff where name like :name");
        query.setString("name", "%"+name+"%");
        return query.list();
    }

    @Override
    public Staff save(Staff staff) {
        sessionFactory.getCurrentSession().save(staff);
        return staff;
    }

    @Override
    public Staff update(Staff staff) {
        sessionFactory.getCurrentSession().update(staff);
        return staff;
    }

    @Override
    public int delete(int id) {
        Staff staff = findById(id);
        sessionFactory.getCurrentSession().delete(staff);
        return id;
    }
}
