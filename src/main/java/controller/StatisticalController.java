package controller;

import model.DeliveryNote;
import model.ReceivingNote;
import model.SalesInvoice;
import model.InventoryNote;
import model.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.StatisticalService;
import service.ProductService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class StatisticalController {

    @Autowired
    private StatisticalService statisticalService;

    //List all receiving note by a period: start date and end date
    @RequestMapping(path = "receiving_notes")
    public PaginatedList<ReceivingNote> getAllReceivingNotesByPeriodOfTime(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam int pageIndex, @RequestParam int pageSize
    ){
        return statisticalService.findReceivingNotesByTimePeriod(startDate, endDate, pageIndex, pageSize);
    }

    //List all delivery note by a period: start date and end date
    @RequestMapping(path = "delivery_notes")
    public PaginatedList<DeliveryNote> getAllDeliveryNotesByAPeriodOfTime(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam int pageIndex, @RequestParam int pageSize
    ){
        return statisticalService.findDeliveryNotesByTimePeriod(startDate, endDate, pageIndex, pageSize);
    }

    //List all sales invoice by a period: start date and end date
    @RequestMapping(path = "sales_invoices")
    public PaginatedList<SalesInvoice> getAllSalesInvoicesInAPeriodOfTime(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam int pageIndex, @RequestParam int pageSize
    ){
        return statisticalService.findInvoicesByTimePeriod(startDate, endDate, pageIndex, pageSize);
    }

    //All sales invoice by a customer and by a sale staff in a period of time: start date and end date
    @RequestMapping(path = "sales_invoices/{customerId}/{staffId}")
    public PaginatedList<SalesInvoice> getAllSalesInvoicesWithCustomerAndStaffInAPeriodOfTime(
            @PathVariable int customerId, @PathVariable int staffId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam int pageIndex, @RequestParam int pageSize
    ){
        return statisticalService.findInvoicesByCustomerAndStaffByTimePeriod(customerId, staffId, startDate, endDate, pageIndex, pageSize);
    }

    //Revenue in a period of time: start date and end date
    @RequestMapping(path = "revenue")
    public Float getRevenueInAPeriodOfTime(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return statisticalService.findRevenueByTimePeriod(startDate, endDate);
    }

    //Revenue by a customer in a period of time: start date and end date
    @RequestMapping(path = "revenue/by_customer/{customerId}")
    public Float getRevenueByCustomerInAPeriodOfTime(
            @PathVariable int customerId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return statisticalService.findRevenueByCustomerByTimePeriod(customerId, startDate, endDate);
    }

    //Revenue by a sale staff in a period of time: start date and end date
    @RequestMapping(path = "revenue/by_staff/{staffId}")
    public Float getRevenueBySaleStaffInAPeriodOfTime(
            @PathVariable int staffId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return statisticalService.findRevenueByStaffByTimePeriod(staffId, startDate, endDate);
    }

    @Autowired
    private ProductService productService;

    //Inventory of all products in warehouse at a particular date
    @RequestMapping(path = "inventory")
    public List<InventoryNote> getInventoryStatus(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return statisticalService.findInventoryStatus(startDate, endDate);
    }

}
