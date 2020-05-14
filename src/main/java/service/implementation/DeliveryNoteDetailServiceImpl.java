package service.implementation;

import entity.DeliveryNoteDetail;
import entity.Order;
import helper.pagination.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DeliveryNoteDetailService;

import java.util.List;

@Transactional
@Service
public class DeliveryNoteDetailServiceImpl implements DeliveryNoteDetailService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<DeliveryNoteDetail> findAll(int pageIndex, int pageSize) {
        PaginatedList<DeliveryNoteDetail> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM DeliveryNoteDetail");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from DeliveryNoteDetail").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public DeliveryNoteDetail findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNoteDetail where id=:id");
        query.setInteger("id", id);
        return (DeliveryNoteDetail) query.uniqueResult();
    }

    @Override
    public DeliveryNoteDetail save(DeliveryNoteDetail deliveryNoteDetail) {
        sessionFactory.getCurrentSession().save(deliveryNoteDetail);
        return  deliveryNoteDetail;
    }

    @Override
    public DeliveryNoteDetail update(DeliveryNoteDetail deliveryNoteDetail) {
        sessionFactory.getCurrentSession().update(deliveryNoteDetail);
        return deliveryNoteDetail;
    }

    @Override
    public int delete(int id) {
        DeliveryNoteDetail deliveryNoteDetail = findById(id);
        sessionFactory.getCurrentSession().delete(deliveryNoteDetail);
        return id;
    }
}
