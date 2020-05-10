package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales_invoice")
public class SalesInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private Staff staff;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private DeliveryNote deliveryNote;

    public SalesInvoice() {
    }

    public SalesInvoice(int id, Staff staff, Customer customer, DeliveryNote deliveryNote) {
        this.id = id;
        this.staff = staff;
        this.customer = customer;
        this.deliveryNote = deliveryNote;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public DeliveryNote getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
    }
}
