package helper.model;

public class InventoryNote {
    private int productId;
    private InventoryNoteDetail inventoryDetail;

    public InventoryNote() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public InventoryNoteDetail getInventoryDetail() {
        return inventoryDetail;
    }

    public void setInventoryDetail(InventoryNoteDetail inventoryDetail) {
        this.inventoryDetail = inventoryDetail;
    }

    public InventoryNote(int id, InventoryNoteDetail inventoryDetail) {
        this.productId = id;
        this.inventoryDetail = inventoryDetail;
    }
}
