package service;


import entity.ReceivingNote;

import java.util.List;

public interface ReceivingNoteService {
    public List<ReceivingNote> findAll();
    public ReceivingNote findById(int id);
    public ReceivingNote save(ReceivingNote receivingNote);
    public ReceivingNote update(ReceivingNote receivingNote);
    public int delete(int id);
}
