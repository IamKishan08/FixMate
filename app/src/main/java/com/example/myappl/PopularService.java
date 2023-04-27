package com.example.myappl;

import java.io.Serializable;

public class PopularService implements Serializable {

        private String name;
        private String provider;
       // private String cost;
       // private String timing;
        private int image;

        private String servicedetail;

        private String providerdetail;

        public PopularService(String name, String provider, int image, String servicedetail, String providerdetail) {
            this.name = name;
            this.image = image;
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


        public int getImage() {
            return image;
        }

        public String getServicedetail() {
        return servicedetail;
    }

        public String getProviderdetail() {return providerdetail;}


}
