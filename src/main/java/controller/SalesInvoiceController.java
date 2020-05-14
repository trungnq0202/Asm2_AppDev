package controller;

import entity.SalesInvoice;
import helper.pagination.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import service.SalesInvoiceService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class SalesInvoiceController {
    @Autowired
    private SalesInvoiceService salesInvoiceService;

    @RequestMapping(path = "sales_invoices/all", method = RequestMethod.GET)
    public PaginatedList<SalesInvoice> getAllSalesInvoices(@RequestParam int pageIndex, @RequestParam int pageSize){
        return salesInvoiceService.findAll(pageIndex, pageSize);
    }

    @RequestMapping(path = "sales_invoices/by_date", method = RequestMethod.GET)
    public PaginatedList<SalesInvoice> getAllSalesInvoicesByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
            , @RequestParam int pageIndex, @RequestParam int pageSize){
        return salesInvoiceService.findByDate(date, pageIndex, pageSize);
    }

    @RequestMapping(path = "sales_invoices/{id}", method = RequestMethod.GET)
    public SalesInvoice getSalesInvoiceById(@PathVariable int id){
        return salesInvoiceService.findById(id);
    }

    @RequestMapping(path = "sales_invoices", method = RequestMethod.POST)
    public SalesInvoice addSalesInvoice(@RequestBody SalesInvoice salesInvoice){
        return salesInvoiceService.save(salesInvoice);
    }

    @RequestMapping(path = "sales_invoices", method = RequestMethod.PUT)
    public SalesInvoice updateSalesInvoice(@RequestBody SalesInvoice salesInvoice){
        return salesInvoiceService.update(salesInvoice);
    }

    @RequestMapping(path = "sales_invoices/{id}", method = RequestMethod.DELETE)
    public int deleteSalesInvoice(@PathVariable int id){
        return salesInvoiceService.delete(id);
    }
}
