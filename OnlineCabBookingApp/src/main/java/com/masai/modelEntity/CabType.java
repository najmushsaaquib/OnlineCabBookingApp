package com.masai.modelEntity;

public enum CabType {
    MINI, MICRO, MICRO_PREMIUM, SEDAN , SUV, SUV_PREMIUM;
    double price;

    public void setPrice(double price) {
        this.price = price;
    }

    public int sittingCapacity() {
        switch (this) {
            case MINI:
                return 4;
            case MICRO:
                return 4;
            case MICRO_PREMIUM:
                return 4;
            case SEDAN:
                return 5;
            case SUV:
                return 6;
            case SUV_PREMIUM:
                return 7;
            default:
                return 4;
        }
    }

    public double getPrice() {
		price = 8;
        switch (this) {
            case MINI:
                return price *= 0.75;
            case MICRO:
                return price *= 1;
            case MICRO_PREMIUM:
                return price *= 1.75;
            case SEDAN:
                return price *= 2;
            case SUV:
                return price *= 3.75;
            case SUV_PREMIUM:
                return price *= 6;
            default:
                return price;
        }
    }
}
