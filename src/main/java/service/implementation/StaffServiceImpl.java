package service.implementation;

import model.Staff;
import model.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.StaffService;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<Staff> findAll(int pageIndex, int pageSize) {
        PaginatedList<Staff> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Staff");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Staff").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public Staff findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff where id=:id");
        query.setInteger("id", id);
        return (Staff)query.uniqueResult();
    }

    @Override
    public PaginatedList<Staff> findByName(String name, int pageIndex, int pageSize){
        PaginatedList<Staff> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff where name like :name");
        query.setString("name", "%"+name+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Staff where name like :name")
                .setString("name", "%"+name+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public PaginatedList<Staff> findByAddress(String address, int pageIndex, int pageSize) {
        PaginatedList<Staff> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff where address like :address");
        query.setString("address", "%"+address+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Staff where address like :address")
                .setString("address", "%"+address+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public PaginatedList<Staff> findByPhone(String phone, int pageIndex, int pageSize) {
        PaginatedList<Staff> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff where phone like :phone");
        query.setString("phone", "%"+phone+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Staff where phone like :phone")
                .setString("phone", "%"+phone+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
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
