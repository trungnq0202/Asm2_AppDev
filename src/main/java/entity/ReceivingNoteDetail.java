package entity;

import javax.persistence.*;

@Entity
@Table(name = "receiving_note_detail")
public class ReceivingNoteDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private ReceivingNote receivingNote;

    @ManyToOne
    private Product product;

    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReceivingNote getReceivingNote() {
        return receivingNote;
    }

    public void setReceivingNote(ReceivingNote receivingNote) {
        this.receivingNote = receivingNote;
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

    public ReceivingNoteDetail() {
    }

    public ReceivingNoteDetail(int id, ReceivingNote receivingNote, Product product, int quantity) {
        this.id = id;
        this.receivingNote = receivingNote;
        this.product = product;
        this.quantity = quantity;
    }
}
