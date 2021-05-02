package com.example.retailfinalproj;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "products")
public class ProductModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long incrementalId;


    @SerializedName("id")
    private int id;
    @SerializedName("name")
    @ColumnInfo(name = "details")
    private String details;
    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;
    @SerializedName("category_id")
    private  int categoryId;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private double price;
    @SerializedName("discount")
    private int discount;
    @SerializedName("discount_type")
    private String discountType;
    @SerializedName("currency")
    private String currency;
    @SerializedName("in_stock")
    private int inStock;
    @SerializedName("avatar")
    @ColumnInfo(name = "image")
    private String image;
    @SerializedName("price_final")
    private double priceFinal;
    @SerializedName("price_final_text")
    @ColumnInfo(name = "priceFinalText")
    private String priceFinalText;

    @ColumnInfo(name = "quantity")
    private int quantity=1;


    public ProductModel() {
    }

    public ProductModel(String details, String title, String image, int priceFinal) {
        this.details = details;
        this.title = title;
        this.image = image;
        this.priceFinal = priceFinal;
    }

    public void setIncrementalId(long incrementalId) {
        this.incrementalId = incrementalId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getIncrementalId() {
        return incrementalId;
    }

    public String getPriceFinalText() {
        return priceFinalText;
    }

    public void setPriceFinalText(String priceFinalText) {
        this.priceFinalText = priceFinalText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPriceFinal() {
        return priceFinal;
    }

    public void setPriceFinal(double priceFinal) {
        this.priceFinal = priceFinal;
    }
}
