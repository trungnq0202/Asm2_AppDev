package controller;

import model.SalesInvoiceDetail;
import model.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.SalesInvoiceDetailService;

@RestController
@RequestMapping(path = "/")
public class SalesInvoiceDetailController {
    @Autowired
    private SalesInvoiceDetailService salesInvoiceDetailService;

    @RequestMapping(path = "sales_invoice_details/all", method = RequestMethod.GET)
    public PaginatedList<SalesInvoiceDetail> getAllSalesInvoiceDetails(@RequestParam int pageIndex, @RequestParam int pageSize){
        return salesInvoiceDetailService.findAll(pageIndex, pageSize);
    }

    @RequestMapping(path = "sales_invoice_details/{id}", method = RequestMethod.DELETE)
    public int deleteSalesInvoiceDetail(@PathVariable int id){
        return salesInvoiceDetailService.delete(id);
    }
}
