package entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String model;

    private String brand;

    private String company;

    private String description;

    private float selling_price;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(int id, String name, String model, String brand, String company, String description, float sellingPrice, Category category) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.company = company;
        this.description = description;
        this.selling_price = sellingPrice;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(float sellingPrice) {
        this.selling_price = sellingPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
