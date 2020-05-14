package service;


import entity.Order;
import entity.ReceivingNote;
import helper.pagination.PaginatedList;

import java.util.Date;
import java.util.List;

public interface ReceivingNoteService {
    public PaginatedList<ReceivingNote> findAll(int pageIndex, int pageSize);
    public PaginatedList<ReceivingNote> findByDate(Date date, int pageIndex, int pageSize);
    public ReceivingNote findById(int id);
    public ReceivingNote save(ReceivingNote receivingNote);
    public ReceivingNote update(ReceivingNote receivingNote);
    public int delete(int id);
}
