// Xuqing Wu
// 1/14/2020
// CSE143
// TA: Eric Fan
// Assignment #1
//
// Class LetterInventory can be used to keep track of an inventory of letters of the alphabet.

public class LetterInventory {
   private int[] elementData;  //inventory of letters
   private int size;  //current number of alphabetic letters in the list
   public static final int NUM = 26;  
      
   //post: Constructs an inventory of the alphabetic letters in the given string, 
   //ignoring the case of letters and ignoring any non-alphabetic characters.
   public LetterInventory(String data) {
      elementData = new int[NUM];
      data = data.toLowerCase();
      for(int i = 0; i < data.length(); i++) {
         char ch = data.charAt(i);
         if(Character.isLetter(ch)) {
            elementData[ch - 97]++;
            size++;
         }
      } 
   }
   
   //pre: alphabetic character is passed(throw IllegalArgumentException if not)
   //post: Returns a count of how many of the letter given as 
   //      parameter are in the inventory. 
   public int get(char letter) {
      checkAlphabetic(letter);
      letter = Character.toLowerCase(letter);
      return elementData[letter - 97];
   }
   
   //pre: check that the given character is alphabetic, 
   //     throw IllegalArgumentException if not.
   public void checkAlphabetic(char letter) {
      if(!Character.isLetter(letter)){
         throw new IllegalArgumentException(letter + "is not letter");
      }
   }

   //pre: alphabetic character is passed(throw IllegalArgumentException if not)
   //     value >= 0(throw an IllegalArgumentException if not)
   //post: Sets the count for the given letter to the given value. 
   public void set(char letter, int value) {
      checkAlphabetic(letter);
      checkValue(value);
      letter = Character.toLowerCase(letter);
      size = size - elementData[letter - 97] + value;
      elementData[letter - 97] = value;
   }
   
   //pre: check that the value given is non negative
   //     (throw IllegalArgumentException if not)
   public void checkValue(int value) {
      if(value < 0){
         throw new IllegalArgumentException("value: " + value);
      }
   }
   
   //post: Returns the sum of all of the counts in this inventory
   public int size() {
      return size;
   }
   
   //post: Returns true if this inventory is empty (all counts are 0)
   public boolean isEmpty() {
      return size == 0;
   }
   
   //post: Returns a String representation of the inventory with the letters 
   //      all in lowercase and in sorted order and surrounded by square brackets.
   public String toString() {
      String result = "[";
      for(int i = 0; i < NUM; i++) {
         for(int j = 0; j < elementData[i]; j++) {
            char grade = (char) ('a' + i);
            result += grade;
         }
      }
      result += "]";
      return result;
   } 
   
   //post: Constructs and returns a new LetterInventory object 
   //      that represents the sum of this letter inventory and the other 
   //      given LetterInventory.
   public LetterInventory add(LetterInventory other) {
      LetterInventory added = new LetterInventory("");
      for(int i = 0; i < NUM; i++){
         added.elementData[i] = this.elementData[i] + other.elementData[i];  
      }
      added.size = this.size + other.size;
      return added;
   }
   
   //post: Constructs and returns a new LetterInventory object 
   //      that represents the result of subtracting the other 
   //      inventory from this inventory. If any resulting count 
   //      is negative, return null.
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory subtracted = new LetterInventory("");
      for(int i = 0; i < NUM; i++){
         if(this.elementData[i] < other.elementData[i]){
            size = 0;
            return null;
         }
         else{
            subtracted.elementData[i] = this.elementData[i] - other.elementData[i]; 
            subtracted.size = this.size - other.size;
         }
      }
      return subtracted;
   }                                                                                                                                                                                                                        
}                