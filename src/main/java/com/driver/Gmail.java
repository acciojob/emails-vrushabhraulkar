package com.driver;

import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    ArrayList<Triple<Date,String,String>>Inbox=new ArrayList<>();
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    ArrayList<Triple<Date,String,String>>Trash=new ArrayList<>();
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
        this.Inbox=new ArrayList<>();
        this.Trash=new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(Inbox.size()==inboxCapacity){
            Triple<Date,String,String>oldMessage=Inbox.get(0);
            Inbox.remove(0);
            Trash.add(oldMessage);
        }
        Triple<Date,String,String>newMail=Triple.of(date,sender,message);
        Inbox.add(newMail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        int ind=-1;
        for(int i=0;i<Inbox.size();i++){
            if(Inbox.get(i).getRight().equals(message)){
                ind=i;
            }
        }
        if(ind != -1){
            Trash.add(Inbox.get(ind));
            Inbox.remove(ind);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(Inbox.size()==0){
            return null;
        }
        else{
            return Inbox.get(Inbox.size()-1).getRight();
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

        if(Inbox.size()==0){
            return null;
        }
        else{
            return Inbox.get(0).getRight();
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        int count=0;
        for(int i=0;i<Inbox.size();i++){
            if(Inbox.get(i).getLeft().compareTo(start)>=0 && Inbox.get(i).getLeft().compareTo(end)<=0){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}