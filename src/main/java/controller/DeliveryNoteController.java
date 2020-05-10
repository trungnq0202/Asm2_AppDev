package controller;

import entity.DeliveryNote;
import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DeliveryNoteService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class DeliveryNoteController {
    @Autowired
    private DeliveryNoteService deliveryNoteService;

    @RequestMapping(path = "delivery_notes/all", method = RequestMethod.GET)
    public List<DeliveryNote> getAllDeliveryNotes(){
        return deliveryNoteService.findAll();
    }

    @RequestMapping(path = "delivery_notes/{id}", method = RequestMethod.GET)
    public DeliveryNote getDeliveryNoteById(@PathVariable int id){
        return deliveryNoteService.findById(id);
    }

    @RequestMapping(path = "delivery_notes", method = RequestMethod.POST)
    public DeliveryNote addDeliveryNote(@RequestBody DeliveryNote deliveryNote){
        return deliveryNoteService.save(deliveryNote);
    }

    @RequestMapping(path = "delivery_notes", method = RequestMethod.PUT)
    public DeliveryNote updateDeliveryNote(@RequestBody DeliveryNote deliveryNote){
        return deliveryNoteService.update(deliveryNote);
    }

    @RequestMapping(path = "delivery_notes/{id}", method = RequestMethod.DELETE)
    public int deleteDeliveryNote(@PathVariable int id){
        return deliveryNoteService.delete(id);
    }

}
