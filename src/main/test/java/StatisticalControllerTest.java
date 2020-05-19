import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.DeliveryNote;
import model.ReceivingNote;
import model.SalesInvoice;
import helper.*;
import model.InventoryNote;
import model.PaginatedList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatisticalControllerTest {
    private int staffId;
    private int providerId;
    private int customerId;
    private int categoryId;
    private int productId;
    private int orderId;
    private int orderDetailId;
    private int receivingNoteId;
    private int receivingNoteDetailId;
    private int deliveryNoteId;
    private int deliveryNoteDetailId;
    private int salesInvoiceId;
    private int salesInvoiceDetailId;
    private static boolean setUpIsDone = false;

    @Before
    public void setup() throws IOException {
        if (setUpIsDone){
            this.staffId = TestDataIdGetter.getIdOfStaffTestData();
            this.customerId = TestDataIdGetter.getIdOfCustomerTestData();
            this.providerId = TestDataIdGetter.getIdOfProviderTestData();
            this.categoryId = TestDataIdGetter.getIdOfCategoryTestData();
            this.productId = TestDataIdGetter.getIdOfProductTestData() ;
            this.orderId = TestDataIdGetter.getIdOfOrderTestData();
            this.orderDetailId = TestDataIdGetter.getIdOfOrderDetailTestData() ;
            this.receivingNoteId = TestDataIdGetter.getIdOfReceivingNoteTestData();
            this.receivingNoteDetailId = TestDataIdGetter.getIdOfReceivingNoteDetailTestData();
            this.deliveryNoteId = TestDataIdGetter.getIdOfDeliveryNoteTestData();
            this.deliveryNoteDetailId = TestDataIdGetter.getIdOfDeliveryNoteDetailTestData();
            this.salesInvoiceId = TestDataIdGetter.getIdOfSalesInvoiceTestData();
            this.salesInvoiceDetailId = TestDataIdGetter.getIdOfSalesInvoiceTestData();

        } else {
            this.staffId = TestDataAdder.addStaffTestDataAndReturnId();
            this.customerId = TestDataAdder.addCustomerTestDataAndReturnId();
            this.providerId = TestDataAdder.addProviderTestDataAndReturnId();
            this.categoryId = TestDataAdder.addCategoryTestDataAndReturnId();
            this.productId = TestDataAdder.addProductTestDataAndReturnId(this.categoryId);
            this.orderId = TestDataAdder.addOrderTestDataAndReturnId(this.staffId, this.providerId, this.productId);
            this.orderDetailId = TestDataIdGetter.getIdOfOrderDetailTestData();
            this.receivingNoteId = TestDataAdder.addReceivingNoteTestDataAndReturnId(this.staffId, this.orderId);
            this.receivingNoteDetailId = TestDataIdGetter.getIdOfReceivingNoteDetailTestData();
            this.deliveryNoteId = TestDataAdder.addDeliveryNoteTestDataAndReturnId(this.productId);
            this.deliveryNoteDetailId = TestDataIdGetter.getIdOfDeliveryNoteDetailTestData();
            this.salesInvoiceId = TestDataAdder.addSalesInvoiceTestDataAndReturnId(this.staffId, this.customerId, this.deliveryNoteId);
            this.salesInvoiceDetailId = TestDataIdGetter.getIdOfSalesInvoiceTestData();

            setUpIsDone = true;
        }
    }

    @Test
    public void test1GetAllReceivingNotesByPeriodOfTime() throws IOException {
        String testURL = "receiving_notes?startDate=1998-01-01&endDate=2026-01-01&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<ReceivingNote> receivingNoteList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<ReceivingNote>>(){}.getType());

        int indexOfTestData = getIndexOfReceivingNoteTestDataInReturnList(receivingNoteList, this.receivingNoteId);
        ReceivingNote receivingNote = receivingNoteList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.receivingNoteDate, new SimpleDateFormat("yyyy-MM-dd").format(receivingNote.getDate()));
        Assert.assertEquals(this.staffId, receivingNote.getStaff().getId());
        Assert.assertEquals(this.orderId, receivingNote.getOrder().getId());

    }

    @Test
    public void test2GetAllDeliveryNotesByPeriodOfTime() throws IOException {
        String testURL = "delivery_notes?startDate=1998-01-01&endDate=2026-01-01&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<DeliveryNote> deliveryNoteList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<DeliveryNote>>(){}.getType());

        int indexOfTestData = getIndexOfDeliveryNoteTestDataInReturnList(deliveryNoteList, this.deliveryNoteId);
        DeliveryNote deliveryNote = deliveryNoteList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.deliveryNoteDate, new SimpleDateFormat("yyyy-MM-dd").format(deliveryNote.getDate()));
        Assert.assertEquals(this.productId, deliveryNote.getDeliveryNoteDetails().get(0).getProduct().getId());
        Assert.assertEquals(TestDataHelper.deliveryNoteDetailQuantity, deliveryNote.getDeliveryNoteDetails().get(0).getQuantity());
    }

    @Test
    public void test3GetAllSalesInvoiceByPeriodOfTime() throws IOException {
        String testURL = "sales_invoices?startDate=1998-01-01&endDate=2026-01-01&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<SalesInvoice> salesInvoiceList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<SalesInvoice>>(){}.getType());

        int indexOfTestData = getIndexOfSalesInvoiceTestDataInReturnList(salesInvoiceList, this.salesInvoiceId);
        SalesInvoice salesInvoice = salesInvoiceList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.salesInvoiceDate, new SimpleDateFormat("yyyy-MM-dd").format(salesInvoice.getDate()));
        Assert.assertEquals(this.staffId, salesInvoice.getStaff().getId());
        Assert.assertEquals(this.customerId, salesInvoice.getCustomer().getId());
    }

    @Test
    public void test4GetAllSalesInvoicesWithCustomerAndStaffInAPeriodOfTime() throws IOException{
        String testURL = "sales_invoices/" + this.customerId + "/" + this.staffId + "?startDate=1998-01-01&endDate=2026-01-01&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<SalesInvoice> salesInvoiceList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<SalesInvoice>>(){}.getType());

        int indexOfTestData = getIndexOfSalesInvoiceTestDataInReturnList(salesInvoiceList, this.salesInvoiceId);
        SalesInvoice salesInvoice = salesInvoiceList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.salesInvoiceDate, new SimpleDateFormat("yyyy-MM-dd").format(salesInvoice.getDate()));
        Assert.assertEquals(this.staffId, salesInvoice.getStaff().getId());
        Assert.assertEquals(this.customerId, salesInvoice.getCustomer().getId());
    }

    @Test
    public void test5GetRevenueInAPeriodOfTime() throws IOException{
        String testURL = "revenue?startDate=1998-01-01&endDate=2026-01-01";
        Gson gson = new Gson();
        Float revenueResult = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<Float>(){}.getType());

        Assert.assertEquals(TestDataHelper.productSellingPrice * TestDataHelper.deliveryNoteDetailQuantity, revenueResult, 0.1);
    }

    @Test
    public void test6GetRevenueByCustomerInAPeriodOfTime()throws IOException{
        String testURL = "revenue/by_customer/" + this.customerId + "?startDate=1998-01-01&endDate=2026-01-01";
        Gson gson = new Gson();
        Float revenueResult = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<Float>(){}.getType());

        Assert.assertEquals(TestDataHelper.productSellingPrice * TestDataHelper.deliveryNoteDetailQuantity, revenueResult, 0.1);
    }

    @Test
    public void test7GetRevenueByStaffInAPeriodOfTime()throws IOException{
        String testURL = "revenue/by_staff/" + this.staffId + "?startDate=1998-01-01&endDate=2026-01-01";
        Gson gson = new Gson();
        Float revenueResult = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<Float>(){}.getType());

        Assert.assertEquals(TestDataHelper.productSellingPrice * TestDataHelper.deliveryNoteDetailQuantity, revenueResult, 0.1);
    }

