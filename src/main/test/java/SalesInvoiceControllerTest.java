import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.SalesInvoice;
import helper.*;
import model.PaginatedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesInvoiceControllerTest {
    private int staffId;
    private int customerId;
    private int categoryId;
    private int productId;
    private int deliveryNoteId;
    private int deliveryNoteDetailId;
    private int salesInvoiceId;
    private static boolean setUpIsDone = false;

    @Before
    public void setup() throws IOException {
        if (setUpIsDone){
            this.staffId = TestDataIdGetter.getIdOfStaffTestData();
            this.customerId = TestDataIdGetter.getIdOfCustomerTestData();
            this.categoryId = TestDataIdGetter.getIdOfCategoryTestData();
            this.productId = TestDataIdGetter.getIdOfProductTestData();
            this.deliveryNoteId = TestDataIdGetter.getIdOfDeliveryNoteTestData();
            this.deliveryNoteDetailId = TestDataIdGetter.getIdOfDeliveryNoteDetailTestData();
            this.salesInvoiceId = TestDataIdGetter.getIdOfSalesInvoiceTestData();
        } else {
            this.staffId = TestDataAdder.addStaffTestDataAndReturnId();
            this.customerId = TestDataAdder.addCustomerTestDataAndReturnId();
            this.categoryId = TestDataAdder.addCategoryTestDataAndReturnId();
            this.productId = TestDataAdder.addProductTestDataAndReturnId(this.categoryId);
            this.deliveryNoteId = TestDataAdder.addDeliveryNoteTestDataAndReturnId(this.productId);
            this.deliveryNoteDetailId = TestDataIdGetter.getIdOfDeliveryNoteDetailTestData();
            setUpIsDone = true;
        }
    }

    @Test
    public void test1AddSalesInvoice() throws IOException{
        String testURL = "sales_invoices";
        JsonObject requestBody = TestDataHelper.createSalesInvoiceJSONObject(this.staffId, this.customerId, this.deliveryNoteId);
        Gson gson =  new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();;
        SalesInvoice salesInvoice = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(testURL, requestBody),
                new TypeToken<SalesInvoice>(){}.getType());

        Assert.assertEquals(TestDataHelper.salesInvoiceDate, new SimpleDateFormat("yyyy-MM-dd").format(salesInvoice.getDate()));
        Assert.assertEquals(this.staffId, salesInvoice.getStaff().getId());
        Assert.assertEquals(this.customerId, salesInvoice.getCustomer().getId());
    }

    @Test
    public void test2GetAllSalesInvoice() throws IOException{
        String testURL = "sales_invoices/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<SalesInvoice> salesInvoiceList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<SalesInvoice>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(salesInvoiceList, TestDataIdGetter.getIdOfReceivingNoteTestData());
        SalesInvoice salesInvoice = salesInvoiceList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.salesInvoiceDate, new SimpleDateFormat("yyyy-MM-dd").format(salesInvoice.getDate()));
        Assert.assertEquals(this.staffId, salesInvoice.getStaff().getId());
        Assert.assertEquals(this.customerId, salesInvoice.getCustomer().getId());
    }

    @Test
    public void test3SearchSalesInvoiceByDate() throws IOException {
        String testURL = "sales_invoices/by_date?date=" + TestDataHelper.salesInvoiceDate + "&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<SalesInvoice> salesInvoiceList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<SalesInvoice>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(salesInvoiceList, TestDataIdGetter.getIdOfReceivingNoteTestData());
        SalesInvoice salesInvoice = salesInvoiceList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.salesInvoiceDate, new SimpleDateFormat("yyyy-MM-dd").format(salesInvoice.getDate()));
        Assert.assertEquals(this.staffId, salesInvoice.getStaff().getId());
        Assert.assertEquals(this.customerId, salesInvoice.getCustomer().getId());
    }

    @Test
    public void test4UpdateSalesInvoice() throws IOException{
        String testURL = "sales_invoices";
        JsonObject requestBody = TestDataHelper.createUpdatedSalesInvoiceJSONObject(this.salesInvoiceId, this.staffId, this.customerId, this.deliveryNoteId
                                    , this.deliveryNoteDetailId, this.productId, this.categoryId);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        SalesInvoice salesInvoice = gson.fromJson(HTTPRequestMaker.executePutMethodAPI(testURL, requestBody),
                new TypeToken<SalesInvoice>(){}.getType());


        Assert.assertEquals(TestDataHelper.updatedSalesInvoiceDate, new SimpleDateFormat("yyyy-MM-dd").format(salesInvoice.getDate()));
        Assert.assertEquals(this.staffId, salesInvoice.getStaff().getId());
        Assert.assertEquals(this.customerId, salesInvoice.getCustomer().getId());
    }

    @Test
    public void test5DeleteSalesInvoice() throws IOException{
        String testURL = "sales_invoices/" + this.salesInvoiceId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(this.salesInvoiceId, id);

        TestDataDeleter.deleteDeliveryNoteTestData(this.deliveryNoteId);
        TestDataDeleter.deleteProductTestData(this.productId);
        TestDataDeleter.deleteCategoryTestData(this.categoryId);
        TestDataDeleter.deleteStaffTestData(this.staffId);
        TestDataDeleter.deleteCustomerTestData(this.customerId);
    }

    private int getIndexOfTestDataInReturnList(PaginatedList<SalesInvoice> salesInvoiceList, int idOfTestData){
        for (int i = 0; i < salesInvoiceList.getItems().size(); i++){
            if (salesInvoiceList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

}
