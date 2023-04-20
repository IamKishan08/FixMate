package com.example.myappl;

public class PopularService {

        private String name;
        private String provider;
        private String cost;
        private String timing;
        private int image;

        public PopularService(String name, String provider, String cost, String timing, int image) {
            this.name = name;
            this.provider = provider;
            this.cost = cost;
            this.timing = timing;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public String getProvider() {
            return provider;
        }

        public String getCost() {
            return cost;
        }

        public String getTiming() {
            return timing;
        }

        public int getImage() {
            return image;
        }


}
