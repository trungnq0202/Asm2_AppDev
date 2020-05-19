import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.OrderDetail;
import helper.*;
import model.PaginatedList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDetailControllerTest {
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
            this.productId = TestDataIdGetter.getIdOfProductTestData() ;
            this.orderId = TestDataIdGetter.getIdOfOrderTestData();
            this.orderDetailId = TestDataIdGetter.getIdOfOrderDetailTestData() ;
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
    public void test1GetAllOrderDetail() throws IOException{
        String testURL = "order_details/all?pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<OrderDetail> orderDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<OrderDetail>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(orderDetailList, this.orderDetailId);
        OrderDetail orderDetail = orderDetailList.getItems().get(indexOfTestData);

        Assert.assertEquals(this.orderDetailId, orderDetail.getId());
        Assert.assertEquals(TestDataHelper.orderDetailQuantity, orderDetail.getQuantity());
        Assert.assertEquals(TestDataHelper.orderDetailPrice, orderDetail.getPrice(), 0);
        Assert.assertEquals(this.productId, orderDetail.getProduct().getId());
    }

    @Test
    public void test2DeleteOrderDetail() throws IOException {
        String testURL = "order_details/" + this.orderDetailId;

        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(this.orderDetailId, id);

        TestDataDeleter.deleteOrderTestData(this.orderId);
        TestDataDeleter.deleteProductTestData(this.productId);
        TestDataDeleter.deleteCategoryTestData(this.categoryId);
        TestDataDeleter.deleteProviderTestData(this.providerId);
        TestDataDeleter.deleteStaffTestData(this.staffId);
    }


    private int getIndexOfTestDataInReturnList(PaginatedList<OrderDetail> orderList, int idOfTestData){
    for (int i = 0; i < orderList.getItems().size(); i++){
        if (orderList.getItems().get(i).getId() == idOfTestData) {
            return i;
        }
    }
    return 0;
}
}
