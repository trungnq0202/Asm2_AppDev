package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery_note")
public class DeliveryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

//    @Transient
//    List<DeliveryNoteDetail>

    public DeliveryNote() {
    }

    public DeliveryNote(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
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