//    @Test
//    public void test8getInventoryStatus()throws IOException {
//        String testURL = "inventory?startDate=1998-01-01&endDate=2025-01-01";
//        Gson gson = new Gson();
//        List<InventoryNote> inventoryNoteList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
//                new TypeToken<List<InventoryNote>>(){}.getType());
//
//        System.out.println(inventoryNoteList.get(0).getProductId());
//        System.out.println(inventoryNoteList.get(0).getInventoryDetail().getProductName());
//        System.out.println(inventoryNoteList.get(0).getInventoryDetail().getReceived());
//        System.out.println(inventoryNoteList.get(0).getInventoryDetail().getDelivered());
//        System.out.println(inventoryNoteList.get(0).getInventoryDetail().getBalance());
//
//
//        int indexOfTestData = getIndexOfInventoryProductTestDataInReturnList(inventoryNoteList, this.productId);
//        InventoryNoteDetail inventoryNoteDetail = inventoryNoteList.get(indexOfTestData).getInventoryDetail();
//
//        Assert.assertEquals(TestDataHelper.deliveryNoteDetailQuantity, inventoryNoteDetail.getDelivered());
//        Assert.assertEquals(TestDataHelper.orderDetailQuantity, inventoryNoteDetail.getReceived());
//        Assert.assertEquals(TestDataHelper.orderDetailQuantity - TestDataHelper.deliveryNoteDetailQuantity, inventoryNoteDetail.getBalance());
//
//    }

    @Test
    public void test9ClearAllTestData() throws IOException {
        TestDataDeleter.deleteSalesInvoiceTestData(this.salesInvoiceId);
        TestDataDeleter.deleteDeliveryNoteTestData(this.deliveryNoteId);
        TestDataDeleter.deleteReceivingNoteTestData(this.receivingNoteId);
        TestDataDeleter.deleteOrderTestData(this.orderId);
        TestDataDeleter.deleteProductTestData(this.productId);
        TestDataDeleter.deleteCategoryTestData(this.categoryId);
        TestDataDeleter.deleteCustomerTestData(this.customerId);
        TestDataDeleter.deleteProviderTestData(this.providerId);
        TestDataDeleter.deleteStaffTestData(this.staffId);
    }

    private int getIndexOfReceivingNoteTestDataInReturnList(PaginatedList<ReceivingNote> receivingNoteList, int idOfTestData){
        for (int i = 0; i < receivingNoteList.getItems().size(); i++){
            if (receivingNoteList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

    private int getIndexOfDeliveryNoteTestDataInReturnList(PaginatedList<DeliveryNote> deliveryNoteList, int idOfTestData){
        for (int i = 0; i < deliveryNoteList.getItems().size(); i++){
            if (deliveryNoteList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

    private int getIndexOfSalesInvoiceTestDataInReturnList(PaginatedList<SalesInvoice> salesInvoiceList, int idOfTestData){
        for (int i = 0; i < salesInvoiceList.getItems().size(); i++){
            if (salesInvoiceList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

    private int getIndexOfInventoryProductTestDataInReturnList(List<InventoryNote> inventoryNoteList, int productId){
        for (int i = 0; i < inventoryNoteList.size(); i++){
            if (inventoryNoteList.get(i).getProductId() == productId)
                return i;
        }
        return 0;
    }

}
