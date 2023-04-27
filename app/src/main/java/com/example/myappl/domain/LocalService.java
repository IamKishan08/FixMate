package com.example.myappl.domain;

import java.io.Serializable;

public class LocalService implements Serializable {
    private String name;
   // private String description;

    private String provider;

    private int imageResource;

    private String servicedetail;

    private String providerdetail;



    public LocalService(String name, String provider, String servicedetail, String providerdetail, int imageResource) {
        this.name = name;
        //this.description = description;
        this.imageResource = imageResource;
        this.provider = provider;


        this.servicedetail = servicedetail;
        this.providerdetail = providerdetail;



    }

    public String getName() {
        return name;
    }


   public String getProvider() {
       return provider;
   }

    public String getServicedetail() {
        return servicedetail;
    }

    public String getProviderdetail() {return providerdetail;}

    public int getImageResource() {
        return imageResource;
    }
}

