package com.example.myappl.domain;

public class LocalService {
    private String name;
    private String description;
    private String cost;
    private String timing;
    private int imageResource;

    public LocalService(String name, String description, String cost, String timing, int imageResource) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.timing = timing;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCost() {
        return cost;
    }

    public String getTiming() {
        return timing;
    }

    public int getImageResource() {
        return imageResource;
    }
}

