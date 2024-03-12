package org.example.model;

import java.util.*;

public class Reservation {
    private String customerName;
    private Date date;
    private int numGuests;
    private boolean confirmed;

    public Reservation(String customerName, Date date, int numGuests) {
        this.customerName = customerName;
        this.date = date;
        this.numGuests = numGuests;
        this.confirmed = false;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public void setNumGuests(int numGuests) {
        this.numGuests = numGuests;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
