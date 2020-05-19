package service.implementation;

import model.OrderDetail;
import model.ReceivingNote;
import model.ReceivingNoteDetail;
import model.PaginatedList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.OrderService;
import service.ReceivingNoteDetailService;
import service.ReceivingNoteService;

import java.util.ArrayList;
import java.util.Date;

@Transactional
@Service
public class ReceivingNoteServiceImpl implements ReceivingNoteService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ReceivingNoteDetailService receivingNoteDetailService;

    @Autowired
    private OrderService orderService;

    @Override
    public PaginatedList<ReceivingNote> findAll(int pageIndex, int pageSize) {
        PaginatedList<ReceivingNote> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM ReceivingNote");

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from ReceivingNote").uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());

        return paginatedList;
    }

    @Override
    public PaginatedList<ReceivingNote> findByDate(Date date, int pageIndex, int pageSize) {
        PaginatedList<ReceivingNote> paginatedList = new PaginatedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote where date = :date");
        query.setDate("date", date);

        int totalRow = Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from ReceivingNote where date = :date")
                .setDate("date", date).uniqueResult().toString());
        paginatedList.create(totalRow, pageIndex, pageSize);
        query.setFirstResult(paginatedList.getOffset());
        query.setMaxResults(pageSize);
        paginatedList.setItems(query.list());
        return paginatedList;
    }

    @Override
    public ReceivingNote findById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote where id=:id");
        query.setInteger("id", id);
        return (ReceivingNote) query.uniqueResult();
    }

    @Override
    public ReceivingNote save(ReceivingNote receivingNote) {
        receivingNote.setReceivingNoteDetails(new ArrayList<>());
        for (OrderDetail orderDetail: orderService.findById(receivingNote.getOrder().getId()).getOrderDetails()){
            ReceivingNoteDetail receivingNoteDetail = new ReceivingNoteDetail(0, receivingNote, orderDetail.getProduct(), orderDetail.getQuantity());
            receivingNote.getReceivingNoteDetails().add(receivingNoteDetail);
            receivingNoteDetailService.save(receivingNoteDetail);
        }
        sessionFactory.getCurrentSession().save(receivingNote);
        return receivingNote;
    }

    @Override
    public ReceivingNote update(ReceivingNote receivingNote) {
        ReceivingNote savedReceivingNote = findById(receivingNote.getId());
        int oldOrderId = savedReceivingNote.getOrder().getId();
        int newOrderId = receivingNote.getOrder().getId();

        //If new Order Id is updated
//        if (oldOrderId != newOrderId){
            //Delete old Receiving Note Details
            for (ReceivingNoteDetail receivingNoteDetail:savedReceivingNote.getReceivingNoteDetails()){
                receivingNoteDetailService.delete(receivingNoteDetail.getId());
            }
            savedReceivingNote.getReceivingNoteDetails().clear();

            //Add new Receiving Note Details
            for (OrderDetail orderDetail: orderService.findById(newOrderId).getOrderDetails()){
                ReceivingNoteDetail receivingNoteDetail = new ReceivingNoteDetail(0, receivingNote, orderDetail.getProduct(), orderDetail.getQuantity());
                savedReceivingNote.getReceivingNoteDetails().add(receivingNoteDetail);
                receivingNoteDetailService.save(receivingNoteDetail);
            }
//        }

        //Update others
        savedReceivingNote.setDate(receivingNote.getDate());
        savedReceivingNote.setStaff(receivingNote.getStaff());
        savedReceivingNote.setOrder(receivingNote.getOrder());

        sessionFactory.getCurrentSession().update(savedReceivingNote);
        return savedReceivingNote;
    }

    @Override
    public int delete(int id) {
        ReceivingNote receivingNote = findById(id);
        for (ReceivingNoteDetail receivingNoteDetail:receivingNote.getReceivingNoteDetails())
            receivingNoteDetailService.delete(receivingNoteDetail.getId());
        sessionFactory.getCurrentSession().delete(receivingNote);
        return id;
    }

}
