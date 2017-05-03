package com.example.williammordohay.simplewsproject;

/**
 * Created by william.mordohay on 03/05/2017.
 */

public class Product {

    private int ProductId, Price;
    private String Name, CategoryName;

    public Product(int ProductId,String Name, String CategoryName, int Price){
        this.ProductId=ProductId;
        this.Price=Price;
        this.Name=Name;
        this.CategoryName=CategoryName;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
