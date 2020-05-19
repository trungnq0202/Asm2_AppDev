package helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.io.IOException;
import java.util.Date;

public class TestDataAdder {

    //Add Staff test data to database
    public static int addStaffTestDataAndReturnId() throws IOException {
        //Add a staff to the database
        String addStaffURL = "staffs";
        JsonObject requestBodyStaff = TestDataHelper.createStaffJSONObject();

        Gson gson = new Gson();
        Staff staff = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(addStaffURL, requestBodyStaff),
                new TypeToken<Staff>(){}.getType());

        return staff.getId();
    }

    //Add Provider test data to database
    public static int addProviderTestDataAndReturnId() throws IOException {
        //Add a Provider to the database
        String addProviderURL = "providers";
        JsonObject requestBodyProvider = TestDataHelper.createProviderJSONObject();

        Gson gson = new Gson();
        Provider provider = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(addProviderURL, requestBodyProvider),
                new TypeToken<Provider>(){}.getType());

        return provider.getId();
    }

    //Add Customer test data to database
    public static int addCustomerTestDataAndReturnId() throws IOException {
        //Add a Provider to the database
        String addProviderURL = "customers";
        JsonObject requestBodyProvider = TestDataHelper.createCustomerJSONObject();

        Gson gson = new Gson();
        Customer customer = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(addProviderURL, requestBodyProvider),
                new TypeToken<Customer>(){}.getType());

        return customer.getId();
    }

    //Add Category test data to database
    public static int addCategoryTestDataAndReturnId() throws IOException {
        //Add a Category to the database
        String addProviderURL = "categories";
        JsonObject requestBodyCategory = TestDataHelper.createCategoryJSONObject();

        Gson gson = new Gson();
        Category category = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(addProviderURL, requestBodyCategory),
                new TypeToken<Category>(){}.getType());

        return category.getId();
    }

    //Add Product test data to database
    public static int addProductTestDataAndReturnId(int categoryId) throws IOException{
        String addProviderURL = "products";
        JsonObject requestBodyCategory = TestDataHelper.createProductJSONObject(categoryId);
        Gson gson = new Gson();
        Product product = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(addProviderURL, requestBodyCategory),
                new TypeToken<Product>(){}.getType());

        return product.getId();
    }

    public static int addProductTestData2AndReturnId(int categoryId) throws IOException{
        String addProviderURL = "products";
        JsonObject requestBodyCategory = TestDataHelper.createProductJSONObject2(categoryId);
        Gson gson = new Gson();
        Product product = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(addProviderURL, requestBodyCategory),
                new TypeToken<Product>(){}.getType());

        return product.getId();
    }

    //Add Order test data to database
    public static int addOrderTestDataAndReturnId(int staffId, int providerId, int productId) throws IOException {
        String addOrderURL = "orders";
        JsonObject requestBodyOrder = TestDataHelper.createOrderJSONObject(staffId, providerId, productId);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        Order order = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(addOrderURL, requestBodyOrder),
                new TypeToken<Order>(){}.getType());
        return order.getId();
    }

    //Add ReceivingNote test data to database
    public static int addReceivingNoteTestDataAndReturnId(int staffId, int orderId) throws IOException {
        String addOrderURL = "receiving_notes";
        JsonObject requestBodyReceivingNote = TestDataHelper.createReceivingNoteJSONObject(staffId, orderId);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        ReceivingNote receivingNote = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(addOrderURL, requestBodyReceivingNote),
                new TypeToken<ReceivingNote>(){}.getType());
        return receivingNote.getId();
    }

    //Add DeliveryNote test data to database
    public static int addDeliveryNoteTestDataAndReturnId(int productId) throws IOException {
        String addOrderURL = "delivery_notes";
        JsonObject requestBodyDeliveryNote = TestDataHelper.createDeliveryNoteJSONObject(productId);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        DeliveryNote deliveryNote = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(addOrderURL, requestBodyDeliveryNote),
                new TypeToken<DeliveryNote>(){}.getType());
        return deliveryNote.getId();
    }

    //Add SalesInvoice Note test data to database
    public static int addSalesInvoiceTestDataAndReturnId(int staffId, int customerId, int deliveryNoteId) throws IOException {
        String addOrderURL = "sales_invoices";
        JsonObject requestBodyDeliveryNote = TestDataHelper.createSalesInvoiceJSONObject(staffId, customerId, deliveryNoteId);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        DeliveryNote deliveryNote = gson.fromJson(HTTPRequestMaker.executePostMethodAPI(addOrderURL, requestBodyDeliveryNote),
                new TypeToken<DeliveryNote>(){}.getType());
        return deliveryNote.getId();
    }


}
