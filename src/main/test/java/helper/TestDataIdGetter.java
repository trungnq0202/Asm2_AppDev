package helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import model.PaginatedList;

import java.io.IOException;

public class TestDataIdGetter {

    public static int getIdOfStaffTestData(){
        String testURL = "staffs/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Staff> staffList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
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

    public static int getIdOfProviderTestData(){
        String testURL = "providers/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Provider> providerList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
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

    public static int getIdOfCategoryTestData(){
        String testURL = "categories/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Category> staffList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
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

    public static int getIdOfProductTestData(){
        String testURL = "products/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Product> productList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Product>>(){}.getType());

            int maxId = -1;

            for (Product product: productList.getItems())
                if (product.getId() > maxId) maxId = product.getId();

            return maxId;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static int getIdOfCustomerTestData(){
        String testURL = "customers/all?pageIndex=1&pageSize=20";
        try{
            Gson gson = new Gson();
            PaginatedList<Product> productList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                    new TypeToken<PaginatedList<Product>>(){}.getType());

            int maxId = -1;

            for (Product product: productList.getItems())
                if (product.getId() > maxId) maxId = product.getId();

            return maxId;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static int getIdOfOrderTestData() throws IOException {
        String testURL = "orders/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<Order> orderList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<Order>>(){}.getType());

        int maxId = -1;

        for (Order order: orderList.getItems())
            if (order.getId() > maxId) maxId = order.getId();

            return maxId;

    }

    public static int getIdOfOrderDetailTestData() throws IOException {
        String testURL = "order_details/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<OrderDetail> orderDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<OrderDetail>>(){}.getType());

        int maxId = -1;

        for (OrderDetail orderDetail: orderDetailList.getItems())
            if (orderDetail.getId() > maxId) maxId = orderDetail.getId();

        return maxId;

    }

    public static int getIdOfReceivingNoteTestData() throws IOException {
        String testURL = "receiving_notes/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<ReceivingNote> orderDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<ReceivingNote>>(){}.getType());

        int maxId = -1;

        for (ReceivingNote receivingNote: orderDetailList.getItems())
            if (receivingNote.getId() > maxId) maxId = receivingNote.getId();

        return maxId;

    }

    public static int getIdOfReceivingNoteDetailTestData() throws IOException {
        String testURL = "receiving_note_details/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<ReceivingNoteDetail> orderDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<ReceivingNoteDetail>>(){}.getType());

        int maxId = -1;

        for (ReceivingNoteDetail receivingNoteDetail: orderDetailList.getItems())
            if (receivingNoteDetail.getId() > maxId) maxId = receivingNoteDetail.getId();

        return maxId;

    }

    public static int getIdOfDeliveryNoteTestData() throws IOException {
        String testURL = "delivery_notes/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<DeliveryNote> orderDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<DeliveryNote>>(){}.getType());

        int maxId = -1;

        for (DeliveryNote deliveryNote: orderDetailList.getItems())
            if (deliveryNote.getId() > maxId) maxId = deliveryNote.getId();

        return maxId;

    }

    public static int getIdOfDeliveryNoteDetailTestData() throws IOException {
        String testURL = "delivery_note_details/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<DeliveryNoteDetail> orderDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<DeliveryNoteDetail>>(){}.getType());

        int maxId = -1;

        for (DeliveryNoteDetail deliveryNoteDetail: orderDetailList.getItems())
            if (deliveryNoteDetail.getId() > maxId) maxId = deliveryNoteDetail.getId();

        return maxId;

    }

    public static int getIdOfSalesInvoiceTestData() throws IOException {
        String testURL = "sales_invoices/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<SalesInvoice> orderDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<SalesInvoice>>(){}.getType());

        int maxId = -1;

        for (SalesInvoice salesInvoice: orderDetailList.getItems())
            if (salesInvoice.getId() > maxId) maxId = salesInvoice.getId();

        return maxId;

    }

    public static int getIdOfSalesInvoiceDetailTestData() throws IOException {
        String testURL = "sales_invoice_details/all?pageIndex=1&pageSize=50";
        Gson gson = new Gson();
        PaginatedList<SalesInvoiceDetail> orderDetailList = gson.fromJson(HTTPRequestMaker.executeGetMethodAPI(testURL),
                new TypeToken<PaginatedList<SalesInvoiceDetail>>(){}.getType());

        int maxId = -1;

        for (SalesInvoiceDetail salesInvoiceDetail: orderDetailList.getItems())
            if (salesInvoiceDetail.getId() > maxId) maxId = salesInvoiceDetail.getId();

        return maxId;

    }

}
