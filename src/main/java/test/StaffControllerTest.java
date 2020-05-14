package test;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Staff;
import helper.pagination.PaginatedList;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StaffControllerTest {

    @Test
    public void testGetAllStaffs(){
        try{
            URL url = new URL(TestConfig.URL + "staffs/all?pageIndex=1&pageSize=3");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();

            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }

            Gson gson = new Gson();
            String json = stringBuilder.toString();
            PaginatedList<Staff> staffList = gson.fromJson(json, new TypeToken<PaginatedList<Staff>>(){}.getType());
            Assert.assertEquals("Ngo Quang Trung", staffList.getItems().get(0).getName());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
