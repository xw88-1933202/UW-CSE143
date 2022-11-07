// Xuqing Wu
// 1/29/2020
// CSE143
// TA: Eric Fan
// Assignment #3
//
// Class AssassinManager allows a client to manage a game 
// of assassin through keeping track of who is stalking whom 
// and the history of who killed whom

import java.util.*;

public class AssassinManager {
   private AssassinNode alive;   //stores names of people that still alive
   private AssassinNode dead;    //stores names of people that are killed 
      
   //pre: the list of name passed is not empty
   //(throw IllegalArgumentException if not)
   //post: add the names from the list into the 
   //kill ring in the same order in which they 
   //appear in the list
   public AssassinManager(List<String> names) {
      if(names == null) {
         throw new IllegalArgumentException();      
      }
      for(int i = names.size() - 1; i >= 0; i--) {
         if(alive == null) {
            alive = new AssassinNode(names.get(i));
         }
         else {
            alive = new AssassinNode(names.get(i), alive);
         }
      }
   }
   
   //post: print the names of the people in the kill ring
   //in the form “<name> is stalking <name>"
   public void printKillRing() {
      AssassinNode current = alive; 
      while(current.next != null) {
         System.out.println("    " + current.name + " is stalking " + current.next.name);
         current = current.next;
      }
      System.out.println("    " + current.name + " is stalking " + alive.name);
   }
   
   //post: print the names of the people in the graveyard
   //in the form “<name> was killed by <name>” in reverse 
   //kill order, produce no output if the graveyard is empty
   public void printGraveyard() {
      AssassinNode current = dead;      
      while(current != null) {
         System.out.println("    " + current.name + " was killed by " + current.killer);
         current = current.next;
      }  
   }
   
   //post: return true if the given name is in the current 
   //kill ring and return false otherwise. Ignore case in 
   //comparing names.
   public boolean killRingContains(String name) {
      return contains(name, alive);
   }
   
   //post: return true if the given name is in the current 
   //graveyard and return false otherwise. Ignore case in 
   //comparing names.
   public boolean graveyardContains(String name) {     
      return contains(name, dead);
   }
   
   //post: The method in order to reduce redundancy of the 
   //previous two methods.
   private boolean contains(String name, AssassinNode in) {
      AssassinNode current = in;
      while(current != null) {
         if(current.name.equalsIgnoreCase(name)) {
            return true;
         }
         current = current.next;
      }
      return false;
   }
   
   //post: return true if the game is over(kill ring has just 
   //one person in it)and return false otherwise.
   public boolean gameOver() {
      return alive.next == null;
   }
   
   //post: return the name of the winner of the game. Return 
   //null if the game is not over.
   public String winner() {
      if(gameOver()) {
         return alive.name;
      }
      else {
         return null;
      }
   }
      
   //pre: the given name is part of the current kill ring
   //(throw IllegalArgumentException if not)
   //game is not over(throw IllegalStateException if not)
   //post: record the killing of the person with the given name,
   //transferring the person from the kill ring to the graveyard.
   //Ignore case in comparing names.
   public void kill(String name) {
      if(!killRingContains(name)) {
         throw new IllegalArgumentException();
      }
      if(gameOver()) {
         throw new IllegalStateException();
      }     
      AssassinNode current = alive;
      AssassinNode killed = null;
      if(alive.name.equalsIgnoreCase(name)) {
         killed = alive;
         while(current.next != null) {
            current = current.next;
         }
         killed.killer = current.name;
         alive = alive.next;
      }
      else {      
         while(!current.next.name.equalsIgnoreCase(name)) {
            current = current.next;
         }
         killed = current.next;
         killed.killer = current.name;
         if(current.next.next == null) {
            current.next = null;
         }
         else {
            current.next = current.next.next;
         }
      }
      killed.next = dead;
      dead = killed;
   }
}                         
