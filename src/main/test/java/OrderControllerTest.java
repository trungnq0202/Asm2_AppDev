import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import entity.Order;
import helper.*;
import helper.pagination.PaginatedList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderControllerTest {
    private int staffId;
    private int providerId;
    private int categoryId;
    private int productId;
    private int orderId;
    private int orderDetailId;
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
        } else {
            this.staffId = TestDataAdder.addStaffTestDataAndReturnId();
            this.providerId = TestDataAdder.addProviderTestDataAndReturnId();
            this.categoryId = TestDataAdder.addCategoryTestDataAndReturnId();
            this.productId = TestDataAdder.addProductTestDataAndReturnId(this.categoryId);
            setUpIsDone = true;
        }
    }

    @Test
    public void test1AddOrder() throws IOException {
        String testURL = "orders";
        JsonObject requestBody = TestDataHelper.createOrderJSONObject(this.staffId, this.providerId, this.productId);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        Order order = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(testURL, requestBody),
                new TypeToken<Order>(){}.getType());

        Assert.assertEquals(TestDataHelper.orderDate, new SimpleDateFormat("yyyy-MM-dd").format(order.getDate()));
        Assert.assertEquals(this.staffId, order.getStaff().getId());
        Assert.assertEquals(this.providerId, order.getProvider().getId());
        Assert.assertEquals(TestDataHelper.orderDetailQuantity, order.getOrderDetails().get(0).getQuantity());
        Assert.assertEquals(TestDataHelper.orderDetailPrice, order.getOrderDetails().get(0).getPrice(), 0);

    }

    @Test
    public void test2GetAllOrders() throws IOException {
        //Get all Orders with pagination, 50  records at 1 page
        String testURL = "orders/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<Order> orderList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Order>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(orderList, TestDataIdGetter.getIdOfOrderTestData());
        Order order = orderList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.orderDate, new SimpleDateFormat("yyyy-MM-dd").format(order.getDate()));
        Assert.assertEquals(this.staffId, order.getStaff().getId());
        Assert.assertEquals(this.providerId, order.getProvider().getId());
        Assert.assertEquals(TestDataHelper.orderDetailQuantity, order.getOrderDetails().get(0).getQuantity());
        Assert.assertEquals(TestDataHelper.orderDetailPrice, order.getOrderDetails().get(0).getPrice(), 0);
    }

    @Test
    public void test3SearchOrderByDate() throws IOException {
        String testURL = "orders/by_date?date=2020-02-02&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<Order> orderList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Order>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(orderList, TestDataIdGetter.getIdOfOrderTestData());
        Order order = orderList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.orderDate, new SimpleDateFormat("yyyy-MM-dd").format(order.getDate()));
        Assert.assertEquals(this.staffId, order.getStaff().getId());
        Assert.assertEquals(this.providerId, order.getProvider().getId());
        Assert.assertEquals(this.providerId, order.getProvider().getId());
        Assert.assertEquals(TestDataHelper.orderDetailQuantity, order.getOrderDetails().get(0).getQuantity());
        Assert.assertEquals(TestDataHelper.orderDetailPrice, order.getOrderDetails().get(0).getPrice(), 0);
    }

    @Test
    public void test4UpdateOrder() throws IOException {
        String testURL = "orders";

        JsonObject requestBody = TestDataHelper.createUpdatedOrderJSONObject(this.orderId,
                this.orderDetailId, this.staffId, this.providerId, this.productId, this.categoryId);

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        Order order = gson.fromJson(HTTPRequestMaker.executePutMethodAPI(testURL, requestBody),
                new TypeToken<Order>(){}.getType());

        Assert.assertEquals(TestDataHelper.updatedOrderDate, new SimpleDateFormat("yyyy-MM-dd").format(order.getDate()));
        Assert.assertEquals(this.staffId, order.getStaff().getId());
        Assert.assertEquals(this.providerId, order.getProvider().getId());
        Assert.assertEquals(this.providerId, order.getProvider().getId());
        Assert.assertEquals(TestDataHelper.updatedOrderDetailQuantity, order.getOrderDetails().get(0).getQuantity());
        Assert.assertEquals(TestDataHelper.updatedOrderDetailPrice, order.getOrderDetails().get(0).getPrice(), 0);

    }

    @Test
    public void test5DeleteOrder() throws IOException {
        String testURL = "orders/" + this.orderId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(this.orderId, id);

        TestDataDeleter.deleteStaffTestData(this.staffId);
        TestDataDeleter.deleteProviderTestData(this.providerId);
        TestDataDeleter.deleteProductTestData(this.productId);
        TestDataDeleter.deleteCategoryTestData(this.categoryId);
    }


    private int getIndexOfTestDataInReturnList(PaginatedList<Order> orderList, int idOfTestData){
        for (int i = 0; i < orderList.getItems().size(); i++){
            if (orderList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

}
