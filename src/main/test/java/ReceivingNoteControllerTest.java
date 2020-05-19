import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.ReceivingNote;
import helper.*;
import model.PaginatedList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReceivingNoteControllerTest {
    private int staffId;
    private int providerId;
    private int categoryId;
    private int productId;
    private int orderId;
    private int orderDetailId;
    private int receivingNoteId;
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
        } else {
            this.staffId = TestDataAdder.addStaffTestDataAndReturnId();
            this.providerId = TestDataAdder.addProviderTestDataAndReturnId();
            this.categoryId = TestDataAdder.addCategoryTestDataAndReturnId();
            this.productId = TestDataAdder.addProductTestDataAndReturnId(this.categoryId);
            this.orderId = TestDataAdder.addOrderTestDataAndReturnId(this.staffId, this.providerId, this.productId);
            this.orderDetailId = TestDataIdGetter.getIdOfOrderDetailTestData();
            setUpIsDone = true;
        }
    }

    @Test
    public void test1AddReceivingNote() throws IOException {
        String testURL = "receiving_notes";
        JsonObject requestBody = TestDataHelper.createReceivingNoteJSONObject(this.staffId, this.orderId);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        ReceivingNote receivingNote = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(testURL, requestBody),
                new TypeToken<ReceivingNote>(){}.getType());

        Assert.assertEquals(TestDataHelper.receivingNoteDate, new SimpleDateFormat("yyyy-MM-dd").format(receivingNote.getDate()));
        Assert.assertEquals(this.staffId, receivingNote.getStaff().getId());
        Assert.assertEquals(this.orderId, receivingNote.getOrder().getId());

    }

    @Test
    public void test2GetAllReceivingNotes() throws IOException{
        String testURL = "receiving_notes/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<ReceivingNote> receivingNoteList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<ReceivingNote>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(receivingNoteList, TestDataIdGetter.getIdOfReceivingNoteTestData());
        ReceivingNote receivingNote = receivingNoteList.getItems().get(indexOfTestData);
        Assert.assertEquals(TestDataHelper.receivingNoteDate, new SimpleDateFormat("yyyy-MM-dd").format(receivingNote.getDate()));
        Assert.assertEquals(this.staffId, receivingNote.getStaff().getId());
        Assert.assertEquals(this.orderId, receivingNote.getOrder().getId());
    }

    @Test
    public void test3SearchReceivingNoteByDate() throws IOException{
        String testURL = "receiving_notes/by_date?date=2021-01-03&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<ReceivingNote> receivingNoteList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<ReceivingNote>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(receivingNoteList, TestDataIdGetter.getIdOfReceivingNoteTestData());
        ReceivingNote receivingNote = receivingNoteList.getItems().get(indexOfTestData);
        Assert.assertEquals(TestDataHelper.receivingNoteDate, new SimpleDateFormat("yyyy-MM-dd").format(receivingNote.getDate()));
        Assert.assertEquals(this.staffId, receivingNote.getStaff().getId());
        Assert.assertEquals(this.orderId, receivingNote.getOrder().getId());
    }

    @Test
    public void test4UpdateReceivingNoteByDate() throws IOException{
        String testURL = "receiving_notes";
        JsonObject requestBody = TestDataHelper.createUpdatedReceivingNoteJSONObject(this.receivingNoteId, this.orderId,
                this.orderDetailId, this.staffId, this.providerId, this.productId, this.categoryId);

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        ReceivingNote receivingNote = gson.fromJson(HTTPRequestMaker.executePutMethodAPI(testURL, requestBody),
                new TypeToken<ReceivingNote>(){}.getType());

        Assert.assertEquals(TestDataHelper.updatedReceivingNoteDate, new SimpleDateFormat("yyyy-MM-dd").format(receivingNote.getDate()));
        Assert.assertEquals(this.staffId, receivingNote.getStaff().getId());
        Assert.assertEquals(this.orderId, receivingNote.getOrder().getId());
    }

    @Test
    public void test5DeleteReceivingNote() throws IOException {
        String testURL = "receiving_notes/" + this.receivingNoteId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(this.receivingNoteId, id);

        TestDataDeleter.deleteOrderTestData(this.orderId);
        TestDataDeleter.deleteProductTestData(this.productId);
        TestDataDeleter.deleteCategoryTestData(this.categoryId);
        TestDataDeleter.deleteStaffTestData(this.staffId);
        TestDataDeleter.deleteProviderTestData(this.providerId);

    }

    private int getIndexOfTestDataInReturnList(PaginatedList<ReceivingNote> receivingNoteList, int idOfTestData){
        for (int i = 0; i < receivingNoteList.getItems().size(); i++){
            if (receivingNoteList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

}
