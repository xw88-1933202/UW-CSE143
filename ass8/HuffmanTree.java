// Xuqing Wu
// 3/15/2020
// CSE143
// TA: Eric Fan
// Assignment #8 part 2
//
// Class HuffmanTree allows client to compress text files by using a coding 
// scheme called Huffman coding based on the frequency of characters. 
// Instead of using the usual seven or eight bits per character, Huffman's 
// method uses only a few bits for characters that are used often, more bits 
// for those that are rarely used

import java.util.*;
import java.io.*;

public class HuffmanTree {
   private HuffmanNode tree;  // overall root 
      
   // post: construct Huffman tree using the given array of frequencies where
   // count[i] is the number of occurrences of the character with integer 
   // value i. The use of priority queue helps figure out sequence to add 
   // nodes. Add a eof character which has value one higher than the value
   // of the highest character in the array passed at the end of queue 
   // for convenience
   public HuffmanTree(int[] count) {
      Queue<HuffmanNode> sequence = new PriorityQueue<>();
      for(int i = 0; i < count.length; i++) {
         if(count[i] > 0) {
            HuffmanNode cur = new HuffmanNode(count[i], i);
            sequence.add(cur);
         }
      }
      int max = count.length;
      sequence.add(new HuffmanNode(1, max));
      while(sequence.size() > 1) {
         HuffmanNode first = sequence.remove();
         HuffmanNode second = sequence.remove();
         HuffmanNode total = new HuffmanNode(first.data + second.data,
            first, second, 0);
         sequence.add(total);          
      }
      tree = sequence.remove();
   }
      
   // post: write tree to the given output stream in standard format
   // standard format is a series of pairs of lines where the first line has
   // an integer representing the character’s integer value and the second
   // line has the code to use for that character.
   public void write(PrintStream output) {      
      write(output, tree, "");
   }
      
   // post: private method of write to write tree to the given output 
   // stream in standard format with given tree and frequency
   private void write(PrintStream output, HuffmanNode tree, String 
      frequency) {   
      if(tree.zero == null && tree.one == null) {
         output.println(tree.letter);
         output.println(frequency);
      }
      else {
         write(output, tree.zero, frequency + "0");
         write(output, tree.one, frequency + "1");
      }
   }   
   
   // post: reconstruct the tree from the given input file. The Scanner
   // contains a tree stored in standard format. Frequencies are irrelevant 
   // so all of the frequencies are set to -1.
   public HuffmanTree(Scanner input) {
      tree = null;
      while(input.hasNextLine()) {
         int data = Integer.parseInt(input.nextLine()); 
         String code = input.nextLine();
         tree = constructorHelper(data, code, tree);
      }
   }      
         
   // post: private method of constructor to reconstruct the tree with given
   // tree, frequency and tree and return it. 
   private HuffmanNode constructorHelper(int data, String code, 
      HuffmanNode current) { 
      if(code.length() == 0) {
         current = new HuffmanNode(0, data);
      }
      else {
         if(current == null) {
            current = new HuffmanNode(0);
         }
         if(code.charAt(0) == '0') {
            current.zero = constructorHelper(data, code.substring(1), 
               current.zero);
         }
         else {
            current.one = constructorHelper(data, code.substring(1), 
               current.one);
         }
      }
      return current;
   }
   
   // post: read individual bits from the input stream and write
   // corresponding characters to the output. Stop reading when encounter
   // a character with value equal to the eof parameter. Assume that the 
   // input stream contains a legal encoding of characters.
   public void decode(BitInputStream input, PrintStream output, int eof) {
      HuffmanNode current = tree;
      while(current.letter < eof) {
         if(current.zero == null && current.one == null) {
            output.write(current.letter);
            current = tree;
         }
         else {
            if(input.readBit() == 0) {
               current = current.zero;
            }
            else {
               current = current.one;
            }
         }
      }
   }
}