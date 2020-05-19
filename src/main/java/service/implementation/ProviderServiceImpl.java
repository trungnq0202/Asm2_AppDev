package service.implementation;

import model.Provider;
import model.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ProviderService;

@Transactional
@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<Provider> findAll(int pageIndex, int pageSize) {
        PaginatedList<Provider> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Provider");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Provider").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public Provider findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Provider where id=:id");
        query.setInteger("id", id);
        return (Provider) query.uniqueResult();
    }

    @Override
    public PaginatedList<Provider> findByName(String name, int pageIndex, int pageSize){
        PaginatedList<Provider> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Provider where name like :name");
        query.setString("name", "%"+name+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Provider where name like :name")
                .setString("name", "%"+name+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public PaginatedList<Provider> findByAddress(String address, int pageIndex, int pageSize) {
        PaginatedList<Provider> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Provider where address like :address");
        query.setString("address", "%"+address+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Provider where address like :address")
                .setString("address", "%"+address+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public PaginatedList<Provider> findByPhone(String phone, int pageIndex, int pageSize) {
        PaginatedList<Provider> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Provider where phone like :phone");
        query.setString("phone", "%"+phone+"%");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Provider where phone like :phone")
                .setString("phone", "%"+phone+"%").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public Provider save(Provider provider) {
        sessionFactory.getCurrentSession().save(provider);
        return provider;
    }

    @Override
    public Provider update(Provider provider) {
        sessionFactory.getCurrentSession().update(provider);
        return provider;
    }

    @Override
    public int delete(int id) {
        Provider provider = findById(id);
        sessionFactory.getCurrentSession().delete(provider);
        return id;
    }
}
