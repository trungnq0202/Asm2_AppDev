package service.implementation;

import model.ReceivingNoteDetail;
import model.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ReceivingNoteDetailService;

@Transactional
@Service
public class ReceivingNoteDetailServiceImpl implements ReceivingNoteDetailService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginatedList<ReceivingNoteDetail> findAll(int pageIndex, int pageSize) {
        PaginatedList<ReceivingNoteDetail> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM ReceivingNoteDetail");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from ReceivingNoteDetail").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
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
