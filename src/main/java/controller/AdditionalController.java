package controller;

import entity.Product;
import entity.SalesInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.AdditionalService;
import service.ProductService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class AdditionalController {

    @Autowired
    private AdditionalService additionalService;

    //All sales invoice by a customer and by a sale staff in a period of time: start date and end date
    @RequestMapping(path = "sales_invoices/{customerId}/{staffId}")
    public List<SalesInvoice> getAllSalesInvoicesWithCustomerAndStaffInAPeriodOfTime(
            @PathVariable int customerId, @PathVariable int staffId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return additionalService.findInvoicesByCustomerAndStaffInATimePeriod(customerId, staffId, startDate, endDate);
    }

    //Revenue in a period of time: start date and end date
    @RequestMapping(path = "revenue")
    public Float getRevenueInAPeriodOfTime(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return additionalService.findRevenueInATimePeriod(startDate, endDate);
    }

    //Revenue by a customer in a period of time: start date and end date
    @RequestMapping(path = "revenue/{customerId}")
    public Float getRevenueByCustomerInAPeriodOfTime(
            @PathVariable String customerId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return additionalService.findRevenueInATimePeriod(startDate, endDate);
    }

    //Revenue by a sale staff in a period of time: start date and end date
    @RequestMapping(path = "revenue/{staffId}")
    public Float getRevenueBySaleStaffInAPeriodOfTime(
            @PathVariable String staffId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return additionalService.findRevenueInATimePeriod(startDate, endDate);
    }

    @Autowired
    private ProductService productService;

    //Inventory of all products in warehouse at a particular date
    @RequestMapping(path = "inventory")
    public HashMap<HashMap<Integer, String>, HashMap<String, Integer>> getInventoryStatus(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return additionalService.findInventoryStatus(startDate, endDate);
    }
}
