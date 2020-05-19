
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import entity.Customer;
import helper.HTTPRequestMaker;
import helper.TestDataHelper;
import helper.TestDataIdGetter;
import helper.pagination.PaginatedList;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerControllerTest {

    //Test "ADD Customer" API
    @Test
    public void test1AddCustomer() throws IOException {
        String testURL = "customers";
        JsonObject requestBody = TestDataHelper.createCustomerJSONObject();

        Gson gson = new Gson();
        Customer customer = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(testURL, requestBody),
                new TypeToken<Customer>(){}.getType());

        Assert.assertEquals(TestDataHelper.customerName, customer.getName());
        Assert.assertEquals(TestDataHelper.customerAddress, customer.getAddress());
        Assert.assertEquals(TestDataHelper.customerEmail, customer.getEmail());
        Assert.assertEquals(TestDataHelper.customerPhone, customer.getPhone());
        Assert.assertEquals(TestDataHelper.customerFax, customer.getFax());
        Assert.assertEquals(TestDataHelper.customerContactPerson, customer.getContact_person());

    }

    //Test "GET all Customers" API
    @Test
    public void test2GetAllCustomers() throws IOException {

        //Get all staffs with pagination, 20 staffs record at 1 page
        String testURL = "customers/all?pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Customer> customerList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Customer>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(customerList, TestDataIdGetter.getIdOfCustomerTestData());
        Customer customer = customerList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.customerName, customer.getName());
        Assert.assertEquals(TestDataHelper.customerAddress, customer.getAddress());
        Assert.assertEquals(TestDataHelper.customerEmail, customer.getEmail());
        Assert.assertEquals(TestDataHelper.customerPhone, customer.getPhone());
        Assert.assertEquals(TestDataHelper.customerFax, customer.getFax());
        Assert.assertEquals(TestDataHelper.customerContactPerson, customer.getContact_person());

    }

    //Test "Search Customer By name API"
    @Test
    public void test3SearchCustomerByName() throws IOException {
        String testURL = "customers/by_name?name=Vu%20Thi%20Bay&pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Customer> customerList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Customer>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(customerList, TestDataIdGetter.getIdOfCustomerTestData());
        Customer customer = customerList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.customerName, customer.getName());
        Assert.assertEquals(TestDataHelper.customerAddress, customer.getAddress());
        Assert.assertEquals(TestDataHelper.customerEmail, customer.getEmail());
        Assert.assertEquals(TestDataHelper.customerPhone, customer.getPhone());
        Assert.assertEquals(TestDataHelper.customerFax, customer.getFax());
        Assert.assertEquals(TestDataHelper.customerContactPerson, customer.getContact_person());
    }

    //Test "Search Customer By phone API"
    @Test
    public void test4SearchCustomerByPhone() throws IOException {
        String testURL = "customers/by_phone?phone=0979878774&pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Customer> customerList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Customer>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(customerList, TestDataIdGetter.getIdOfCustomerTestData());
        Customer customer = customerList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.customerName, customer.getName());
        Assert.assertEquals(TestDataHelper.customerAddress, customer.getAddress());
        Assert.assertEquals(TestDataHelper.customerEmail, customer.getEmail());
        Assert.assertEquals(TestDataHelper.customerPhone, customer.getPhone());
        Assert.assertEquals(TestDataHelper.customerFax, customer.getFax());
        Assert.assertEquals(TestDataHelper.customerContactPerson, customer.getContact_person());
    }

    //Test "Search Customer By address API"
    @Test
    public void test5SearchCustomerByAddress() throws IOException {
        String testURL = "customers/by_address?address=116%20Le%20Van%20Tho&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<Customer> customerList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Customer>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(customerList, TestDataIdGetter.getIdOfCustomerTestData());
        Customer customer = customerList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.customerName, customer.getName());
        Assert.assertEquals(TestDataHelper.customerAddress, customer.getAddress());
        Assert.assertEquals(TestDataHelper.customerEmail, customer.getEmail());
        Assert.assertEquals(TestDataHelper.customerPhone, customer.getPhone());
        Assert.assertEquals(TestDataHelper.customerFax, customer.getFax());
        Assert.assertEquals(TestDataHelper.customerContactPerson, customer.getContact_person());
    }

    //Test "UPDATE Customer API"
    @Test
    public void test6UpdateCustomer() throws IOException {
        String testURL = "customers";
        JsonObject requestBody = TestDataHelper.createUpdatedCustomerJSONObject(TestDataIdGetter.getIdOfCustomerTestData());

        Gson gson = new Gson();
        Customer customer = gson.fromJson(HTTPRequestMaker.executePutMethodAPI(testURL, requestBody),
                new TypeToken<Customer>(){}.getType());

        Assert.assertEquals(TestDataHelper.updatedCustomerName, customer.getName());
        Assert.assertEquals(TestDataHelper.updatedCustomerAddress, customer.getAddress());
        Assert.assertEquals(TestDataHelper.updatedCustomerEmail, customer.getEmail());
        Assert.assertEquals(TestDataHelper.updatedCustomerPhone, customer.getPhone());
        Assert.assertEquals(TestDataHelper.updatedCustomerFax, customer.getFax());
        Assert.assertEquals(TestDataHelper.updatedCustomerContactPerson, customer.getContact_person());
    }

    //Test "DELETE Customer API"
    @Test
    public void test7DeleteCustomer() throws IOException {
        int idOfTestData = TestDataIdGetter.getIdOfCustomerTestData();
        String testURL = "customers/" + idOfTestData;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(idOfTestData, id);

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
