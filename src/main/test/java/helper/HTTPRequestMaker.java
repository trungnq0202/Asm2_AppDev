package helper;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HTTPRequestMaker {
    public static String executeGetMethodAPI(String url) throws IOException {
        URL finalUrl = new URL(TestConfig.baseURL + url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) finalUrl.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    public static String executePostMethodAPI(String url, Map<String, Object> requestBodyDetail) throws IOException {
        JsonObject requestBody = new JsonObject();
        for (Map.Entry<String,Object> param : requestBodyDetail.entrySet()) {
            requestBody.addProperty(param.getKey(), param.getValue().toString());
        }

        URL finalUrl = new URL(TestConfig.baseURL + url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) finalUrl.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setDoOutput(true);
        try(OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    public static String executePostMethodAPI(String url, JsonObject requestBody) throws IOException {
        URL finalUrl = new URL(TestConfig.baseURL + url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) finalUrl.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setDoOutput(true);

        try(OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    public static String executePutMethodAPI(String url, Map<String, Object> requestBodyDetail) throws IOException{
        JsonObject requestBody = new JsonObject();
        for (Map.Entry<String,Object> param : requestBodyDetail.entrySet()) {
            requestBody.addProperty(param.getKey(), param.getValue().toString());
        }

        URL finalUrl = new URL(TestConfig.baseURL + url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) finalUrl.openConnection();
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setDoOutput(true);
        try(OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    public static String executePutMethodAPI(String url, JsonObject requestBody) throws IOException{
        URL finalUrl = new URL(TestConfig.baseURL + url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) finalUrl.openConnection();
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setDoOutput(true);
        try(OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    public static String executeDeleteMethodAPI(String url) throws IOException {
        URL finalUrl = new URL(TestConfig.baseURL + url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) finalUrl.openConnection();
        httpURLConnection.setRequestMethod("DELETE");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }
}
