import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import model.Product;
import helper.*;
import model.PaginatedList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {
    private int categoryId;
    private int productId;
    private static boolean setUpIsDone = false;

    @Before
    public void setup() throws IOException {
        if (setUpIsDone){
            this.categoryId = TestDataIdGetter.getIdOfCategoryTestData();
            this.productId = TestDataIdGetter.getIdOfProductTestData();
        } else {
            this.categoryId = TestDataAdder.addCategoryTestDataAndReturnId();
            setUpIsDone = true;
        }
    }

    //Test "ADD Product" API
    @Test
    public void test1AddProduct() throws IOException {
        String testURL = "products";
        JsonObject requestBody = TestDataHelper.createProductJSONObject(this.categoryId);

        Gson gson = new Gson();
        Product product = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(testURL, requestBody),
                new TypeToken<Product>(){}.getType());

        Assert.assertEquals(TestDataHelper.productName, product.getName());
        Assert.assertEquals(TestDataHelper.productModel, product.getModel());
        Assert.assertEquals(TestDataHelper.productBrand, product.getBrand());
        Assert.assertEquals(TestDataHelper.productCompany, product.getCompany());
        Assert.assertEquals(TestDataHelper.productDescription, product.getDescription());
        Assert.assertEquals(TestDataHelper.productSellingPrice, product.getSelling_price(), 0);

    }

    //Test "GET all Products" API
    @Test
    public void test2GetAllProducts() throws IOException {
        //Get all staffs with pagination, 20 staffs record at 1 page
        String testURL = "products/all?pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Product> productList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Product>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(productList, TestDataIdGetter.getIdOfProductTestData());
        Product product = productList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.productName, product.getName());
        Assert.assertEquals(TestDataHelper.productModel, product.getModel());
        Assert.assertEquals(TestDataHelper.productBrand, product.getBrand());
        Assert.assertEquals(TestDataHelper.productCompany, product.getCompany());
        Assert.assertEquals(TestDataHelper.productDescription, product.getDescription());
        Assert.assertEquals(TestDataHelper.productSellingPrice, product.getSelling_price(), 0);
    }

    //Test "Search Product By name API"
    @Test
    public void test3SearchProductByName() throws IOException {
        String testURL = "products/by_name?name=Macbook%20Pro%2016%20inch&pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Product> productList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Product>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(productList, TestDataIdGetter.getIdOfProductTestData());
        Product product = productList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.productName, product.getName());
        Assert.assertEquals(TestDataHelper.productModel, product.getModel());
        Assert.assertEquals(TestDataHelper.productBrand, product.getBrand());
        Assert.assertEquals(TestDataHelper.productCompany, product.getCompany());
        Assert.assertEquals(TestDataHelper.productDescription, product.getDescription());
        Assert.assertEquals(TestDataHelper.productSellingPrice, product.getSelling_price(), 0);

    }

    //Test "Search Product By brand API"
    @Test
    public void test4SearchProductByBrand() throws IOException {
        String testURL = "products/by_brand?brand=Apple&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<Product> productList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Product>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(productList, TestDataIdGetter.getIdOfProductTestData());
        Product product = productList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.productName, product.getName());
        Assert.assertEquals(TestDataHelper.productModel, product.getModel());
        Assert.assertEquals(TestDataHelper.productBrand, product.getBrand());
        Assert.assertEquals(TestDataHelper.productCompany, product.getCompany());
        Assert.assertEquals(TestDataHelper.productDescription, product.getDescription());
        Assert.assertEquals(TestDataHelper.productSellingPrice, product.getSelling_price(), 0);
    }

    //Test "UPDATE Product API"
    @Test
    public void test5UpdateProduct() throws IOException {
        String testURL = "products";
        JsonObject requestBody = TestDataHelper.createUpdatedProductJSONObject(this.productId, this.categoryId);

        Gson gson = new Gson();
        Product product = gson.fromJson(HTTPRequestMaker.executePutMethodAPI(testURL, requestBody),
                new TypeToken<Product>(){}.getType());

        Assert.assertEquals(TestDataHelper.updatedProductName, product.getName());
        Assert.assertEquals(TestDataHelper.updatedProductModel, product.getModel());
        Assert.assertEquals(TestDataHelper.updatedProductBrand, product.getBrand());
        Assert.assertEquals(TestDataHelper.updatedProductCompany, product.getCompany());
        Assert.assertEquals(TestDataHelper.updatedProductDescription, product.getDescription());
        Assert.assertEquals(TestDataHelper.updatedProductSellingPrice, product.getSelling_price(), 0);

    }

    //Test "DELETE Product API"
    @Test
    public void test6DeleteProduct() throws IOException {
        String testURL = "products/" + this.productId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(this.productId, id);

        TestDataDeleter.deleteCategoryTestData(this.categoryId);
    }


    private int getIndexOfTestDataInReturnList(PaginatedList<Product> productList, int idOfTestData){
        for (int i = 0; i < productList.getItems().size(); i++){
            if (productList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }
}
