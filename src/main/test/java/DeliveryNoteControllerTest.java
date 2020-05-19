import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.DeliveryNote;
import helper.*;
import model.PaginatedList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeliveryNoteControllerTest {
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
            setUpIsDone = true;
        }
    }

    @Test
    public void test1AddDeliveryNote() throws IOException{
        String testURL = "delivery_notes";
        JsonObject requestBody = TestDataHelper.createDeliveryNoteJSONObject(this.productId);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        DeliveryNote deliveryNote = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(testURL, requestBody),
                new TypeToken<DeliveryNote>(){}.getType());

        Assert.assertEquals(TestDataHelper.deliveryNoteDate, new SimpleDateFormat("yyyy-MM-dd").format(deliveryNote.getDate()));
        Assert.assertEquals(this.productId, deliveryNote.getDeliveryNoteDetails().get(0).getProduct().getId());
        Assert.assertEquals(TestDataHelper.deliveryNoteDetailQuantity, deliveryNote.getDeliveryNoteDetails().get(0).getQuantity());
    }

    @Test
    public void test2GetAllDeliveryNotes() throws IOException{
        String testURL = "delivery_notes/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<DeliveryNote> deliveryNoteList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<DeliveryNote>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(deliveryNoteList, TestDataIdGetter.getIdOfOrderTestData());
        DeliveryNote deliveryNote = deliveryNoteList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.deliveryNoteDate, new SimpleDateFormat("yyyy-MM-dd").format(deliveryNote.getDate()));
        Assert.assertEquals(this.productId, deliveryNote.getDeliveryNoteDetails().get(0).getProduct().getId());
        Assert.assertEquals(TestDataHelper.deliveryNoteDetailQuantity, deliveryNote.getDeliveryNoteDetails().get(0).getQuantity());
    }

    @Test
    public void test3SearchDeliveryNoteByDate() throws IOException{
        String testURL = "delivery_notes/by_date?date=" + TestDataHelper.deliveryNoteDate + "&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<DeliveryNote> deliveryNoteList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<DeliveryNote>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(deliveryNoteList, TestDataIdGetter.getIdOfOrderTestData());
        DeliveryNote deliveryNote = deliveryNoteList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.deliveryNoteDate, new SimpleDateFormat("yyyy-MM-dd").format(deliveryNote.getDate()));
        Assert.assertEquals(this.productId, deliveryNote.getDeliveryNoteDetails().get(0).getProduct().getId());
        Assert.assertEquals(TestDataHelper.deliveryNoteDetailQuantity, deliveryNote.getDeliveryNoteDetails().get(0).getQuantity());
    }

    @Test
    public void test4UpdateDeliveryNote() throws IOException{
        String testURL = "delivery_notes";
        JsonObject requestBody = TestDataHelper.createUpdatedDeliveryNoteJSONObject(this.deliveryNoteId, this.deliveryNoteDetailId, productId, categoryId);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        DeliveryNote deliveryNote = gson.fromJson(HTTPRequestMaker.executePutMethodAPI(testURL, requestBody),
                new TypeToken<DeliveryNote>(){}.getType());

        System.out.println(this.deliveryNoteId);
        System.out.println(this.deliveryNoteDetailId);

        Assert.assertEquals(TestDataHelper.updatedDeliveryNoteDate, new SimpleDateFormat("yyyy-MM-dd").format(deliveryNote.getDate()));
        Assert.assertEquals(this.productId, deliveryNote.getDeliveryNoteDetails().get(0).getProduct().getId());
        Assert.assertEquals(TestDataHelper.updatedDeliveryNoteDetailQuantity, deliveryNote.getDeliveryNoteDetails().get(0).getQuantity());
    }


    @Test
    public void test5DeleteDeliveryNote() throws IOException{
        String testURL = "delivery_notes/" + this.deliveryNoteId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(this.deliveryNoteId, id);
        TestDataDeleter.deleteProductTestData(this.productId);
        TestDataDeleter.deleteCategoryTestData(this.categoryId);
    }

    private int getIndexOfTestDataInReturnList(PaginatedList<DeliveryNote> deliveryNoteList, int idOfTestData){
        for (int i = 0; i < deliveryNoteList.getItems().size(); i++){
            if (deliveryNoteList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

}
