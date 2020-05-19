import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.Category;
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
public class CategoryControllerTest {

    //Test "ADD Category" API
    @Test
    public void test1AddCategory() throws IOException {
        String testURL = "categories";
        JsonObject requestBody = TestDataHelper.createCategoryJSONObject();
        Gson gson = new Gson();
        Category category = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(testURL, requestBody),
                new TypeToken<Category>(){}.getType());

        Assert.assertEquals(TestDataHelper.categoryName, category.getName());
    }

    //Test "GET all Categories" API
    @Test
    public void test2GetAllCategories() throws IOException {
        //Get all staffs with pagination, 20 staffs record at 1 page
        String testURL = "categories/all?pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Category> categoryList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Category>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(categoryList, TestDataIdGetter.getIdOfCategoryTestData());
        Category category = categoryList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.categoryName, category.getName());

    }

    //Test "Search Category By name API"
    @Test
    public void test3SearchCategoryByName() throws IOException {
        String testURL = "categories/by_name?name=Apple%20Laptop&pageIndex=1&pageSize=20";

        Gson gson = new Gson();
        PaginatedList<Category> categoryList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Category>>(){}.getType());

        int indexOfTestData = getIndexOfTestDataInReturnList(categoryList, TestDataIdGetter.getIdOfCategoryTestData());
        Category category = categoryList.getItems().get(indexOfTestData);

        Assert.assertEquals(TestDataHelper.categoryName, category.getName());
    }

    //Test "UPDATE Category API"
    @Test
    public void test4updateCategory() throws IOException {
        String testURL = "categories";

        JsonObject requestBody = TestDataHelper.createUpdatedCategoryJSONObject(TestDataIdGetter.getIdOfCategoryTestData());
        Gson gson = new Gson();
        Category category = gson.fromJson(HTTPRequestMaker.executePutMethodAPI(testURL, requestBody),
                new TypeToken<Category>(){}.getType());

        Assert.assertEquals(TestDataHelper.updatedCategoryName, category.getName());

    }

    //Test "DELETE Category API"
    @Test
    public void test5DeleteCategory() throws IOException {
        int idOfTestData = TestDataIdGetter.getIdOfCategoryTestData();
        String testURL = "categories/" + idOfTestData;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(testURL),
                new TypeToken<Integer>(){}.getType());

        Assert.assertEquals(idOfTestData, id);
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
