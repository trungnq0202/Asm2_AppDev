package service;


import entity.DeliveryNote;
import helper.pagination.PaginatedList;

import java.util.Date;

public interface DeliveryNoteService {
    public PaginatedList<DeliveryNote> findAll(int pageIndex, int pageSize);
    public PaginatedList<DeliveryNote> findByDate(Date date, int pageIndex, int pageSize);
    public DeliveryNote findById(int id);
    public DeliveryNote save(DeliveryNote deliveryNote);
    public DeliveryNote update(DeliveryNote deliveryNote);
    public int delete(int id);
}
