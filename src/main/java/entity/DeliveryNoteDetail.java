package entity;

import javax.persistence.*;

@Entity
@Table(name = "delivery_note_detail")
public class DeliveryNoteDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private DeliveryNote deliveryNote;

    @ManyToOne
    private Product product;

    private int quantity;



    public DeliveryNoteDetail() {
    }

    public DeliveryNoteDetail(int id, DeliveryNote deliveryNote, Product product, int quantity) {
        this.id = id;
        this.deliveryNote = deliveryNote;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeliveryNote getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
