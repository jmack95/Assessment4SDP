package com.assessment4.entities;

public class MasterCard implements Payment {

    private String name;
    private String cardNumber;
    private String expiry;

    public MasterCard (String name, String cardNumber, String expiry) {
        super();
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    @Override
    public boolean purchase(double amount) {
        if (this.cardNumber.length() != 16) {
            return false;
        }
        else {
            return true;
        }
    }
}