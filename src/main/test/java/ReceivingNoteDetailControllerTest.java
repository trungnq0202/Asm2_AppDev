import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.OrderDetail;
import entity.ReceivingNoteDetail;
import helper.*;
import helper.pagination.PaginatedList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReceivingNoteDetailControllerTest {
    private int staffId;
    private int providerId;
    private int categoryId;
    private int productId;
    private int orderId;
    private int orderDetailId;
    private int receivingNoteId;
    private int receivingNoteDetailId;
    private static boolean setUpIsDone = false;

    @Before
    public void setup() throws IOException {
        if (setUpIsDone){
            this.staffId = TestDataIdGetter.getIdOfStaffTestData();
            this.providerId = TestDataIdGetter.getIdOfProviderTestData();
            this.categoryId = TestDataIdGetter.getIdOfCategoryTestData();
            this.productId = TestDataIdGetter.getIdOfProductTestData();
            this.orderId = TestDataIdGetter.getIdOfOrderTestData();
            this.orderDetailId = TestDataIdGetter.getIdOfOrderDetailTestData();
            this.receivingNoteId = TestDataIdGetter.getIdOfReceivingNoteTestData();
            this.receivingNoteDetailId = TestDataIdGetter.getIdOfReceivingNoteDetailTestData();
        } else {
            this.staffId = TestDataAdder.addStaffTestDataAndReturnId();
            this.providerId = TestDataAdder.addProviderTestDataAndReturnId();
            this.categoryId = TestDataAdder.addCategoryTestDataAndReturnId();
            this.productId = TestDataAdder.addProductTestDataAndReturnId(this.categoryId);
            this.orderId = TestDataAdder.addOrderTestDataAndReturnId(this.staffId, this.providerId, this.productId);
            this.orderDetailId = TestDataIdGetter.getIdOfOrderDetailTestData();
            this.receivingNoteId = TestDataAdder.addReceivingNoteTestDataAndReturnId(this.staffId, this.orderId);
            this.receivingNoteDetailId = TestDataIdGetter.getIdOfReceivingNoteDetailTestData();
            setUpIsDone = true;
        }
    }

    @Test
    public void test1GetAllReceivingNoteDetail() throws IOException{
        String testURL = "receiving_note_details/all?pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<ReceivingNoteDetail> receivingNoteDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<ReceivingNoteDetail>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(receivingNoteDetailList, this.receivingNoteDetailId);
        ReceivingNoteDetail receivingNoteDetail = receivingNoteDetailList.getItems().get(indexOfTestData);

        Assert.assertEquals(this.receivingNoteDetailId, receivingNoteDetail.getId());
        Assert.assertEquals(this.productId, receivingNoteDetail.getProduct().getId());
        Assert.assertEquals(TestDataHelper.orderDetailQuantity, receivingNoteDetail.getQuantity());

    }

    @Test
    public void test2DeleteReceivingNoteDetail() throws IOException{
        String testURL = "receiving_note_details/" + this.receivingNoteDetailId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(this.receivingNoteDetailId, id);

        TestDataDeleter.deleteReceivingNoteTestData(this.receivingNoteId);
        TestDataDeleter.deleteOrderTestData(this.orderId);
        TestDataDeleter.deleteProductTestData(this.productId);
        TestDataDeleter.deleteCategoryTestData(this.categoryId);
        TestDataDeleter.deleteProviderTestData(this.providerId);
        TestDataDeleter.deleteStaffTestData(this.staffId);
    }

    private int getIndexOfTestDataInReturnList(PaginatedList<ReceivingNoteDetail> receivingNoteDetailList, int idOfTestData){
        for (int i = 0; i < receivingNoteDetailList.getItems().size(); i++){
            if (receivingNoteDetailList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

}
