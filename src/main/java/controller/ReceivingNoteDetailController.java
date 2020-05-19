package controller;

import model.ReceivingNoteDetail;
import model.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ReceivingNoteDetailService;

@RestController
@RequestMapping(path = "/")
public class ReceivingNoteDetailController {

    @Autowired
    private ReceivingNoteDetailService receivingNoteDetailService;

    @RequestMapping(path = "receiving_note_details/all", method = RequestMethod.GET)
    public PaginatedList<ReceivingNoteDetail> getAllReceivingNoteDetails(@RequestParam int pageIndex, @RequestParam int pageSize){
        return receivingNoteDetailService.findAll(pageIndex, pageSize);
    }

    @RequestMapping(path = "receiving_note_details/{id}", method = RequestMethod.DELETE)
    public int deleteReceivingNoteDetail(@PathVariable int id){
        return receivingNoteDetailService.delete(id);
    }
}
