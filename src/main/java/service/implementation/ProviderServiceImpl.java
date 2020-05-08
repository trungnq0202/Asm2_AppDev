package service.implementation;

import entity.Provider;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ProviderService;

import java.util.List;

@Transactional
@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Provider> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Provider").list();
    }

    @Override
    public Provider findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Provider where id=:id");
        query.setInteger("id", id);
        return (Provider) query.uniqueResult();
    }

    @Override
    public List<Provider> findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Provider where name like :name");
        query.setString("name", "%"+name+"%");
        return query.list();
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
