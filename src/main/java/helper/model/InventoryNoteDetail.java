package helper.model;

public class InventoryNoteDetail {
    private String productName;
    private int Received;
    private int Delivered;
    private int Balance;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getReceived() {
        return Received;
    }

    public void setReceived(int received) {
        Received = received;
    }

    public int getDelivered() {
        return Delivered;
    }

    public void setDelivered(int delivered) {
        Delivered = delivered;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    public InventoryNoteDetail() {
    }

    public InventoryNoteDetail(String productName, int received, int delivered, int balance) {
        this.productName = productName;
        Received = received;
        Delivered = delivered;
        Balance = balance;
    }
}
