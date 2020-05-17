package test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Category;
import helper.pagination.PaginatedList;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryControllerTest {

    //Test "ADD Category" API
    @Test
    public void test1AddCategory(){
        String testURL = "categories";
        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name","Phone");

        try{
            Gson gson = new Gson();
            Category category = gson.fromJson(TestConfig.executePostMethodAPI(testURL, requestBody),
                    new TypeToken<Category>(){}.getType());

            Assert.assertEquals("Phone", category.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "GET all Categories" API
    @Test
    public void test2GetAllCategories(){

        //Get all staffs with pagination, 20 staffs record at 1 page
        String testURL = "categories/all?pageIndex=1&pageSize=20";

        try{
            Gson gson = new Gson();
            PaginatedList<Category> categoryList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Category>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(categoryList, getIdOfTestData());

            Assert.assertEquals("Phone", categoryList.getItems().get(indexOfTestData).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "Search Category By name API"
    @Test
    public void test3SearchCategoryByName(){
        String testURL = "categories/by_name?name=Phone&pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Category> categoryList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Category>>(){}.getType());

            int indexOfTestData = getIndexOfTestDataInReturnList(categoryList, getIdOfTestData());

            Assert.assertEquals("Phone", categoryList.getItems().get(indexOfTestData).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test "UPDATE Category API"
    @Test
    public void test4updateCategory(){
        String testURL = "categories";
        int idOfTestData = getIdOfTestData();

        Map<String, Object> requestBody = new LinkedHashMap<>();

        requestBody.put("id", getIdOfTestData());
        requestBody.put("name","Speakers");

        try{
            Gson gson = new Gson();
            Category category = gson.fromJson(TestConfig.executePutMethodAPI(testURL, requestBody),
                    new TypeToken<Category>(){}.getType());

            Assert.assertEquals(idOfTestData, category.getId());
            Assert.assertEquals("Speakers", category.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test5DeleteCategory(){
        int idOfTestData = getIdOfTestData();
        String testURL = "categories/" + idOfTestData;
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
        String testURL = "categories/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Category> staffList = gson.fromJson(TestConfig.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Category>>(){}.getType());

            int maxId = -1;

            for (Category category: staffList.getItems())
                if (category.getId() > maxId) maxId = category.getId();

            return maxId;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int getIndexOfTestDataInReturnList(PaginatedList<Category> categoryList, int idOfTestData){
        for (int i = 0; i < categoryList.getItems().size(); i++){
            if (categoryList.getItems().get(i).getId() == idOfTestData) {
                return i;
            }
        }
        return 0;
    }

}
