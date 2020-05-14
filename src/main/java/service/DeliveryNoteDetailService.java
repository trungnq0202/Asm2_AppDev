package service;



import entity.DeliveryNoteDetail;
import helper.pagination.PaginatedList;

import java.util.List;

public interface DeliveryNoteDetailService {
    public PaginatedList<DeliveryNoteDetail> findAll(int pageIndex, int pageSize);
    public DeliveryNoteDetail findById(int id);
    public DeliveryNoteDetail save(DeliveryNoteDetail deliveryNote);
    public DeliveryNoteDetail update(DeliveryNoteDetail deliveryNote);
    public int delete(int id);
}
