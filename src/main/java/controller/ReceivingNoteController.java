package controller;

import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ReceivingNoteService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ReceivingNoteController {

    @Autowired
    private ReceivingNoteService receivingNoteService;

    @RequestMapping(path = "receiving_notes/all", method = RequestMethod.GET)
    public List<ReceivingNote> getAllReceivingNotes(){
        return receivingNoteService.findAll();
    }

    @RequestMapping(path = "receiving_notes/{id}", method = RequestMethod.GET)
    public ReceivingNote getReceivingNoteById(@PathVariable int id){
        return receivingNoteService.findById(id);
    }

    @RequestMapping(path = "receiving_notes", method = RequestMethod.POST)
    public ReceivingNote addReceivingNote(@RequestBody ReceivingNote receivingNote){
        return receivingNoteService.save(receivingNote);
    }

    @RequestMapping(path = "receiving_notes", method = RequestMethod.PUT)
    public ReceivingNote updateReceivingNote(@RequestBody ReceivingNote receivingNote){
        return receivingNoteService.update(receivingNote);
    }

    @RequestMapping(path = "receiving_notes/{id}", method = RequestMethod.DELETE)
    public int deleteReceivingNote(@PathVariable int id){
        return receivingNoteService.delete(id);
    }


}
