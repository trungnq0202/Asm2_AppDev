package helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

public class TestDataDeleter {

    //Delete Staff test Data
    public static void deleteStaffTestData(int staffId) throws IOException {
        String deleteStaffURL = "staffs/" + staffId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(deleteStaffURL),
                new TypeToken<Integer>(){}.getType());
    }

    //Delete Provider test Data
    public static void deleteProviderTestData(int providerId) throws IOException {
        String deleteStaffURL = "providers/" + providerId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(deleteStaffURL),
                new TypeToken<Integer>(){}.getType());
    }

    //Delete Customer test Data
    public static void deleteCustomerTestData(int customerId) throws IOException {
        String deleteStaffURL = "customers/" + customerId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(deleteStaffURL),
                new TypeToken<Integer>(){}.getType());
    }

    //Delete Category test Data
    public static void deleteCategoryTestData(int categoryId) throws IOException {
        String deleteCategoryURL = "categories/" + categoryId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(deleteCategoryURL),
                new TypeToken<Integer>(){}.getType());
    }

    //Delete Product test Data
    public static void deleteProductTestData(int productId) throws IOException {
        String deleteProductURL = "products/" + productId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(deleteProductURL),
                new TypeToken<Integer>(){}.getType());
    }

    //Delete Order test Data
    public static void deleteOrderTestData(int orderId) throws IOException {
        String deleteOrderURL = "orders/" + orderId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(deleteOrderURL),
                new TypeToken<Integer>(){}.getType());
    }

    //Delete Receiving Note test Data
    public static void deleteReceivingNoteTestData(int receivingNoteId) throws IOException {
        String deleteOrderURL = "receiving_notes/" + receivingNoteId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(deleteOrderURL),
                new TypeToken<Integer>(){}.getType());
    }

    //Delete Delivery Note test Data
    public static void deleteDeliveryNoteTestData(int deliveryNoteId) throws IOException {
        String deleteOrderURL = "delivery_notes/" + deliveryNoteId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(deleteOrderURL),
                new TypeToken<Integer>(){}.getType());
    }

    //Delete SalesInvoice test Data
    public static void deleteSalesInvoiceTestData(int salesInvoiceId) throws IOException {
        String deleteOrderURL = "sales_invoices/" + salesInvoiceId;
        Gson gson = new Gson();
        int id = gson.fromJson(HTTPRequestMaker.executeDeleteMethodAPI(deleteOrderURL),
                new TypeToken<Integer>(){}.getType());
    }

}
