package service;

import entity.ReceivingNoteDetail;
import helper.pagination.PaginatedList;

import java.util.List;

public interface ReceivingNoteDetailService {
    public PaginatedList<ReceivingNoteDetail> findAll(int pageIndex, int pageSize);
    public ReceivingNoteDetail findById(int id);
    public ReceivingNoteDetail save(ReceivingNoteDetail receivingNoteDetail);
    public ReceivingNoteDetail update(ReceivingNoteDetail receivingNoteDetail);
    public int delete(int id);
}
