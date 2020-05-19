package service;



import model.DeliveryNoteDetail;
import model.PaginatedList;

public interface DeliveryNoteDetailService {
    public PaginatedList<DeliveryNoteDetail> findAll(int pageIndex, int pageSize);
    public DeliveryNoteDetail findById(int id);
    public DeliveryNoteDetail save(DeliveryNoteDetail deliveryNote);
    public DeliveryNoteDetail update(DeliveryNoteDetail deliveryNote);
    public int delete(int id);
}
