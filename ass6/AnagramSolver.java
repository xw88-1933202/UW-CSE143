// Xuqing Wu
// 2/27/2020
// CSE143
// TA: Eric Fan
// Assignment #6
//
// Class AnagramSolver allows clients use a given dictionary to 
// find all combinations of certain number of words that have 
// the exactly same letters as the given phrase and print them in 
// same order as the dictionary

import java.util.*;

public class AnagramSolver {
   private Map<String, LetterInventory> map;    //map to store all
      // strings from the given list and their corresponding letterinventory
   private List<String> replicate; //list contains all words from dictionary
      
   // post: It is the constructor that uses the given list as its dictionary
   //       and put all word from list and their letterinventory in to the map.
   //       The list is unchanged. The dictionary is a nonempty collection
   //       of nonempty sequences of letters and contains no duplicates
   public AnagramSolver(List<String> list) {
      replicate = list;
      map = new HashMap<>();
      for(int i = 0; i < list.size(); i++) {
         String word = list.get(i);
         LetterInventory single = new LetterInventory(word);
         map.put(word, single);
      }
   }
   
   // pre: max is bigger than or equal 0
   //      (throw an IllegalArgumentException if not)
   // post: Find all combinations of words that have the 
   //       same letters as the given string and print them out.
   //       All combinations from the dictionary that are anagrams of 
   //       the string include at most the passed integer "max" words 
   //       (unlimited number of words if max is 0). First filter out 
   //       words that are included in the string. 
   public void print(String s, int max) {
      if(max < 0) { 
         throw new IllegalArgumentException
            ("Max words to include is smaller than 0!");
      }
      LetterInventory target = new LetterInventory(s);
      LetterInventory current = new LetterInventory("");
      List<String> list = new LinkedList<>();
      Stack<String> stack = new Stack<>();
      for(String single: replicate) {
         LetterInventory letters = map.get(single);
         if(target.subtract(letters) != null) {
            list.add(single);
         }
      }
      print(target, current, max, list, stack);
   }
   
   // post: the helper method of the public print method. Use 
   //       recursive backtracking to find combinations of words
   //       that have the same letters as the given string. Use satck to
   //       store combinations, use list to get all related words, use 
   //       Letterinventorys to keep track of what letters are left to fill.
   private void print(LetterInventory target, LetterInventory current,
      int max, List<String> list, Stack<String> stack) {
      if(target.isEmpty()) {
         System.out.println(stack);
      }
      if(max == 0 || max != stack.size()) {
         for(int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            current = new LetterInventory(s);
            if(target.subtract(current) != null) {
               stack.push(s);
               print(target.subtract(current), current, max, list, stack);
               stack.pop();
            }                          
         }
      }
   }
}                