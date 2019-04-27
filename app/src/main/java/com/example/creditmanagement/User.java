package com.example.creditmanagement;

public class User {
    private String name;
    private String email;
    private int credit;

    public User(String name, String email, int credit){
        this.name=name;
        this.email=email;
        this.credit=credit;
    }

    public String getName(){return name;}

    public String getEmail(){return email;}

    public int getCredit(){return credit;}

    public void addCredit(int credit){this.credit+=credit;}

    public void subCredit(int credit){
        this.credit-=credit;
    }

    public boolean isTransactionPossible(int credit){
        return (this.credit>=credit);
    }

    public void setCredit(int credit){
        this.credit=credit;
    }
}
