package com.example.myapp;

public class RestaurantItem {
    // These are the properties of each menu item
    private String name;
    private String description;
    private String price;
    private int imageResId;
    private String category;
    private double rating;

    // Constructor - this creates a new menu item
    public RestaurantItem(String name, String description, String price,
                          int imageResId, String category, double rating) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.category = category;
        this.rating = rating;
    }

    // Getter methods - these let us read the data
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public String getCategory() { return category; }
    public double getRating() { return rating; }
}