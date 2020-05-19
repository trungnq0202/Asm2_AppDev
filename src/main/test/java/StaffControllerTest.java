import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.Staff;
import helper.HTTPRequestMaker;
import helper.TestDataHelper;
import helper.TestDataIdGetter;
import model.PaginatedList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffControllerTest {

    //Test "ADD Staff" API
    @Test
    public void test1AddStaff() throws IOException {
        String testURL = "staffs";

        JsonObject requestBody = TestDataHelper.createStaffJSONObject();
        Gson gson = new Gson();
        Staff staff = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(testURL, requestBody),
                new TypeToken<Staff>(){}.getType());

        Assert.assertEquals(TestDataHelper.staffName, staff.getName());
        Assert.assertEquals(TestDataHelper.staffAddress, staff.getAddress());
        Assert.assertEquals(TestDataHelper.staffEmail, staff.getEmail());
        Assert.assertEquals(TestDataHelper.staffPhone, staff.getPhone());

    }

    //Test "GET all Staffs" API
    @Test
    public void test2GetAllStaffs() throws IOException {

        //Get all staffs with pagination, 20 staffs record at 1 page
        String testURL = "staffs/all?pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Staff> staffList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Staff>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(staffList, TestDataIdGetter.getIdOfStaffTestData());
        Staff staff = staffList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.staffName, staff.getName());
        Assert.assertEquals(TestDataHelper.staffAddress, staff.getAddress());
        Assert.assertEquals(TestDataHelper.staffEmail, staff.getEmail());
        Assert.assertEquals(TestDataHelper.staffPhone, staff.getPhone());

    }

    //Test "Search Staff By name API"
    @Test
    public void test3SearchStaffByName() throws IOException {
        String testURL = "staffs/by_name?name=Ngo%20Quang%20Trung&pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Staff> staffList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Staff>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(staffList, TestDataIdGetter.getIdOfStaffTestData());
        Staff staff = staffList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.staffName, staff.getName());
        Assert.assertEquals(TestDataHelper.staffAddress, staff.getAddress());
        Assert.assertEquals(TestDataHelper.staffEmail, staff.getEmail());
        Assert.assertEquals(TestDataHelper.staffPhone, staff.getPhone());

    }

    //Test "Search Staff By phone API"
    @Test
    public void test4SearchStaffByPhone() throws IOException {
        String testURL = "staffs/by_phone?phone=0703300037&pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Staff> staffList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Staff>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(staffList, TestDataIdGetter.getIdOfStaffTestData());
        Staff staff = staffList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.staffName, staff.getName());
        Assert.assertEquals(TestDataHelper.staffAddress, staff.getAddress());
        Assert.assertEquals(TestDataHelper.staffEmail, staff.getEmail());
        Assert.assertEquals(TestDataHelper.staffPhone, staff.getPhone());

    }

    //Test "Search Staff By address API"
    @Test
    public void test5SearchStaffByAddress() throws IOException {
        String testURL = "staffs/by_address?address=235%20Nguyen%20Van%20Cu&pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Staff> staffList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Staff>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(staffList, TestDataIdGetter.getIdOfStaffTestData());
        Staff staff = staffList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.staffName, staff.getName());
        Assert.assertEquals(TestDataHelper.staffAddress, staff.getAddress());
        Assert.assertEquals(TestDataHelper.staffEmail, staff.getEmail());
        Assert.assertEquals(TestDataHelper.staffPhone, staff.getPhone());
    }

    //Test "UPDATE Staff API"
    @Test
    public void test6UpdateStaff() throws IOException {
        String testURL = "staffs";

        JsonObject requestBody = TestDataHelper.createUpdatedStaffJSONObject(TestDataIdGetter.getIdOfStaffTestData());
        Gson gson = new Gson();
        Staff staff = gson.fromJson(HTTPRequestMaker.executePutMethodAPI(testURL, requestBody),
                new TypeToken<Staff>(){}.getType());

        Assert.assertEquals(TestDataHelper.updatedStaffName, staff.getName());
        Assert.assertEquals(TestDataHelper.updatedStaffAddress, staff.getAddress());
        Assert.assertEquals(TestDataHelper.updatedStaffEmail, staff.getEmail());
        Assert.assertEquals(TestDataHelper.updatedStaffPhone, staff.getPhone());

    }

    //Test "DELETE Staff API"
    @Test
    public void test7DeleteStaff() throws IOException {
        int idOfTestData = TestDataIdGetter.getIdOfStaffTestData();
        String testURL = "staffs/" + idOfTestData;

        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(idOfTestData, id);


    }

    private int getIndexOfTestDataInReturnList(PaginatedList<Staff> staffList, int idOfTestData){
        for (int i = 0; i < staffList.getItems().size(); i++){
            if (staffList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

}
