package controller;

import entity.SalesInvoice;
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
    public List<SalesInvoice> getAllSalesInvoices(){
        return salesInvoiceService.findAll();
    }

    @RequestMapping(path = "sales_invoices/{id}", method = RequestMethod.GET)
    public SalesInvoice getSalesInvoiceById(@PathVariable int id){
        return salesInvoiceService.findById(id);
    }

    @RequestMapping(path = "sales_invoices/{customerId}/{staffId}")
    public List<SalesInvoice> getAllSalesInvoicesWithCustomerAndStaffInAPeriodOfTime(
            @PathVariable int customerId, @PathVariable int staffId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
            ){
        return salesInvoiceService.findByCustomerAndStaffInATimePeriod(customerId, staffId, startDate, endDate);
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
