package com.example.creditmanagement;

public class Transaction {
    private String senderName;
    private String recieverName;
    private int amount;

    public Transaction(String senderName,String recieverName,int amount){
        this.senderName=senderName;
        this.recieverName=recieverName;
        this.amount=amount;
    }
    String getSenderName(){
        return senderName;
    }

    String getRecieverName(){
        return recieverName;
    }
    int getAmount(){
        return amount;
    }
}
