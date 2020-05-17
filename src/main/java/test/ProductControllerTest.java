package test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entity.Product;
import entity.Staff;
import helper.pagination.PaginatedList;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

    //Test "ADD Product" API
    @Test
    public void test1AddProduct(){
        String testURL = "products";
        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name","");
        requestBody.put("model","");
        requestBody.put("brand","");
        requestBody.put("company","");
        requestBody.put("description","");
        requestBody.put("sellingPrice","");

        try{
            Gson gson = new Gson();
            Product product = gson.fromJson(TestConfig.executePostMethodAPI(testURL, requestBody),
                    new TypeToken<Product>(){}.getType());

            Assert.assertEquals("", product.getName());
            Assert.assertEquals("", product.getModel());
            Assert.assertEquals("", product.getBrand());
            Assert.assertEquals("", product.getCompany());
            Assert.assertEquals("", product.getDescription());
            Assert.assertEquals(5.6, product.getSelling_price(), 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "GET all Products" API
    @Test
    public void test2GetAllProducts(){

        //Get all staffs with pagination, 20 staffs record at 1 page
        String testURL = "products/all?pageIndex=1&pageSize=20";

        try{
            Gson gson = new Gson();
            PaginatedList<Product> productList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Product>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(productList, getIdOfTestData());

            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getModel());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getBrand());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getCompany());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getDescription());
            Assert.assertEquals(0, productList.getItems().get(indexOfTestData).getSelling_price(), 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Product By name API"
    @Test
    public void test3SearchProductByName(){
        String testURL = "products/by_name?name=        pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Product> productList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Product>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(productList, getIdOfTestData());

            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getModel());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getBrand());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getCompany());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getDescription());
            Assert.assertEquals(0, productList.getItems().get(indexOfTestData).getSelling_price(), 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Product By brand API"
    @Test
    public void test4SearchProductByBrand(){
        String testURL = "products/by_brand?brand=        pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Product> productList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Product>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(productList, getIdOfTestData());

            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getModel());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getBrand());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getCompany());
            Assert.assertEquals("", productList.getItems().get(indexOfTestData).getDescription());
            Assert.assertEquals(0, productList.getItems().get(indexOfTestData).getSelling_price(), 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Test "UPDATE Product API"
    @Test
    public void test5UpdateProduct(){
        String testURL = "products";
        int idOfTestData = getIdOfTestData();

        Map<String, Object> requestBody = new LinkedHashMap<>();

        requestBody.put("name","");
        requestBody.put("model","");
        requestBody.put("brand","");
        requestBody.put("company","");
        requestBody.put("description","");
        requestBody.put("sellingPrice","");

        try{
            Gson gson = new Gson();
            Product product = gson.fromJson(TestConfig.executePutMethodAPI(testURL, requestBody),
                    new TypeToken<Staff>(){}.getType());

            Assert.assertEquals("", product.getName());
            Assert.assertEquals("", product.getModel());
            Assert.assertEquals("", product.getBrand());
            Assert.assertEquals("", product.getCompany());
            Assert.assertEquals("", product.getDescription());
            Assert.assertEquals(0, product.getSelling_price(), 0);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private int getIdOfTestData(){
        String testURL = "products/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Product> productList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Product>>(){}.getType());

            int maxId = -1;

            for (Product product: productList.getItems())
                if (product.getId() > maxId) maxId = product.getId();

            return maxId;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
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
