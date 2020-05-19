import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.SalesInvoiceDetail;
import helper.*;
import model.PaginatedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesInvoiceDetailControllerTest {
    private int staffId;
    private int customerId;
    private int categoryId;
    private int productId;
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
            this.categoryId = TestDataIdGetter.getIdOfCategoryTestData();
            this.productId = TestDataIdGetter.getIdOfProductTestData();
            this.deliveryNoteId = TestDataIdGetter.getIdOfDeliveryNoteTestData();
            this.deliveryNoteDetailId = TestDataIdGetter.getIdOfDeliveryNoteDetailTestData();
            this.salesInvoiceId = TestDataIdGetter.getIdOfSalesInvoiceTestData();
            this.salesInvoiceDetailId = TestDataIdGetter.getIdOfSalesInvoiceDetailTestData();
        } else {
            this.staffId = TestDataAdder.addStaffTestDataAndReturnId();
            this.customerId = TestDataAdder.addCustomerTestDataAndReturnId();
            this.categoryId = TestDataAdder.addCategoryTestDataAndReturnId();
            this.productId = TestDataAdder.addProductTestDataAndReturnId(this.categoryId);
            this.deliveryNoteId = TestDataAdder.addDeliveryNoteTestDataAndReturnId(this.productId);
            this.deliveryNoteDetailId = TestDataIdGetter.getIdOfDeliveryNoteDetailTestData();
            this.salesInvoiceId = TestDataAdder.addSalesInvoiceTestDataAndReturnId(staffId, customerId, deliveryNoteId);
            this.salesInvoiceDetailId = TestDataIdGetter.getIdOfSalesInvoiceDetailTestData();
            setUpIsDone = true;
        }
    }

    @Test
    public void test1GetAllSalesInvoiceDetail() throws IOException{
        String testURL = "sales_invoice_details/all?pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<SalesInvoiceDetail> salesInvoiceDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<SalesInvoiceDetail>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(salesInvoiceDetailList, this.salesInvoiceDetailId);
        SalesInvoiceDetail salesInvoiceDetail = salesInvoiceDetailList.getItems().get(indexOfTestData);

        Assert.assertEquals(this.salesInvoiceDetailId, salesInvoiceDetail.getId());
        Assert.assertEquals(this.productId, salesInvoiceDetail.getProduct().getId());
        Assert.assertEquals(TestDataHelper.deliveryNoteDetailQuantity, salesInvoiceDetail.getQuantity());
        Assert.assertEquals(TestDataHelper.productSellingPrice, salesInvoiceDetail.getPrice(), 0);
        Assert.assertEquals(TestDataHelper.productSellingPrice * TestDataHelper.deliveryNoteDetailQuantity, salesInvoiceDetail.getTotalValue(), 0.1);

    }

    @Test
    public void test2DeleteSalesInvoiceDetail() throws IOException{
        String testURL = "sales_invoice_details/" + this.salesInvoiceDetailId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(this.salesInvoiceDetailId, id);

        TestDataDeleter.deleteSalesInvoiceTestData(this.salesInvoiceId);
        TestDataDeleter.deleteDeliveryNoteTestData(this.deliveryNoteId);
        TestDataDeleter.deleteProductTestData(this.productId);
        TestDataDeleter.deleteCategoryTestData(this.categoryId);
        TestDataDeleter.deleteStaffTestData(this.staffId);
        TestDataDeleter.deleteCustomerTestData(this.customerId);
    }

    private int getIndexOfTestDataInReturnList(PaginatedList<SalesInvoiceDetail> salesInvoiceDetailList, int idOfTestData){
        for (int i = 0; i < salesInvoiceDetailList.getItems().size(); i++){
            if (salesInvoiceDetailList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

}
