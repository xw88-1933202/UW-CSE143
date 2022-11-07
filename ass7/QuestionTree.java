// Xuqing Wu
// 3/5/2020
// CSE143
// TA: Eric Fan
// Assignment #7 part 2
//
// Class QuestionTree allows client to play a yes/no guessing game.
// The class constructs a binary tree that distinguishes between 
// the objects. The computer will try to guess the object clients 
// think by asking a series of yes or no questions. It updates the 
// tree when it asks a question. Eventually the computer will have 
// asked enough questions that it thinks it knows what object clients 
// are thinking of. It will make a guess. If this guess is correct, the 
// computer wins; if not, clients win.


import java.util.*;
import java.io.*;

public class QuestionTree {
   private QuestionNode overallRoot;  
   // binary tree which stores questions and answers
   private Scanner console;
   // scanner to get the response of user
   
   // post: Construct a binary tree with one leaf node representing “computer”
   public QuestionTree() {
      overallRoot = new QuestionNode("computer");
      console = new Scanner(System.in);
   }
      
   // post: asks the user a question, forcing an answer of "y " or "n"; 
   // returns true if the answer was yes, returns false otherwise 
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase(); 
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n."); 
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }

   // post: replace the current tree by reading another tree from a file.
   // A Scanner that link to the file is passed. 
   public void read(Scanner input) {
      overallRoot = readHelper(input);
   }
      
   // post: the helper method that read information from scanner passed 
   // and construct a new tree   
   private QuestionNode readHelper(Scanner input) {   
      QuestionNode root = null;
      if(input.hasNextLine()) {
         String type = input.nextLine();
         String qOrA = input.nextLine();
         if(type.contains("Q:")) {
            root = new QuestionNode(qOrA, readHelper(input), 
               readHelper(input)); 
         }
         else {
            root = new QuestionNode(qOrA);
         }
      }
      return root;
   }
   
   // post: store the current tree to an output file using passed PrintStream
   public void write(PrintStream output) {
      write(output, overallRoot);
   }
   
   // post: helper method that store the current tree to an output file 
   private void write(PrintStream output, QuestionNode root) {
      if(root != null) {
         if(root.data.contains("?")) {
            output.println("Q: ");
         }
         else {
            output.println("A: ");
         }
         output.println(root.data);
         write(output, root.left);
         write(output, root.right);
      }
   }
      
   // post: use the current tree to ask the user a series of yes/no questions
   // until computer either guess their object correctly or until fail, 
   // in which case expand the tree to include their object and a new question
   // to distinguish their object from the others
   public void askQuestions() {
      overallRoot = askQuestions(overallRoot);
   }
   
   // post: the helper method asks user questions and get response from 
   // users and update the tree with qustions and answers from user when it 
   // fails to guess the object
   private QuestionNode askQuestions(QuestionNode root) {
      if(root.left == null || root.right == null) {
         if(yesTo("Would your object happen to be " + root.data + "?")) {
            System.out.println("Great, I got it right!");
         }
         else {
            System.out.print("What is the name of your object? ");
            String animal = console.nextLine();
            QuestionNode curr = new QuestionNode(animal);
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String question = console.nextLine();
            if(yesTo("And what is the answer for your object?")) {
               root = new QuestionNode(question, curr, root);
            }
            else {
               root = new QuestionNode(question, root, curr);
            }
         }
      }
      else {
         if(yesTo(root.data)) {
            root.left = askQuestions(root.left);
         }
         else {
            root.right = askQuestions(root.right);
         }
      }
      return root;   
   }
}
     