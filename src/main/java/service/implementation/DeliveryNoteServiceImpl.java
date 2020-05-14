package service.implementation;

import entity.DeliveryNote;
import entity.DeliveryNoteDetail;
import entity.Order;
import helper.pagination.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DeliveryNoteService;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<DeliveryNote> findAll(int pageIndex, int pageSize) {
        PaginatedList<DeliveryNote> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM DeliveryNote");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from DeliveryNote").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public PaginatedList<DeliveryNote> findByDate(Date date, int pageIndex, int pageSize) {
        PaginatedList<DeliveryNote> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote where date = :date");
        query.setDate("date", date);

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from DeliveryNote where date = :date")
                .setDate("date", date).uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public DeliveryNote findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote where id=:id");
        query.setInteger("id", id);
        return (DeliveryNote) query.uniqueResult();
    }

    @Override
    public DeliveryNote save(DeliveryNote deliveryNote) {
        for (DeliveryNoteDetail deliveryNoteDetail: deliveryNote.getDeliveryNoteDetails()){
            deliveryNoteDetail.setDeliveryNote(deliveryNote);
        }
        sessionFactory.getCurrentSession().save(deliveryNote);
        return  deliveryNote;
    }

    @Override
    public DeliveryNote update(DeliveryNote deliveryNote) {
        for (DeliveryNoteDetail deliveryNoteDetail: deliveryNote.getDeliveryNoteDetails()){
            deliveryNoteDetail.setDeliveryNote(deliveryNote);
        }
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
