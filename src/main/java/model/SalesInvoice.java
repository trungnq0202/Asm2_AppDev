package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales_invoice", uniqueConstraints = @UniqueConstraint(columnNames = {"deliverynote_id"}))
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

    @OneToMany(mappedBy = "salesInvoice", fetch = FetchType.EAGER)
    private List<SalesInvoiceDetail> salesInvoiceDetails;

    public SalesInvoice() {
    }

    public List<SalesInvoiceDetail> getSalesInvoiceDetails() {
        return salesInvoiceDetails;
    }

    public void setSalesInvoiceDetails(List<SalesInvoiceDetail> salesInvoiceDetails) {
        this.salesInvoiceDetails = salesInvoiceDetails;
    }

    public SalesInvoice(int id, Date date, Staff staff, Customer customer, DeliveryNote deliveryNote, List<SalesInvoiceDetail> salesInvoiceDetails) {
        this.id = id;
        this.date = date;
        this.staff = staff;
        this.customer = customer;
        this.deliveryNote = deliveryNote;
        this.salesInvoiceDetails = salesInvoiceDetails;
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
