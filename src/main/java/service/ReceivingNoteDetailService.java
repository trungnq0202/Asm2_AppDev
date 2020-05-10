package service;

import entity.ReceivingNoteDetail;

import java.util.List;

public interface ReceivingNoteDetailService {
    public List<ReceivingNoteDetail> findAll();
    public ReceivingNoteDetail findById(int id);
    public ReceivingNoteDetail save(ReceivingNoteDetail receivingNoteDetail);
    public ReceivingNoteDetail update(ReceivingNoteDetail receivingNoteDetail);
    public int delete(int id);
}
