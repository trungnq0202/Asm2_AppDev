import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.DeliveryNoteDetail;
import entity.ReceivingNoteDetail;
import helper.*;
import helper.pagination.PaginatedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeliveryNoteDetailController {
    private int categoryId;
    private int productId;
    private int deliveryNoteId;
    private int deliveryNoteDetailId;
    private static boolean setUpIsDone = false;

    @Before
    public void setup() throws IOException {
        if (setUpIsDone){
            this.categoryId = TestDataIdGetter.getIdOfCategoryTestData();
            this.productId = TestDataIdGetter.getIdOfProductTestData();
            this.deliveryNoteId = TestDataIdGetter.getIdOfDeliveryNoteTestData();
            this.deliveryNoteDetailId = TestDataIdGetter.getIdOfDeliveryNoteDetailTestData();
        } else {
            this.categoryId = TestDataAdder.addCategoryTestDataAndReturnId();
            this.productId = TestDataAdder.addProductTestDataAndReturnId(this.categoryId);
            this.deliveryNoteId = TestDataAdder.addDeliveryNoteTestDataAndReturnId(this.productId);
            this.deliveryNoteDetailId = TestDataIdGetter.getIdOfDeliveryNoteDetailTestData();
            setUpIsDone = true;
        }
    }

    @Test
    public void test1GetAllDeliveryNoteDetail() throws IOException{
        String testURL = "delivery_note_details/all?pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<DeliveryNoteDetail> deliveryNoteDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<DeliveryNoteDetail>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(deliveryNoteDetailList, this.deliveryNoteDetailId);
        DeliveryNoteDetail deliveryNoteDetail = deliveryNoteDetailList.getItems().get(indexOfTestData);

        Assert.assertEquals(this.deliveryNoteDetailId, deliveryNoteDetail.getId());
        Assert.assertEquals(this.productId, deliveryNoteDetail.getProduct().getId());
        Assert.assertEquals(TestDataHelper.deliveryNoteDetailQuantity, deliveryNoteDetail.getQuantity());
    }

    @Test
    public void test2DeleteDeliveryNoteDetail() throws IOException {
        String testURL = "delivery_note_details/" + this.deliveryNoteDetailId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(this.deliveryNoteDetailId, id);
        TestDataDeleter.deleteDeliveryNoteTestData(this.deliveryNoteId);
        TestDataDeleter.deleteProductTestData(this.productId);
        TestDataDeleter.deleteCategoryTestData(this.categoryId);
    }

    private int getIndexOfTestDataInReturnList(PaginatedList<DeliveryNoteDetail> deliveryNoteDetailList, int idOfTestData){
        for (int i = 0; i < deliveryNoteDetailList.getItems().size(); i++){
            if (deliveryNoteDetailList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

}
