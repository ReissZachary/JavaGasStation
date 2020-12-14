package com.MarceloZometa;

public class Card {
    private String cardNumber;
    private String lastName;
    private int pin;
    private String firstName;

    public Card(String cardNumber, String lastName, int pin, String firstName) {
        this.cardNumber = cardNumber;
        this.lastName = lastName;
        this.pin = pin;
        this.firstName = firstName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }



}
