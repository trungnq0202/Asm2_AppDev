package controller;

import entity.SalesInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.AdditionalService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class AdditionalController {

    @Autowired
    private AdditionalService additionalService;

    //All sales invoice by a customer and by a sale staff in a period: start date and end date
    @RequestMapping(path = "sales_invoices/{customerId}/{staffId}")
    public List<SalesInvoice> getAllSalesInvoicesWithCustomerAndStaffInAPeriodOfTime(
            @PathVariable int customerId, @PathVariable int staffId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return additionalService.findInvoicesByCustomerAndStaffInATimePeriod(customerId, staffId, startDate, endDate);
    }

    //Revenue
    @RequestMapping(path = "revenue")
    public Double getRevenueInAPeriodOfTime(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return additionalService.findRevenueInATimePeriod(startDate, endDate);
    }




}
