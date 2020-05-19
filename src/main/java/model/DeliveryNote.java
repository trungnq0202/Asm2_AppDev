package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "delivery_note")
public class DeliveryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "deliveryNote", fetch = FetchType.EAGER)
    private List<DeliveryNoteDetail> deliveryNoteDetails;

    public DeliveryNote() {
    }

    public DeliveryNote(int id, Date date, List<DeliveryNoteDetail> deliveryNoteDetails) {
        this.id = id;
        this.date = date;
        this.deliveryNoteDetails = deliveryNoteDetails;
    }

    public int getId() {
        return id;
    }

    public List<DeliveryNoteDetail> getDeliveryNoteDetails() {
        return deliveryNoteDetails;
    }

    public void setDeliveryNoteDetails(List<DeliveryNoteDetail> deliveryNoteDetails) {
        this.deliveryNoteDetails = deliveryNoteDetails;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
