package service.implementation;

import entity.DeliveryNote;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DeliveryNoteService;

import java.util.List;

@Transactional
@Service
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<DeliveryNote> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM DeliveryNote").list();
    }

    @Override
    public DeliveryNote findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote where id=:id");
        query.setInteger("id", id);
        return (DeliveryNote) query.uniqueResult();
    }

    @Override
    public DeliveryNote save(DeliveryNote deliveryNote) {
        sessionFactory.getCurrentSession().save(deliveryNote);
        return  deliveryNote;
    }

    @Override
    public DeliveryNote update(DeliveryNote deliveryNote) {
        sessionFactory.getCurrentSession().update(deliveryNote);
        return deliveryNote;
    }

    @Override
    public int delete(int id) {
        DeliveryNote deliveryNote = findById(id);
        sessionFactory.getCurrentSession().delete(deliveryNote);
        return id;
    }
}
