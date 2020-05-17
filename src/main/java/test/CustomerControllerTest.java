package test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Customer;
import helper.pagination.PaginatedList;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerControllerTest {
    //Test "ADD Customer" API
    @Test
    public void test1AddCustomer(){
        String testURL = "customers";
        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name","Vu Thi Bay");
        requestBody.put("address","116 Le Van Tho");
        requestBody.put("email","vuthibay@gmail.com");
        requestBody.put("phone","0979878774");

        try{
            Gson gson = new Gson();
            Customer customer = gson.fromJson(TestConfig.executePostMethodAPI(testURL, requestBody),
                    new TypeToken<Customer>(){}.getType());

            Assert.assertEquals("Vu Thi Bay", customer.getName());
            Assert.assertEquals("116 Le Van Tho", customer.getAddress());
            Assert.assertEquals("vuthibay@gmail.com", customer.getEmail());
            Assert.assertEquals("0979878774", customer.getPhone());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "GET all Customers" API
    @Test
    public void test2GetAllCustomers(){

        //Get all staffs with pagination, 20 staffs record at 1 page
        String testURL = "customers/all?pageIndex=1&pageSize=20";

        try{
            Gson gson = new Gson();
            PaginatedList<Customer> customerList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Customer>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(customerList, getIdOfTestData());

            Assert.assertEquals("Vu Thi Bay", customerList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("116 Le Van Tho", customerList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("vuthibay@gmail.com", customerList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("0979878774", customerList.getItems().get(indexOfTestData).getPhone());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Customer By name API"
    @Test
    public void test3SearchCustomerByName(){
        String testURL = "customers/by_name?name=Vu%20Thi%20Bay&pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Customer> customerList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Customer>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(customerList, getIdOfTestData());

            Assert.assertEquals("Vu Thi Bay", customerList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("116 Le Van Tho", customerList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("vuthibay@gmail.com", customerList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("0979878774", customerList.getItems().get(indexOfTestData).getPhone());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Customer By phone API"
    @Test
    public void test4SearchCustomerByPhone(){
        String testURL = "customers/by_phone?phone=0979878774&pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Customer> customerList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Customer>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(customerList, getIdOfTestData());

            Assert.assertEquals("Vu Thi Bay", customerList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("116 Le Van Tho", customerList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("vuthibay@gmail.com", customerList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("0979878774", customerList.getItems().get(indexOfTestData).getPhone());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Customer By address API"
    @Test
    public void test5SearchCustomerByAddress(){
        String testURL = "customers/by_address?address=116%20Le%20Van%20Tho&pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Customer> customerList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Customer>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(customerList, getIdOfTestData());

            Assert.assertEquals("Vu Thi Bay", customerList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("116 Le Van Tho", customerList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("vuthibay@gmail.com", customerList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("0979878774", customerList.getItems().get(indexOfTestData).getPhone());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Test "UPDATE Customer API"
    @Test
    public void test6UpdateCustomer(){
        String testURL = "customers";
        int idOfTestData = getIdOfTestData();

        Map<String, Object> requestBody = new LinkedHashMap<>();

        requestBody.put("id", getIdOfTestData());
        requestBody.put("name","Ngo Thuy Linh");
        requestBody.put("address","169 Dinh Tien hoang");
        requestBody.put("email","ngothuylinh@gmail.com");
        requestBody.put("phone","01687944515");

        try{
            Gson gson = new Gson();
            Customer customer = gson.fromJson(TestConfig.executePutMethodAPI(testURL, requestBody),
                    new TypeToken<Customer>(){}.getType());

            Assert.assertEquals(idOfTestData, customer.getId());
            Assert.assertEquals("Ngo Thuy Linh", customer.getName());
            Assert.assertEquals("123 Ly Thai To", customer.getAddress());
            Assert.assertEquals("ngothuylinh@gmail.com", customer.getEmail());
            Assert.assertEquals("01687944515", customer.getPhone());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Test "DELETE Customer API"

    @Test
    public void test7DeleteCustomer(){
        int idOfTestData = getIdOfTestData();
        String testURL = "customers/" + idOfTestData;
        try{
            Gson gson = new Gson();
            int id = gson.fromJson(TestConfig.executeDeleteMethodAPI(testURL),
                    new TypeToken<Integer>(){}.getType());

            Assert.assertEquals(idOfTestData, id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int getIdOfTestData(){
        String testURL = "customers/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Customer> customerList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Customer>>(){}.getType());

            int maxId = -1;

            for (Customer customer: customerList.getItems())
                if (customer.getId() > maxId) maxId = customer.getId();

            return maxId;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int getIndexOfTestDataInReturnList(PaginatedList<Customer> customerList, int idOfTestData){
        for (int i = 0; i < customerList.getItems().size(); i++){
            if (customerList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }
}
