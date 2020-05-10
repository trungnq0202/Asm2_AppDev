package service;


import entity.DeliveryNote;

import java.util.List;

public interface DeliveryNoteService {
    public List<DeliveryNote> findAll();
    public DeliveryNote findById(int id);
    public DeliveryNote save(DeliveryNote deliveryNote);
    public DeliveryNote update(DeliveryNote deliveryNote);
    public int delete(int id);
}
