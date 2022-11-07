// Xuqing Wu
// 3/5/2020
// CSE143
// TA: Eric Fan
// Assignment #7 part 1
//
// Class QuestionNode for storing a single node of a binary tree of Strings

public class QuestionNode {
    public String data;
    public QuestionNode left;
    public QuestionNode right;
                
    // constructs a leaf node with given data
    public QuestionNode(String data) {
        this(data, null, null);
    }
                        
    // constructs a branch node with given data, left subtree, right subtree
    public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}  