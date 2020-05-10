package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "receiving_note")
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

    public ReceivingNote() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ReceivingNote(int id, Date date, Staff staff, Order order) {
        this.id = id;
        this.date = date;
        this.staff = staff;
        this.order = order;
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
