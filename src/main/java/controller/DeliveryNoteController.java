package controller;

import entity.DeliveryNote;
import entity.ReceivingNote;
import helper.pagination.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import service.DeliveryNoteService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class DeliveryNoteController {
    @Autowired
    private DeliveryNoteService deliveryNoteService;

    @RequestMapping(path = "delivery_notes/all", method = RequestMethod.GET)
    public PaginatedList<DeliveryNote> getAllDeliveryNotes(@RequestParam int pageIndex, @RequestParam int pageSize){
        return deliveryNoteService.findAll(pageIndex, pageSize);
    }

    @RequestMapping(path = "delivery_notes/by_date", method = RequestMethod.GET)
    public PaginatedList<DeliveryNote> getAllDeliveryNotesByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
            , @RequestParam int pageIndex, @RequestParam int pageSize){
        return deliveryNoteService.findByDate(date, pageIndex, pageSize);
    }

//    @RequestMapping(path = "delivery_notes/{id}", method = RequestMethod.GET)
//    public DeliveryNote getDeliveryNoteById(@PathVariable int id){
//        return deliveryNoteService.findById(id);
//    }

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
