package controller;

import entity.DeliveryNoteDetail;
import helper.pagination.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DeliveryNoteDetailService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class DeliveryNoteDetailController {

    @Autowired
    private DeliveryNoteDetailService deliveryNoteDetailService;

    @RequestMapping(path = "delivery_note_details/all", method = RequestMethod.GET)
    public PaginatedList<DeliveryNoteDetail> getAllDeliveryNoteDetails(@RequestParam int pageIndex, @RequestParam int pageSize){
        return deliveryNoteDetailService.findAll(pageIndex, pageSize);
    }

    @RequestMapping(path = "delivery_note_details/{id}", method = RequestMethod.GET)
    public DeliveryNoteDetail getDeliveryNoteDetailById(@PathVariable int id){
        return deliveryNoteDetailService.findById(id);
    }

    @RequestMapping(path = "delivery_note_details", method = RequestMethod.POST)
    public DeliveryNoteDetail addDeliveryNoteDetail(@RequestBody DeliveryNoteDetail deliveryNoteDetail){
        return deliveryNoteDetailService.save(deliveryNoteDetail);
    }

    @RequestMapping(path = "delivery_note_details", method = RequestMethod.PUT)
    public DeliveryNoteDetail updateDeliveryNoteDetail(@RequestBody DeliveryNoteDetail deliveryNoteDetail){
        return deliveryNoteDetailService.update(deliveryNoteDetail);
    }

    @RequestMapping(path = "delivery_note_details/{id}", method = RequestMethod.DELETE)
    public int deleteDeliveryNoteDetail(@PathVariable int id){
        return deliveryNoteDetailService.delete(id);
    }
}
