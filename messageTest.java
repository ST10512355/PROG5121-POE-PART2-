/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ASUS
 */
public class messageTest {
    
    //Message length test
    @Test 
    public void testMessageReadyToSend() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us"
                + "for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }
    @Test
    public void testMessageExceeds250Charachters() {
        String longMessage ="a".repeat(260);
        Message msg= new Message(1, "+27718693002", longMessage );
        String result= msg.checkMessageLength();
        assertTrue(result.contains("Message exceeds 250 characters by 10."));
    }
    //Recipient cell number tests
    @Test
    public void testRecipientCellCorrectlyFormatted() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us"
                + "for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }
    @Test 
    public void testRecipientCellIncorrectlyFormatted() {
        Message msg= new Message (2, "08575975889", "Hi Keagan, did you recieve the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code"
                + ".Please correct the number and try again.", msg.checkRecipientCell());
    }
    //Message hash Tests
    @Test 
    public void testMessageHashFormat() {
        Message msg= new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash= msg.createMessageHash();
        
    //Check it ends with :HITONIGHT and contains the message number
    assertTrue(hash.endsWith(":HITONIGHT"));
    assertTrue(hash.contains(":1:"));
    }
    
   //Message ID Tests
   @Test 
   public void testMessageIDLength() {
       Message msg= new Message(1, "+27218693002" , "Hi Mike, can you join us for dinner tonight?");
       assertTrue(msg.checkMessageID());
   }
   
   @Test
   public void testSendMessage() {
       Message msg= new Message (1, "+27218693002" , "Hi Mike, can you join us for dinner tonight?");
       assertEquals("Message successfully sent.", msg.sentMessage(1));
   }
   
   @Test 
   public void testDisregardMessage() {
       Message msg= new Message(2, "+27218693002" , "Hi Mike, can you join us for dinner tonight?");
       assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
   }
   
   @Test
   public void testStoreMessage() {
       Message msg= new Message(3, "+27218693002" , "Hi Mike, can you join us for dinner tonight?");
       assertEquals("Message successfully stored.", msg.sentMessage(3)); 
    
        
        
        
        
    }

    
}
