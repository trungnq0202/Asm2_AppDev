package service;

import model.ReceivingNoteDetail;
import model.PaginatedList;

public interface ReceivingNoteDetailService {
    public PaginatedList<ReceivingNoteDetail> findAll(int pageIndex, int pageSize);
    public ReceivingNoteDetail findById(int id);
    public ReceivingNoteDetail save(ReceivingNoteDetail receivingNoteDetail);
    public ReceivingNoteDetail update(ReceivingNoteDetail receivingNoteDetail);
    public int delete(int id);
}
