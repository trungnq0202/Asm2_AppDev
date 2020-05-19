package controller;

import model.DeliveryNoteDetail;
import model.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DeliveryNoteDetailService;

@RestController
@RequestMapping(path = "/")
public class DeliveryNoteDetailController {

    @Autowired
    private DeliveryNoteDetailService deliveryNoteDetailService;

    @RequestMapping(path = "delivery_note_details/all", method = RequestMethod.GET)
    public PaginatedList<DeliveryNoteDetail> getAllDeliveryNoteDetails(@RequestParam int pageIndex, @RequestParam int pageSize){
        return deliveryNoteDetailService.findAll(pageIndex, pageSize);
    }

    @RequestMapping(path = "delivery_note_details/{id}", method = RequestMethod.DELETE)
    public int deleteDeliveryNoteDetail(@PathVariable int id){
        return deliveryNoteDetailService.delete(id);
    }
}
