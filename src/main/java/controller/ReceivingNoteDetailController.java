package controller;

import entity.ReceivingNoteDetail;
import helper.pagination.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ReceivingNoteDetailService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ReceivingNoteDetailController {

    @Autowired
    private ReceivingNoteDetailService receivingNoteDetailService;

    @RequestMapping(path = "receiving_note_details/all", method = RequestMethod.GET)
    public PaginatedList<ReceivingNoteDetail> getAllReceivingNoteDetails(@RequestParam int pageIndex, @RequestParam int pageSize){
        return receivingNoteDetailService.findAll(pageIndex, pageSize);
    }

    @RequestMapping(path = "receiving_note_details/{id}", method = RequestMethod.GET)
    public ReceivingNoteDetail getReceivingNoteDetailById(@PathVariable int id){
        return receivingNoteDetailService.findById(id);
    }

    @RequestMapping(path = "receiving_note_details", method = RequestMethod.POST)
    public ReceivingNoteDetail addReceivingNoteDetail(@RequestBody ReceivingNoteDetail receivingNoteDetail){
        return receivingNoteDetailService.save(receivingNoteDetail);
    }

    @RequestMapping(path = "receiving_note_details", method = RequestMethod.PUT)
    public ReceivingNoteDetail updateReceivingNoteDetail(@RequestBody ReceivingNoteDetail receivingNoteDetail){
        return receivingNoteDetailService.update(receivingNoteDetail);
    }

    @RequestMapping(path = "receiving_note_details/{id}", method = RequestMethod.DELETE)
    public int deleteReceivingNoteDetail(@PathVariable int id){
        return receivingNoteDetailService.delete(id);
    }
}
