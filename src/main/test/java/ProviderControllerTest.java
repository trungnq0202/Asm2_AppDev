
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.Provider;
import helper.HTTPRequestMaker;
import helper.TestDataHelper;
import helper.TestDataIdGetter;
import model.PaginatedList;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProviderControllerTest {

    //Test "ADD Provider" API
    @Test
    public void test1AddProvider() throws IOException {
        String testURL = "providers";
        JsonObject requestBody = TestDataHelper.createProviderJSONObject();

        Gson gson = new Gson();
        Provider provider = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(testURL, requestBody),
                    new TypeToken<Provider>(){}.getType());

        Assert.assertEquals(TestDataHelper.providerName, provider.getName());
        Assert.assertEquals(TestDataHelper.providerAddress, provider.getAddress());
        Assert.assertEquals(TestDataHelper.providerEmail, provider.getEmail());
        Assert.assertEquals(TestDataHelper.providerPhone, provider.getPhone());
        Assert.assertEquals(TestDataHelper.providerFax, provider.getFax());
        Assert.assertEquals(TestDataHelper.providerContactPerson, provider.getContact_person());

    }

    //Test "GET all Providers" API
    @Test
    public void test2GetAllProviders() throws IOException {
        //Get all Providers with pagination, 20 staffs record at 1 page
        String testURL = "providers/all?pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Provider> providerList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Provider>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(providerList, TestDataIdGetter.getIdOfProviderTestData());
        Provider provider = providerList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.providerName, provider.getName());
        Assert.assertEquals(TestDataHelper.providerAddress, provider.getAddress());
        Assert.assertEquals(TestDataHelper.providerEmail, provider.getEmail());
        Assert.assertEquals(TestDataHelper.providerPhone, provider.getPhone());
        Assert.assertEquals(TestDataHelper.providerFax, provider.getFax());
        Assert.assertEquals(TestDataHelper.providerContactPerson, provider.getContact_person());

    }

    //Test "Search Provider By name API"
    @Test
    public void test3SearchProviderByName() throws IOException {
        String testURL = "providers/by_name?name=Mai%20Yen%20Vy&pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Provider> providerList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Provider>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(providerList, TestDataIdGetter.getIdOfProviderTestData());
        Provider provider = providerList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.providerName, provider.getName());
        Assert.assertEquals(TestDataHelper.providerAddress, provider.getAddress());
        Assert.assertEquals(TestDataHelper.providerEmail, provider.getEmail());
        Assert.assertEquals(TestDataHelper.providerPhone, provider.getPhone());
        Assert.assertEquals(TestDataHelper.providerFax, provider.getFax());
        Assert.assertEquals(TestDataHelper.providerContactPerson, provider.getContact_person());
    }

    //Test "Search Provider By phone API"
    @Test
    public void test4SearchProviderByPhone() throws IOException {
        String testURL = "providers/by_phone?phone=01647309917&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<Provider> providerList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Provider>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(providerList, TestDataIdGetter.getIdOfProviderTestData());
        Provider provider = providerList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.providerName, provider.getName());
        Assert.assertEquals(TestDataHelper.providerAddress, provider.getAddress());
        Assert.assertEquals(TestDataHelper.providerEmail, provider.getEmail());
        Assert.assertEquals(TestDataHelper.providerPhone, provider.getPhone());
        Assert.assertEquals(TestDataHelper.providerFax, provider.getFax());
        Assert.assertEquals(TestDataHelper.providerContactPerson, provider.getContact_person());
    }

    //Test "Search Provider By address API"
    @Test
    public void test5SearchProviderByAddress() throws IOException {
        String testURL = "providers/by_address?address=68%20Bach%20Van&pageIndex=1&pageSize=20";
        Gson gson = new Gson();
        PaginatedList<Provider> providerList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Provider>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(providerList, TestDataIdGetter.getIdOfProviderTestData());
        Provider provider = providerList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.providerName, provider.getName());
        Assert.assertEquals(TestDataHelper.providerAddress, provider.getAddress());
        Assert.assertEquals(TestDataHelper.providerEmail, provider.getEmail());
        Assert.assertEquals(TestDataHelper.providerPhone, provider.getPhone());
        Assert.assertEquals(TestDataHelper.providerFax, provider.getFax());
        Assert.assertEquals(TestDataHelper.providerContactPerson, provider.getContact_person());

    }


    //Test "UPDATE Provider API"
    @Test
    public void test6UpdateProvider() throws IOException {
        String testURL = "providers";

        JsonObject requestBody = TestDataHelper.createUpdatedProviderJSONObject(TestDataIdGetter.getIdOfProviderTestData());

        Gson gson = new Gson();
        Provider provider = gson.fromJson(HTTPRequestMaker.executePutMethodAPI(testURL, requestBody),
                new TypeToken<Provider>(){}.getType());

        Assert.assertEquals(TestDataHelper.updatedProviderName, provider.getName());
        Assert.assertEquals(TestDataHelper.updatedProviderAddress, provider.getAddress());
        Assert.assertEquals(TestDataHelper.updatedProviderEmail, provider.getEmail());
        Assert.assertEquals(TestDataHelper.updatedProviderPhone, provider.getPhone());
        Assert.assertEquals(TestDataHelper.updatedProviderFax, provider.getFax());
        Assert.assertEquals(TestDataHelper.updatedProviderContactPerson, provider.getContact_person());

    }

    //Test "DELETE Provider API"
    @Test
    public void test7DeleteProvider() throws IOException {
        int idOfTestData = TestDataIdGetter.getIdOfProviderTestData();
        String testURL = "providers/" + idOfTestData;

        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(idOfTestData, id);
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
