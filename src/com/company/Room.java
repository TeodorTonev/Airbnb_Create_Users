package com.company;

public class Room {
    private User owner;
    private User tenant;
    private boolean isRent;
    private double priceForMonth;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public boolean isRent() {
        return true;
    }

    public boolean isNotRent() {
        return false;
    }

    public double getPriceForMonth() {
        return priceForMonth;
    }

    public void setPriceForMonth(double priceForMonth) {
        this.priceForMonth = priceForMonth;
    }
}
