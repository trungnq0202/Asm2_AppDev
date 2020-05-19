package helper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDataHelper {
    //Staff test data
    public static final String staffName = "Ngo Quang Trung";
    public static final String staffAddress = "235 Nguyen Van Cu";
    public static final String staffEmail = "nqtrung02022000@gmail.com";
    public static final String staffPhone = "0703300037";

    public static final String updatedStaffName = "Nguyen Hue";
    public static final String updatedStaffAddress = "45 Nguyen Van Cu";
    public static final String updatedStaffEmail = "nqtrung6969@gmail.com";
    public static final String updatedStaffPhone = "0703300038";

    //Customer test data
    public static final String customerName = "Vu Thi Bay";
    public static final String customerAddress = "116 Le Van Tho";
    public static final String customerEmail = "vuthibay@gmail.com";
    public static final String customerPhone = "0979878774";
    public static final String customerFax = "125478692";
    public static final String customerContactPerson = "Le Thien Lam";

    public static final String updatedCustomerName = "Ngo Duc Hien";
    public static final String updatedCustomerAddress = "126 Le Van Tho";
    public static final String updatedCustomerEmail = "ngoduchien@gmail.com";
    public static final String updatedCustomerPhone = "0914507001";
    public static final String updatedCustomerFax = "125478693";
    public static final String updatedCustomerContactPerson = "Le Thien Hung";

    //Provider test data
    public static final String providerName = "Mai Yen Vy";
    public static final String providerAddress = "68 Bach Van";
    public static final String providerEmail = "maiyenvy3001@gmail.com";
    public static final String providerPhone = "01647309917";
    public static final String providerFax = "0123456789";
    public static final String providerContactPerson = "Nguyen Duy Hieu";

    public static final String updatedProviderName = "Ho Quang Tri Vinh";
    public static final String updatedProviderAddress = "68 Thanh Thai";
    public static final String updatedProviderEmail = "hqtv@gmail.com";
    public static final String updatedProviderPhone = "09145070014";
    public static final String updatedProviderFax = "985471254";
    public static final String updatedProviderContactPerson = "Bui Le Quoc Hung";

    //Category test data
    //1
    public static final String categoryName = "Apple Laptop";

    public static final String updatedCategoryName = "Apple Iphone";

    //Product Test data
    //1
    public static final String productName = "Macbook Pro 16 inch";
    public static final String productModel = "Macbook Pro";
    public static final String productBrand = "Apple";
    public static final String productCompany = "Apple corporation";
    public static final String productDescription = "Designed for those who defy \" +\n" +
            "                \"limits and change the world, the new MacBook Pro is by \" +\n" +
            "                \"far the most powerful notebook we’ve ever made. ";
    public static final float productSellingPrice = 3000;

    //2
    public static final String productName2 = "Macbook Pro 15 inch";
    public static final String productModel2 = "Macbook Pro";
    public static final String productBrand2 = "Apple";
    public static final String productCompany2 = "Apple corporation";
    public static final String productDescription2 = "Designed for those who defy \" +\n" +
            "                \"limits and change the world, the new MacBook Pro is by \" +\n" +
            "                \"far the most powerful notebook we’ve ever made. ";
    public static final float productSellingPrice2 = 2500;

    public static final String updatedProductName = "Iphone 11 Pro Max";
    public static final String updatedProductModel = "Iphone";
    public static final String updatedProductBrand = "Apple";
    public static final String updatedProductCompany = "Apple corporation";
    public static final String updatedProductDescription = "Designed for those who defy \" +\n" +
            "                \"limits and change the world, the new MacBook Pro is by \" +\n" +
            "                \"far the most powerful notebook we’ve ever made. ";
    public static final float updatedProductSellingPrice = 1600;

    //Order Test data
    public static final String orderDate = "2020-02-02";

    public static final String updatedOrderDate = "2020-06-09";

    //OrderDetail Test data
    public static final int orderDetailQuantity = 10;
    public static final float orderDetailPrice = 2900;

    public static final int orderDetailQuantity2 = 15;
    public static final float orderDetailPrice2 = 2400;

    public static final int updatedOrderDetailQuantity = 20;
    public static final float updatedOrderDetailPrice = 1500;

    //ReceivingNote Test data
    public static final String receivingNoteDate = "2021-01-03";

    public static final String updatedReceivingNoteDate = "2021-02-04";


    //DeliveryNote Test data
    public static final String deliveryNoteDate = "2022-03-05";

    public static final String updatedDeliveryNoteDate = "2022-06-07";

    //DeliveryNoteDetail Test data
    public static final int deliveryNoteDetailQuantity = 5;

    public static final int updatedDeliveryNoteDetailQuantity = 7;

    //SalesInvoice Test data
    public static final String salesInvoiceDate = "2022-03-15";

    public static final String updatedSalesInvoiceDate = "2022-03-20";

    //SalesInvoiceDetail Test data



    //Staff
    public static JsonObject createStaffJSONObject(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",staffName);
        jsonObject.addProperty("address",staffAddress);
        jsonObject.addProperty("email",staffEmail);
        jsonObject.addProperty("phone",staffPhone);
        return jsonObject;
    }

    public static JsonObject createUpdatedStaffJSONObject(int staffId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", staffId);
        jsonObject.addProperty("name",updatedStaffName);
        jsonObject.addProperty("address",updatedStaffAddress);
        jsonObject.addProperty("email",updatedStaffEmail);
        jsonObject.addProperty("phone",updatedStaffPhone);
        return jsonObject;
    }

    public static JsonObject createStaffJSONObjectWithId(int staffId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", staffId);
        return jsonObject;
    }


    //Customer
    public static JsonObject createCustomerJSONObject(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", customerName);
        jsonObject.addProperty("address", customerAddress);
        jsonObject.addProperty("email", customerEmail);
        jsonObject.addProperty("phone", customerPhone);
        jsonObject.addProperty("fax", customerFax);
        jsonObject.addProperty("contact_person", customerContactPerson);

        return jsonObject;
    }

    public static JsonObject createUpdatedCustomerJSONObject(int CustomerId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", CustomerId);
        jsonObject.addProperty("name",updatedCustomerName);
        jsonObject.addProperty("address",updatedCustomerAddress);
        jsonObject.addProperty("email",updatedCustomerEmail);
        jsonObject.addProperty("phone",updatedCustomerPhone);
        jsonObject.addProperty("fax",updatedCustomerFax);
        jsonObject.addProperty("contact_person",updatedCustomerContactPerson);

        return jsonObject;
    }

    private static JsonObject createCustomerJSONObjectWithId(int CustomerId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", CustomerId);
        return jsonObject;
    }

    //Provider
    public static JsonObject createProviderJSONObject(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",providerName);
        jsonObject.addProperty("address",providerAddress);
        jsonObject.addProperty("email",providerEmail);
        jsonObject.addProperty("phone",providerPhone);
        jsonObject.addProperty("fax",providerFax);
        jsonObject.addProperty("contact_person",providerContactPerson);

        return jsonObject;
    }

    public static JsonObject createUpdatedProviderJSONObject(int providerId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", providerId);
        jsonObject.addProperty("name",updatedProviderName);
        jsonObject.addProperty("address",updatedProviderAddress);
        jsonObject.addProperty("email",updatedProviderEmail);
        jsonObject.addProperty("phone",updatedProviderPhone);
        jsonObject.addProperty("fax",updatedProviderFax);
        jsonObject.addProperty("contact_person",updatedProviderContactPerson);

        return jsonObject;
    }

    public static JsonObject createProviderJSONObjectWithId(int providerId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", providerId);
        return jsonObject;
    }

    //Category
    public static JsonObject createCategoryJSONObject(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", categoryName);

        return jsonObject;
    }

    public static JsonObject createUpdatedCategoryJSONObject(int id){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", updatedCategoryName);

        return jsonObject;
    }

    public static JsonObject createCategoryJSONObjectWithId(int id){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);

        return jsonObject;
    }

    //Product
    public static JsonObject createProductJSONObject(int categoryId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",productName);
        jsonObject.addProperty("model",productModel);
        jsonObject.addProperty("brand",productBrand);
        jsonObject.addProperty("company",productCompany);
        jsonObject.addProperty("description",productDescription);
        jsonObject.addProperty("selling_price",productSellingPrice);
        //Create category json element
        jsonObject.add("category", createCategoryJSONObjectWithId(categoryId));

        return jsonObject;
    }

    public static JsonObject createProductJSONObject2(int categoryId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",productName2);
        jsonObject.addProperty("model",productModel2);
        jsonObject.addProperty("brand",productBrand2);
        jsonObject.addProperty("company",productCompany2);
        jsonObject.addProperty("description",productDescription2);
        jsonObject.addProperty("selling_price",productSellingPrice2);
        //Create category json element
        jsonObject.add("category", createCategoryJSONObjectWithId(categoryId));

        return jsonObject;
    }

    public static JsonObject createUpdatedProductJSONObject(int productId, int categoryId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",productId);
        jsonObject.addProperty("name",updatedProductName);
        jsonObject.addProperty("model",updatedProductModel);
        jsonObject.addProperty("brand",updatedProductBrand);
        jsonObject.addProperty("company",updatedProductCompany);
        jsonObject.addProperty("description",updatedProductDescription);
        jsonObject.addProperty("selling_price",updatedProductSellingPrice);
        //Create category json element
        jsonObject.add("category", createCategoryJSONObjectWithId(categoryId));

        return jsonObject;
    }

    public static JsonObject createProductJSONObjectWithId(int productId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",productId);
        return jsonObject;
    }


    //Order
    public static JsonObject createOrderJSONObject(int staffId, int providerId, int productId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", orderDate);
        jsonObject.add("staff", createStaffJSONObjectWithId(staffId));
        jsonObject.add("provider", createProviderJSONObjectWithId(providerId));

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(createOrderDetailJSONObject(productId));

        jsonObject.add("orderDetails", jsonArray);

        return jsonObject;
    }

    public static JsonObject createOrderJSONObjectWithId(int orderId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",orderId);
        return jsonObject;
    }

    public static JsonObject createUpdatedOrderJSONObject(int orderId, int orderDetailId ,int staffId, int providerId, int productId, int categoryId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", orderId);
        jsonObject.addProperty("date", updatedOrderDate);
        jsonObject.add("staff", createUpdatedStaffJSONObject(staffId));
        jsonObject.add("provider", createUpdatedProviderJSONObject(providerId));

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(createUpdatedOrderDetailJSONObject(orderDetailId, productId, categoryId));

        jsonObject.add("orderDetails", jsonArray);

        return jsonObject;
    }

    //Order Detail
    public static JsonObject createOrderDetailJSONObject(int productId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("quantity", orderDetailQuantity);
        jsonObject.addProperty("price", orderDetailPrice);
        //Create product json element
        jsonObject.add("product", createProductJSONObjectWithId(productId));
        return jsonObject;
    }

    public static JsonObject createOrderDetail2JSONObject(int productId, int orderId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("order", createOrderJSONObjectWithId(orderId));
        jsonObject.addProperty("quantity", orderDetailQuantity2);
        jsonObject.addProperty("price", orderDetailPrice2);
        //Create product json element
        jsonObject.add("product", createProductJSONObjectWithId(productId));
        return jsonObject;
    }

    public static JsonObject createUpdatedOrderDetailJSONObject(int OrderDetailId, int productId, int categoryId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", OrderDetailId);
        jsonObject.addProperty("quantity", updatedOrderDetailQuantity);
        jsonObject.addProperty("price", updatedOrderDetailPrice);
        jsonObject.add("product", createUpdatedProductJSONObject(productId, categoryId));
        return jsonObject;


    }

    //Receiving Note
    public static JsonObject createReceivingNoteJSONObject(int staffId, int orderId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", receivingNoteDate);
        jsonObject.add("staff", createStaffJSONObjectWithId(staffId));
        jsonObject.add("order", createOrderJSONObjectWithId(orderId));
        return jsonObject;
    }

    public static JsonObject createReceivingNoteJSONObjectWithId(int receivingNoteId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",receivingNoteId);
        return jsonObject;
    }

    public static JsonObject createUpdatedReceivingNoteJSONObject(int receivingNoteId, int orderId, int orderDetailId ,int staffId, int providerId, int productId, int categoryId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", receivingNoteId);
        jsonObject.addProperty("date", updatedReceivingNoteDate);
        jsonObject.add("staff", createUpdatedStaffJSONObject(staffId));
        jsonObject.add("order", createUpdatedOrderJSONObject(orderId, orderDetailId, staffId, providerId, productId, categoryId));

        return jsonObject;
    }

    //Delivery note
    public static JsonObject createDeliveryNoteJSONObject(int productId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", deliveryNoteDate);

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(createDeliveryNoteDetailJSONObject(productId));

        jsonObject.add("deliveryNoteDetails", jsonArray);
        return jsonObject;
    }

    public static JsonObject createDeliveryNoteJSONObjectWithId(int deliveryNoteId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",deliveryNoteId);
        return jsonObject;
    }

    public static JsonObject createUpdatedDeliveryNoteJSONObject(int deliveryNoteId, int deliveryNoteDetailId ,int productId, int categoryId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", deliveryNoteId);
        jsonObject.addProperty("date", updatedDeliveryNoteDate);

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(createUpdatedDeliveryNoteDetailJSONObject(deliveryNoteDetailId, productId, categoryId));

        jsonObject.add("deliveryNoteDetails", jsonArray);

        return jsonObject;
    }

    //Delivery note detail
    public static JsonObject createDeliveryNoteDetailJSONObject(int productId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("product", createProductJSONObjectWithId(productId));
        jsonObject.addProperty("quantity", deliveryNoteDetailQuantity);
        return jsonObject;
    }

    public static JsonObject createUpdatedDeliveryNoteDetailJSONObject(int deliveryNoteDetailId, int productId, int categoryId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",deliveryNoteDetailId);
        jsonObject.add("product", createUpdatedProductJSONObject(productId, categoryId));
        jsonObject.addProperty("quantity", updatedDeliveryNoteDetailQuantity);
        return jsonObject;
    }


    //SalesInvoice
    public static JsonObject createSalesInvoiceJSONObject(int staffId, int customerId, int deliveryNoteId){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", salesInvoiceDate);
        jsonObject.add("staff", createStaffJSONObjectWithId(staffId));
        jsonObject.add("customer", createCustomerJSONObjectWithId(customerId));
        jsonObject.add("deliveryNote", createDeliveryNoteJSONObjectWithId(deliveryNoteId));
        return jsonObject;
    }

    public static JsonObject createUpdatedSalesInvoiceJSONObject(int salesInvoiceId, int staffId, int customerId
            , int deliveryNoteId, int deliveryNoteDetailId ,int productId, int categoryId){

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", salesInvoiceId);
        jsonObject.addProperty("date", updatedSalesInvoiceDate);
        jsonObject.add("staff", createUpdatedStaffJSONObject(staffId));
        jsonObject.add("customer", createUpdatedCustomerJSONObject(customerId));
        jsonObject.add("deliveryNote", createUpdatedDeliveryNoteJSONObject(deliveryNoteId, deliveryNoteDetailId, productId, categoryId));
        return jsonObject;
    }

}
