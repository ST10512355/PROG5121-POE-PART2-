/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    
    //Storing sent messages
    private static ArrayList<String>sentMessages= new ArrayList<>();
    private static int totalMessagesSent=0;
    
    //Constructor 
    public Message(int messageNumber, String recipient, String message) {
        this.messageNumber= messageNumber;
        this.recipient=recipient;
        this.message= message;
        this.messageID=generateMessageID();
        this.messageHash=createMessageHash();
    }
    // Generate a random 10 digit message ID
    private String generateMessageID() {
        Random rand= new Random();
        long id= (long)(rand.nextDouble()*9000000000L)*1000000000L;
        return String.valueOf(id);
    }
    /**
     * Check that message ID is not more than 10 characters.
     */
    public boolean checkMessageID() {
        return messageID.length()<= 10;
    }
    /** Check that the recipient cell number is no more than 10 characters
     * and starts with an international code(+).
     */
     public String checkRecipientCell() {
         boolean valid= recipient.matches("^\\+[0-9]{6,10}$")
         && (recipient.length()-1) <=10;
         if (valid) {
             return "Cell phone number successfully captured.";
         }
         else{
             return "Cell phone number is incorrectly formatted or does not contain an international code.Please "
                     + "correct the number and try again.";
         }
     }
     // Message Hash
     public String createMessageHash() {
         String[] words=message.trim().split("\\s+");
         String firstWord=words[0];
         String lastWord=words[words.length -1];
         
         //Remove punctuation from last word
         lastWord=lastWord.replaceAll("[^a-zA-Z0-9]", "");
         
         String hash=messageID.substring(0,2)+ ";" +messageNumber+ ";"
                 +firstWord.toUpperCase()+ lastWord.toUpperCase();
         this.messageHash=hash;
         return hash;
     }
     //Checks if message exceeds 250 characters.
     public String checkMessageLength() {
         if (message.length()<=250) {
         return "Message ready to send.";
     }
         else {
             int over= message.length()-250;
             return "Message exceeds 250 charachters by "+over+ ";please reduce the size.";
                 }
     }
     //Allow user to send, discard or store message
     public String sentMessage(int choice) {
        switch (choice){
            case 1:
                totalMessagesSent++;
                sentMessages.add(printMessages());
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message." ;
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
     }
     //Return formatted string of the message's details.
     public String printMessages() {
         return "Message ID: "+messageID + "\n"
                 + "Message Hash: "+ messageHash + "\n"
                 +"Recipient: "+ recipient+ "\n"
                 +"Message: " + message;
     }
     //Total number of messages sent.
     public int returnTotalMessages() {
         return totalMessagesSent;
     }
     //Stores message as a JSON-like string
     public void storeMessage() {
         String json = "{"
                 +"\"messageID\": \"" +messageID + "\", "
                 +"\"messageNumber\": "+ messageNumber + ", "
                 +"\"recipient\": \"" + recipient + "\","
                 +"\"message\": \"" + message + "\", "
                 +"\"messageHash\": \"" + messageHash + "\","  
                 + "}"; 
        System.out.println("Message stored: " +json) ; 
     }
     //Getters 
     public String getMessageID() {return messageID; }
     public String getMessageHash() {return messageHash; }
     public String getRecipient() {return recipient; }
     public String getMessage() {return message; }
     public int getMessageNumber() {return messageNumber; }
     public static ArrayList<String> getsentMessages() {return sentMessages; }
}
                         
                
     
      
    
         
             
         
     
    
    
    

