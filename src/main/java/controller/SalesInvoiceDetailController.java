package controller;

import entity.SalesInvoiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.SalesInvoiceDetailService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class SalesInvoiceDetailController {
    @Autowired
    private SalesInvoiceDetailService salesInvoiceDetailService;

    @RequestMapping(path = "sales_invoice_details/all", method = RequestMethod.GET)
    public List<SalesInvoiceDetail> getAllSalesInvoiceDetails(){
        return salesInvoiceDetailService.findAll();
    }

    @RequestMapping(path = "sales_invoice_details/{id}", method = RequestMethod.GET)
    public SalesInvoiceDetail getSalesInvoiceDetailById(@PathVariable int id){
        return salesInvoiceDetailService.findById(id);
    }

    @RequestMapping(path = "sales_invoice_details", method = RequestMethod.POST)
    public SalesInvoiceDetail addSSalesInvoiceDetail(@RequestBody SalesInvoiceDetail salesInvoiceDetail){
        return salesInvoiceDetailService.save(salesInvoiceDetail);
    }

    @RequestMapping(path = "sales_invoice_details", method = RequestMethod.PUT)
    public SalesInvoiceDetail updateSalesInvoiceDetail(@RequestBody SalesInvoiceDetail salesInvoiceDetail){
        return salesInvoiceDetailService.update(salesInvoiceDetail);
    }

    @RequestMapping(path = "sales_invoice_details/{id}", method = RequestMethod.DELETE)
    public int deleteSalesInvoiceDetail(@PathVariable int id){
        return salesInvoiceDetailService.delete(id);
    }
}
