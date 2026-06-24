package com.example.myapp;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<RestaurantItem> allItems; // All items
    private List<RestaurantItem> filteredItems; // Items shown after search

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the views
        recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create menu data
        createMenuData();

        // Setup adapter
        setupAdapter();

        // Setup search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterItems(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterItems(newText);
                return true;
            }
        });
    }

    private void createMenuData() {
        allItems = new ArrayList<>();

        // Adding menu items
        // Note: For images, we'll use a default image. You can add your own images later.
        allItems.add(new RestaurantItem(
                "Margherita Pizza",
                "Classic tomato sauce with mozzarella and basil",
                "$12.99",
                android.R.drawable.ic_menu_gallery, // Default image
                "Italian",
                4.5
        ));

        allItems.add(new RestaurantItem(
                "Caesar Salad",
                "Fresh romaine with parmesan and croutons",
                "$8.99",
                android.R.drawable.ic_menu_gallery,
                "Healthy",
                4.2
        ));

        allItems.add(new RestaurantItem(
                "Grilled Salmon",
                "Fresh Atlantic salmon with vegetables",
                "$18.99",
                android.R.drawable.ic_menu_gallery,
                "Seafood",
                4.8
        ));

        allItems.add(new RestaurantItem(
                "Beef Burger",
                "Premium beef with cheddar and bacon",
                "$14.99",
                android.R.drawable.ic_menu_gallery,
                "American",
                4.6
        ));

        allItems.add(new RestaurantItem(
                "Pasta Carbonara",
                "Creamy pasta with pancetta and egg",
                "$13.99",
                android.R.drawable.ic_menu_gallery,
                "Italian",
                4.4
        ));

        allItems.add(new RestaurantItem(
                "Chicken Tikka",
                "Grilled chicken with spices and yogurt",
                "$15.99",
                android.R.drawable.ic_menu_gallery,
                "Indian",
                4.7
        ));

        allItems.add(new RestaurantItem(
                "Vegetable Soup",
                "Fresh vegetables in clear broth",
                "$6.99",
                android.R.drawable.ic_menu_gallery,
                "Healthy",
                4.0
        ));

        allItems.add(new RestaurantItem(
                "Chocolate Cake",
                "Rich chocolate cake with ganache",
                "$7.99",
                android.R.drawable.ic_menu_gallery,
                "Dessert",
                4.9
        ));
    }

    private void setupAdapter() {
        // Start with all items
        filteredItems = new ArrayList<>(allItems);

        adapter = new RestaurantAdapter(filteredItems, item -> {
            // This runs when a menu item is clicked
            Toast.makeText(this,
                    "Selected: " + item.getName() + "\nPrice: " + item.getPrice(),
                    Toast.LENGTH_SHORT).show();
        });

        recyclerView.setAdapter(adapter);
    }

    private void filterItems(String query) {
        filteredItems = new ArrayList<>();
        query = query.toLowerCase().trim();

        if (query.isEmpty()) {
            // Show all items if search is empty
            filteredItems.addAll(allItems);
        } else {
            // Search through all items
            for (RestaurantItem item : allItems) {
                if (item.getName().toLowerCase().contains(query) ||
                        item.getCategory().toLowerCase().contains(query) ||
                        item.getDescription().toLowerCase().contains(query)) {
                    filteredItems.add(item);
                }
            }
        }

        // Update the adapter with filtered results
        adapter = new RestaurantAdapter(filteredItems, item -> {
            Toast.makeText(this,
                    "Selected: " + item.getName() + "\nPrice: " + item.getPrice(),
                    Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
    }
}