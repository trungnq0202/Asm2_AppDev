package test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import entity.Staff;
import helper.pagination.PaginatedList;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffControllerTest {

    //Test "ADD Staff" API
    @Test
    public void test1AddStaff(){
        String testURL = "staffs";
//        Map<String, Object> requestBody = new LinkedHashMap<>();
//        requestBody.put("name","Ngo Quang Trung");
//        requestBody.put("address","235 Nguyen Van Cu");
//        requestBody.put("email","nqtrung02022000@gmail.com");
//        requestBody.put("phone","0703300037");

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name","Ngo Quang Trung");
        requestBody.addProperty("address","235 Nguyen Van Cu");
        requestBody.addProperty("email","nqtrung02022000@gmail.com");
        requestBody.addProperty("phone","0703300037");

        try{
            Gson gson = new Gson();
            Staff staff = gson.fromJson(TestConfig.executePostMethodAPI(testURL, requestBody),
                    new TypeToken<Staff>(){}.getType());

            Assert.assertEquals("Ngo Quang Trung", staff.getName());
            Assert.assertEquals("235 Nguyen Van Cu", staff.getAddress());
            Assert.assertEquals("nqtrung02022000@gmail.com", staff.getEmail());
            Assert.assertEquals("0703300037", staff.getPhone());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "GET all Staffs" API
    @Test
    @Ignore
    public void test2GetAllStaffs(){

        //Get all staffs with pagination, 20 staffs record at 1 page
        String testURL = "staffs/all?pageIndex=1&pageSize=20";

        try{
            Gson gson = new Gson();
            PaginatedList<Staff> staffList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Staff>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(staffList, getIdOfTestData());

            Assert.assertEquals("Ngo Quang Trung", staffList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("235 Nguyen Van Cu", staffList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("nqtrung02022000@gmail.com", staffList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("0703300037", staffList.getItems().get(indexOfTestData).getPhone());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Staff By name API"
    @Test
    @Ignore

    public void test3SearchStaffByName(){
        String testURL = "staffs/by_name?name=Ngo%20Quang%20Trung&pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Staff> staffList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Staff>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(staffList, getIdOfTestData());

            Assert.assertEquals("Ngo Quang Trung", staffList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("235 Nguyen Van Cu", staffList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("nqtrung02022000@gmail.com", staffList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("0703300037", staffList.getItems().get(indexOfTestData).getPhone());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Staff By phone API"
    @Test
    @Ignore

    public void test4SearchStaffByPhone(){
        String testURL = "staffs/by_phone?phone=0703300037&pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Staff> staffList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Staff>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(staffList, getIdOfTestData());

            Assert.assertEquals("Ngo Quang Trung", staffList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("235 Nguyen Van Cu", staffList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("nqtrung02022000@gmail.com", staffList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("0703300037", staffList.getItems().get(indexOfTestData).getPhone());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Staff By address API"
    @Test
    @Ignore

    public void test5SearchStaffByAddress(){
        String testURL = "staffs/by_address?address=235%20Nguyen%20Van%20Cu&pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Staff> staffList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Staff>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(staffList, getIdOfTestData());

            Assert.assertEquals("Ngo Quang Trung", staffList.getItems().get(indexOfTestData).getName());
            Assert.assertEquals("235 Nguyen Van Cu", staffList.getItems().get(indexOfTestData).getAddress());
            Assert.assertEquals("nqtrung02022000@gmail.com", staffList.getItems().get(indexOfTestData).getEmail());
            Assert.assertEquals("0703300037", staffList.getItems().get(indexOfTestData).getPhone());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Test "UPDATE Staff API"
    @Test
    @Ignore

    public void test6UpdateStaff(){
        String testURL = "staffs";
        int idOfTestData = getIdOfTestData();

        Map<String, Object> requestBody = new LinkedHashMap<>();

        requestBody.put("id", getIdOfTestData());
        requestBody.put("name","Nguyen Hue");
        requestBody.put("address","123 Ly Thai To");
        requestBody.put("email","blackhat2664@gmail.com");
        requestBody.put("phone","01634857374");

        try{
            Gson gson = new Gson();
            Staff staff = gson.fromJson(TestConfig.executePutMethodAPI(testURL, requestBody),
                    new TypeToken<Staff>(){}.getType());

            Assert.assertEquals(idOfTestData, staff.getId());
            Assert.assertEquals("Nguyen Hue", staff.getName());
            Assert.assertEquals("123 Ly Thai To", staff.getAddress());
            Assert.assertEquals("blackhat2664@gmail.com", staff.getEmail());
            Assert.assertEquals("01634857374", staff.getPhone());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test

    public void test7DeleteStaff(){
        int idOfTestData = getIdOfTestData();
        String testURL = "staffs/" + idOfTestData;
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
        String testURL = "staffs/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Staff> staffList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Staff>>(){}.getType());

            int maxId = -1;

            for (Staff staff: staffList.getItems())
                if (staff.getId() > maxId) maxId = staff.getId();

            return maxId;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
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
