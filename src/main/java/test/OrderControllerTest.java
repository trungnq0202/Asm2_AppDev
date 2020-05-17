package test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import entity.*;
import helper.pagination.PaginatedList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderControllerTest {
    private JsonObject staff;
    private JsonObject provider;
    private List<JsonObject> categoryList;
    private List<JsonObject> productList;
    private List<JsonObject> orderDetails;

    private int staffId;
    private int providerId;
    private List<Integer> categoryListId;
    private List<Integer> productListId;
    private List<Integer> orderDetailsId;

    private int addStaffToDatabaseAndReturnObject() throws IOException {
        //Add a staff to the database
        String addStaffURL = "staffs";
        JsonObject requestBodyStaff = new JsonObject();
        requestBodyStaff.addProperty("name","Ngo Quang Trung");
        requestBodyStaff.addProperty("address","235 Nguyen Van Cu");
        requestBodyStaff.addProperty("email","nqtrung02022000@gmail.com");
        requestBodyStaff.addProperty("phone","0703300037");

        Gson gson = new Gson();
        Staff staff = gson.fromJson(TestConfig.executePostMethodAPI(addStaffURL, requestBodyStaff),
                new TypeToken<Staff>(){}.getType());

        this.staff = requestBodyStaff;

        return staff.getId();
    }

    private int addProviderToDatabaseAndReturnObject() throws IOException {
        //Add a providere to the database
        String addProviderURL = "providers";
        JsonObject requestBodyProvider = new JsonObject();
        requestBodyProvider.addProperty("name","Mai Yen Vy");
        requestBodyProvider.addProperty("address","68 Bach Van");
        requestBodyProvider.addProperty("email","maiyenvy3001@gmail.com");
        requestBodyProvider.addProperty("phone","01647309917");
        requestBodyProvider.addProperty("fax","0123456789");
        requestBodyProvider.addProperty("contact_person","Nguyen Duy Hieu");

        Gson gson = new Gson();
        Provider provider = gson.fromJson(TestConfig.executePostMethodAPI(addProviderURL, requestBodyProvider),
                new TypeToken<Provider>(){}.getType());

        this.provider = requestBodyProvider;

        return provider.getId();
    }

    private List<Integer> addCategoriesToDatabaseAndReturnList() throws IOException{
        List<Integer> categoryList = new ArrayList<>();
        String addCategoryURL = "categories";

        JsonObject requestBodyCategory1 = new JsonObject();
        requestBodyCategory1.addProperty("name","Apple Laptop");
        Gson gson = new Gson();
        Category category1 = gson.fromJson(TestConfig.executePostMethodAPI(addCategoryURL, requestBodyCategory1),
                new TypeToken<Category>(){}.getType());
        categoryList.add(category1.getId());

        this.categoryList.add(requestBodyCategory1);

        JsonObject requestBodyCategory2 = new JsonObject();
        requestBodyCategory2.addProperty("name","Apple Iphone");
        Category category2 = gson.fromJson(TestConfig.executePostMethodAPI(addCategoryURL, requestBodyCategory2),
                new TypeToken<Category>(){}.getType());
        categoryList.add(category2.getId());

        this.categoryList.add(requestBodyCategory2);

        return categoryList;
    }

    private List<Integer> addProductsToDatabaseAndReturnList(List<Integer> categoryListId) throws IOException {
        List<Integer> productList = new ArrayList<>();

        //Add first product
        String addProductURL = "products";
        JsonObject requestBodyProduct1 = new JsonObject();
        requestBodyProduct1.addProperty("name","Macbook Pro 15inch");
        requestBodyProduct1.addProperty("model","Macbook Pro");
        requestBodyProduct1.addProperty("brand","Apple");
        requestBodyProduct1.addProperty("company","Apple corporation");
        requestBodyProduct1.addProperty("description","Designed for those who defy " +
                "limits and change the world, the new MacBook Pro is by " +
                "far the most powerful notebook we’ve ever made. ");
        requestBodyProduct1.addProperty("sellingPrice",3000);

        //Create category json element
        JsonObject category1 = new JsonObject();
        category1.addProperty("id", categoryListId.get(0));

        requestBodyProduct1.add("category", category1);

        Gson gson = new Gson();
        Product product1 = gson.fromJson(TestConfig.executePostMethodAPI(addProductURL, requestBodyProduct1),
                new TypeToken<Product>(){}.getType());
        productList.add(product1.getId());

        this.productList.add(requestBodyProduct1);

        //Add 2nd product
        JsonObject requestBodyProduct2 = new JsonObject();
        requestBodyProduct2.addProperty("name","Iphone X");
        requestBodyProduct2.addProperty("model","Iphone");
        requestBodyProduct2.addProperty("brand","Apple");
        requestBodyProduct2.addProperty("company","Apple corporation");
        requestBodyProduct2.addProperty("description","Welcome to the first iPhone powerful enough to be called Pro.");
        requestBodyProduct2.addProperty("sellingPrice",679);

        //Create category json element
        JsonObject category2 = new JsonObject();
        category2.addProperty("id", categoryListId.get(1));
        requestBodyProduct2.add("category", category2);

        Product product2 = gson.fromJson(TestConfig.executePostMethodAPI(addProductURL, requestBodyProduct2),
                new TypeToken<Product>(){}.getType());
        productList.add(product2.getId());

        this.productList.add(requestBodyProduct2);


        return productList;
    }

    private List<OrderDetail> prepareOrderDetailList(List<Product> productList) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail(0, null, 12, 2900, productList.get(0));
        OrderDetail orderDetail2 = new OrderDetail(1, null, 12, 670, productList.get(1));
        orderDetails.add(orderDetail1); orderDetails.add(orderDetail2);

        List<JsonObject> cc = new ArrayList<>();
        JsonObject cl = new JsonObject();
        cl.add("cdada", (JsonElement) cc);
        return orderDetails;
    }

    @Before
    public void setup() throws IOException, NoSuchFieldException {
        this.staffId = addStaffToDatabaseAndReturnObject();
        this.providerId = addProviderToDatabaseAndReturnObject();
        this.categoryListId = addCategoriesToDatabaseAndReturnList();
        this.productListId = addProductsToDatabaseAndReturnList(this.categoryListId);
//        this.orderDetails = prepareOrderDetailList(this.productList);
    }

    //Test "ADD Order" API
    @Test
    public void test1AddOrder() throws IOException {
//        String testURL = "orders";
//        Map<String, Object> requestBody = new LinkedHashMap<>();
//        requestBody.put("date","2019-02-03");
//        requestBody.put("staff", this.staff);
//        requestBody.put("provider", this.provider);
//        requestBody.put("orderDetails", this.orderDetails);
//
//        try{
//            Gson gson = new Gson();
//            Order order = gson.fromJson(TestConfig.executePostMethodAPI(testURL, requestBody),
//                    new TypeToken<Order>(){}.getType());
//
//            Assert.assertEquals("2019-02-03", order.getDate().toString());
//            Assert.assertEquals(this.staff, order.getStaff());
//            Assert.assertEquals(this.provider, order.getProvider());
//            Assert.assertEquals(this.orderDetails, order.getOrderDetails());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @After
    public void clearAllTestDataAddedToDatabase(){

    }

    private int getIdOfTestData(){
        String testURL = "orders/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Order> orderList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Order>>(){}.getType());

            int maxId = -1;

            for (Order order: orderList.getItems())
                if (order.getId() > maxId) maxId = order.getId();

            return maxId;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
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
