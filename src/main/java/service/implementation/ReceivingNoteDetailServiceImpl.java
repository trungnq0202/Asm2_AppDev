package service.implementation;

import entity.ReceivingNote;
import entity.ReceivingNoteDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ReceivingNoteDetailService;

import java.util.List;

@Transactional
@Service
public class ReceivingNoteDetailServiceImpl implements ReceivingNoteDetailService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ReceivingNoteDetail> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM ReceivingNoteDetail").list();
    }

    @Override
    public ReceivingNoteDetail findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNoteDetail where id=:id");
        query.setInteger("id", id);
        return (ReceivingNoteDetail) query.uniqueResult();
    }


    @Override
    public ReceivingNoteDetail save(ReceivingNoteDetail receivingNoteDetail) {
        sessionFactory.getCurrentSession().save(receivingNoteDetail);
        return receivingNoteDetail;
    }

    @Override
    public ReceivingNoteDetail update(ReceivingNoteDetail receivingNoteDetail) {
        sessionFactory.getCurrentSession().update(receivingNoteDetail);
        return receivingNoteDetail;
    }

    @Override
    public int delete(int id) {
        ReceivingNoteDetail receivingNoteDetail = findById(id);
        sessionFactory.getCurrentSession().delete(receivingNoteDetail);
        return id;
    }
}
