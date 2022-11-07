// Xuqing Wu
// 2/9/2020
// CSE143
// TA: Eric Fan
// Assignment #5
//
// Class GrammarSolver allows a client to randomly generate 
// different grammar types sentence for as many times they want 
// by reading an input file with a grammar in Backus-Naur Form.

import java.util.*;

public class GrammarSolver {
   private SortedMap<String, List<String[]>> nonterminalToRules;  
   //key of map is nonterminal symbol and value of map is 
   //rules which belong to the symbol
  
   //pre: the list of string passed is not empty and there 
   //     is only one entry in the list for one nonterminal
   //     symbol(throw an IllegalArgumentException if not)
   //post: store the list of grammar passed in a convenient way 
   //      through splitting strings. The list passed is not changed.
   public GrammarSolver(List<String> grammar) {
      if(grammar.isEmpty()) {
         throw new IllegalArgumentException("Grammar is empty!");
      }
      nonterminalToRules = new TreeMap<>();
      for(String form: grammar) {
         String[] parts = form.split("::=");
         if(nonterminalToRules.containsKey(parts[0])) {
            throw new IllegalArgumentException
               ("Two or more entries for the same nonterminal!");
         }
         List<String[]> ruleElement = new ArrayList<>(); 
         String[] rules = parts[1].split("[|]");      
         for (int i = 0; i < rules.length; i++) {
            ruleElement.add(rules[i].trim().split("[ \t]+"));
         }
         nonterminalToRules.put(parts[0], ruleElement);
      }
   }   
   
   //post: return true if the passed string is a nonterminal of the grammar
   public boolean grammarContains(String symbol) {
      return nonterminalToRules.containsKey(symbol);
   }
   
   //post: return a string to represent the nonterminal symbols from the 
   //      grammar. its form should be sorted, comma-separated list enclosed
   //      in square brackets
   public String getSymbols() {
      return nonterminalToRules.keySet().toString();
   }
   
   //pre: the grammar contains the passed string and the 
   //     number of times passed is more than or equal to 0
   //     (throw an IllegalArgumentException if not)
   //post: return the sentences being asked to generate as an array of strings
   //      by randomly generating sentences of the passed symbol for the 
   //      passed number of times, each rule of nonterminal symbol should 
   //      have equal probability to be chosen
   public String[] generate(String symbol, int times) {
      if(times < 0 || !grammarContains(symbol)) {
         throw new IllegalArgumentException();
      }
      String[] result = new String[times];
      for(int i = 0; i < times; i++) {
         result[i] = getString(symbol);
      }
      return result;
   }
   
   //post: generate one sentence of the symbol passed using recursing method
   //      and return that string, there is one space between each terminal 
   //      and no leading or trailing spaces in the string
   private String getString(String symbol) {
      Random rand = new Random();
      if(!grammarContains(symbol)) {
         return symbol;
      }
      int range = nonterminalToRules.get(symbol).size();
      int num = rand.nextInt(range);
      String str = "";
      for(int i = 0; i < nonterminalToRules.get(symbol).get(num).length; i++) {
         String added = nonterminalToRules.get(symbol).get(num)[i];
         str = str + getString(added) + " ";
      }
      return str.trim();
   }
}