package service;



import entity.DeliveryNoteDetail;

import java.util.List;

public interface DeliveryNoteDetailService {
    public List<DeliveryNoteDetail> findAll();
    public List<DeliveryNoteDetail> findAllByDeliveryNoteId(int deliveryNoteId);
    public DeliveryNoteDetail findById(int id);
    public DeliveryNoteDetail save(DeliveryNoteDetail deliveryNote);
    public DeliveryNoteDetail update(DeliveryNoteDetail deliveryNote);
    public int delete(int id);
}
