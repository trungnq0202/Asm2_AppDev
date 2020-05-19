package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receiving_note", uniqueConstraints = @UniqueConstraint(columnNames = {"order_id"}))
public class ReceivingNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private Staff staff;

    @OneToOne
    private Order order;

    @OneToMany(mappedBy = "receivingNote", fetch = FetchType.EAGER)
    private List<ReceivingNoteDetail> receivingNoteDetails;

    public List<ReceivingNoteDetail> getReceivingNoteDetails() {
        return receivingNoteDetails;
    }

    public void setReceivingNoteDetails(List<ReceivingNoteDetail> receivingNoteDetails) {
        this.receivingNoteDetails = receivingNoteDetails;
    }

    public ReceivingNote() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ReceivingNote(int id, Date date, Staff staff, Order order, List<ReceivingNoteDetail> receivingNoteDetails) {
        this.id = id;
        this.date = date;
        this.staff = staff;
        this.order = order;
        this.receivingNoteDetails = receivingNoteDetails;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
