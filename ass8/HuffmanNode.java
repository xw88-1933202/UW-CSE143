// Xuqing Wu
// 3/15/2020
// CSE143
// TA: Eric Fan
// Assignment #8 part 1
//
// Class HuffmanNode for storing a single node of a binary 
// tree of integers and can be compared to each other

public class HuffmanNode implements Comparable<HuffmanNode> {
   public int data;
   // frequency
   public HuffmanNode zero;
   // left node
   public HuffmanNode one;
   // right node
   public int letter;
   // integer value of letter
   
   // construct a node with given data
   public HuffmanNode(int data) {
      this(data, null, null, -1);
   }
   
   // construct a node with given data and given letter
   public HuffmanNode(int data, int letter) {
      this(data, null, null, letter);
   }
   
   // construct a node with given data, left subtree, 
   // right subtree and given letter
   public HuffmanNode(int data, HuffmanNode zero, 
      HuffmanNode one, int letter) {
      this.data = data;
      this.zero = zero;
      this.one = one;
      this.letter = letter;
   }
   
   // allows to comapre two HuffmanNodes
   // lower frequencies are “less” than higher frequencies
   // if two frequencies are equal, they are considered equal
   public int compareTo(HuffmanNode other) {
      return this.data - other.data;
   }
}