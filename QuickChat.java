/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
import java.util.Scanner;
 
public class QuickChat {
   public static void main(String[]args) {
       Scanner myInput=new Scanner(System.in);
       
       //Step 1: Register
       System.out.println("===Welcome to QuickChat Registration===");
       System.out.print("Enter username: ");
       String username= myInput.nextLine();
       
       System.out.print("Enter password: ");
       String password= myInput.nextLine();
       
       System.out.print("Enter cell phone number(with an international code e.g. +27): ");
       String cell =myInput.nextLine();
       
       Login login= new Login(username, password,cell);
       String registrationResult = login.registerUser();
       System.out.println(registrationResult);
       
       //Stop if registration failed
       if (!registrationResult.contains("successfully captured")) {
           System.out.println("Registration failed.Exiting.");
           return;
         }
        //Step 2:Login
       System.out.print("\n===Login===");
       System.out.print("Enter username: ");
       String loginUser = myInput.nextLine();
       
       System.out.print("Enter password: ");
       String loginPass = myInput.nextLine();
       
       String loginStatus = login.returnLoginStatus(loginUser, loginPass);
       System.out.println(loginStatus);
       
       if(!login.loginUser(loginUser, loginPass)) {
           System.out.println("Login failed.Exiting.");
           return;
       }
       //Step 3: Welcome & Menu
       System.out.println("\nWelcome to QuckChat.");
       
       System.out.println("\n How many messages do you want to sned?");
       int numMessages =Integer.parseInt(myInput.nextLine());
       
       int messagesSent=0;
       
       while(true) {
           System.out.println("\n1) Send Messages");
           System.out.println("2) Show recently sent messages");
           System.out.println("3) Quit");
            System.out.print("Choose an option: ");
           int menuChoice =Integer.parseInt(myInput.nextLine());
           
           if (menuChoice == 1) {
               if (messagesSent>= numMessages) {
                    System.out.println("You have reached your message limit.");
                    continue ;
               }
                System.out.print("Enter recipient number(with an international code): ");
                String recipient= myInput.nextLine();
                
                 System.out.print("Enter your message: ");
                 String messageText = myInput.nextLine();
                 
                 messagesSent++;
                 Message msg =new Message(messagesSent, recipient, messageText);
                 
                 //Check message length
                  System.out.println(msg.checkMessageLength());
                  if (messageText.length() >250) {
                      messagesSent--;
                      continue;
                  }
                  //Check recipient
                   System.out.println(msg.checkRecipientCell());
                   
                   //Show hash
                    System.out.println("Message Hash: " + msg.createMessageHash());
                    
                    //Send options
                     System.out.println("\n1) Send message");
                     System.out.println("2)  Disregard Message"); 
                     System.out.println("3) Store Message to send later"); 
                     System.out.print("Choose: ");
                     int sendChoice = Integer.parseInt(myInput.nextLine());
                     
                     String sendResult= msg.sentMessage(sendChoice);
                      System.out.println(sendResult);
                      
                  // Display full message details if sent
                  if (sendChoice == 1) {
                       System.out.println("\n---Message Details---");
                       System.out.println(msg.printMessages());
                  }
           }else if (menuChoice ==2) {
                System.out.println("Coming soon.");
                
           }else if (menuChoice ==3) {
                System.out.println("Total messages sent: "+ new Message(0, ""," ").returnTotalMessages());
                 System.out.println("Goodbye!");
                         break; 
           } else {
                System.out.println("Invalid option, please try again.");
               
               
               }
           }
           
       
       }
   }

