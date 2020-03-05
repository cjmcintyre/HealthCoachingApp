package com.creuenterprise.bergman.feburary2020.UTILS;

public class Client {

    String clientID;
    String firstName;
    String lastName;
    String myFitnessPalLink;

    public Client()
    {

    }

    public Client(String clientID, String firstName, String lastName, String myFitnessPalLink) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.myFitnessPalLink = myFitnessPalLink;
    }

    public String getClientID() {
        return clientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMyFitnessPalLink() {
        return myFitnessPalLink;
    }


}
