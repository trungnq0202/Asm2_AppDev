package test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Provider;
import helper.pagination.PaginatedList;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProviderControllerTest {

    //Test "ADD Provider" API
    @Test
    public void test1AddProvider(){
        String testURL = "providers";
        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name","Mai Yen Vy");
        requestBody.put("address","68 Bach Van");
        requestBody.put("email","maiyenvy3001@gmail.com");
        requestBody.put("phone","01647309917");
        requestBody.put("fax","0123456789");
        requestBody.put("contact_person","Nguyen Duy Hieu");

        try{
            Gson gson = new Gson();
            Provider provider = gson.fromJson(TestConfig.executePostMethodAPI(testURL, requestBody),
                    new TypeToken<Provider>(){}.getType());

            Assert.assertEquals("Mai Yen Vy", provider.getName());
            Assert.assertEquals("68 Bach Van", provider.getAddress());
            Assert.assertEquals("maiyenvy3001@gmail.com", provider.getEmail());
            Assert.assertEquals("01647309917", provider.getPhone());
            Assert.assertEquals("0123456789", provider.getFax());
            Assert.assertEquals("Nguyen Duy Hieu", provider.getContact_person());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "GET all Providers" API
    @Test
    public void test2GetAllProviders(){

        //Get all staffs with pagination, 20 staffs record at 1 page
        String testURL = "providers/all?pageIndex=1&pageSize=20";

        try{
            Gson gson = new Gson();
            PaginatedList<Provider> providerList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Provider>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(providerList, getIdOfTestData());

            Assert.assertEquals("Mai Yen Vy", providerList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("68 Bach Van", providerList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("maiyenvy3001@gmail.com", providerList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("01647309917", providerList.getItems().get(indexOfTestData).getPhone());
            Assert.assertEquals("0123456789", providerList.getItems().get(indexOfTestData).getFax());
            Assert.assertEquals("Nguyen Duy Hieu", providerList.getItems().get(indexOfTestData).getContact_person());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Provider By name API"
    @Test
    public void test3SearchProviderByName(){
        String testURL = "providers/by_name?name=Mai%20Yen%20Vy&pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Provider> providerList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Provider>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(providerList, getIdOfTestData());

            Assert.assertEquals("Mai Yen Vy", providerList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("68 Bach Van", providerList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("maiyenvy3001@gmail.com", providerList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("01647309917", providerList.getItems().get(indexOfTestData).getPhone());
            Assert.assertEquals("0123456789", providerList.getItems().get(indexOfTestData).getFax());
            Assert.assertEquals("Nguyen Duy Hieu", providerList.getItems().get(indexOfTestData).getContact_person());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Provider By phone API"
    @Test
    public void test4SearchProviderByPhone(){
        String testURL = "providers/by_phone?phone=01647309917&pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Provider> providerList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Provider>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(providerList, getIdOfTestData());

            Assert.assertEquals("Mai Yen Vy", providerList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("68 Bach Van", providerList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("maiyenvy3001@gmail.com", providerList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("01647309917", providerList.getItems().get(indexOfTestData).getPhone());
            Assert.assertEquals("0123456789", providerList.getItems().get(indexOfTestData).getFax());
            Assert.assertEquals("Nguyen Duy Hieu", providerList.getItems().get(indexOfTestData).getContact_person());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Provider By address API"
    @Test
    public void test5SearchProviderByAddress(){
        String testURL = "providers/by_address?address=68%20Bach%20Van&pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Provider> providerList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Provider>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(providerList, getIdOfTestData());

            Assert.assertEquals("Mai Yen Vy", providerList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("68 Bach Van", providerList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("maiyenvy3001@gmail.com", providerList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("01647309917", providerList.getItems().get(indexOfTestData).getPhone());
            Assert.assertEquals("0123456789", providerList.getItems().get(indexOfTestData).getFax());
            Assert.assertEquals("Nguyen Duy Hieu", providerList.getItems().get(indexOfTestData).getContact_person());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Test "UPDATE Provider API"
    @Test
    public void test6UpdateProvider(){
        String testURL = "providers";
        int idOfTestData = getIdOfTestData();

        Map<String, Object> requestBody = new LinkedHashMap<>();

        requestBody.put("id", getIdOfTestData());
        requestBody.put("name","Nguyen Thi Nhu Quynh");
        requestBody.put("address","52 Ly Thai To");
        requestBody.put("email","ntnq@gmail.com");
        requestBody.put("phone","012475810135");
        requestBody.put("fax","9876543210");
        requestBody.put("contact_person","Nguyen Duy Khiem");

        try{
            Gson gson = new Gson();
            Provider provider = gson.fromJson(TestConfig.executePutMethodAPI(testURL, requestBody),
                    new TypeToken<Provider>(){}.getType());

            Assert.assertEquals(idOfTestData, provider.getId());
            Assert.assertEquals("Nguyen Thi Nhu Quynh", provider.getName());
            Assert.assertEquals("52 Ly Thai To", provider.getAddress());
            Assert.assertEquals("ntnq@gmail.com", provider.getEmail());
            Assert.assertEquals("012475810135", provider.getPhone());
            Assert.assertEquals("9876543210", provider.getFax());
            Assert.assertEquals("Nguyen Duy Khiem", provider.getContact_person());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Test "DELETE Provider API"
    @Test
    public void test7DeleteProvider(){
        int idOfTestData = getIdOfTestData();
        String testURL = "providers/" + idOfTestData;
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
        String testURL = "providers/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Provider> providerList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Provider>>(){}.getType());

            int maxId = -1;

            for (Provider provider: providerList.getItems())
                if (provider.getId() > maxId) maxId = provider.getId();

            return maxId;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int getIndexOfTestDataInReturnList(PaginatedList<Provider> providerList, int idOfTestData){
        for (int i = 0; i < providerList.getItems().size(); i++){
            if (providerList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }
}
