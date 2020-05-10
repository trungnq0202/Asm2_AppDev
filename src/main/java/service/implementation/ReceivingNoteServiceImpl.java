package service.implementation;

import entity.OrderDetail;
import entity.ReceivingNote;
import entity.ReceivingNoteDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.OrderDetailService;
import service.ReceivingNoteDetailService;
import service.ReceivingNoteService;

import java.util.List;

@Transactional
@Service
public class ReceivingNoteServiceImpl implements ReceivingNoteService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ReceivingNoteDetailService receivingNoteDetailService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public List<ReceivingNote> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM ReceivingNote").list();
    }

    @Override
    public ReceivingNote findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote where id=:id");
        query.setInteger("id", id);
        return (ReceivingNote) query.uniqueResult();
    }

    @Override
    public ReceivingNote save(ReceivingNote receivingNote) {
        sessionFactory.getCurrentSession().save(receivingNote);

        //Get List of order details through order id
        List<OrderDetail> orderDetailList = orderDetailService.findAllByOrderId(receivingNote.getOrder().getId());

        //Add list of Order detail to Receiving Note Detail
        for (OrderDetail orderDetail:orderDetailList){
            ReceivingNoteDetail receivingNoteDetail = new ReceivingNoteDetail(0, receivingNote, orderDetail.getProduct(), orderDetail.getQuantity());
            receivingNoteDetailService.save(receivingNoteDetail);
        }

        return receivingNote;
    }

    @Override
    public ReceivingNote update(ReceivingNote receivingNote) {
        sessionFactory.getCurrentSession().update(receivingNote);
        return receivingNote;
    }

    @Override
    public int delete(int id) {
        ReceivingNote receivingNote = findById(id);
        sessionFactory.getCurrentSession().delete(receivingNote);
        return id;
    }

}
