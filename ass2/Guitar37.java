// Xuqing Wu
// 1/22/2020
// CSE143
// TA: Eric Fan
// Assignment #2
//
// Class Guitar37 keeps track of a musical instrument
// with 37 strings. It implements guitar class to 
// process pitch and frequency and play the tune.

public class Guitar37 implements Guitar {
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   public static final int TOTAL = 37;
   private GuitarString[] stringAll;   //construct the array to store guitar string
   private int num;   //number of times tic has been called
   
   //post: create 37 guitar strings with different frequency
   public Guitar37() {
      stringAll = new GuitarString[TOTAL];
      for(int i = 0; i < TOTAL; i++) {
         stringAll[i] = new GuitarString(440.0 * Math.pow(2, (i - 24) / 12.0));
      }
   }
   
   //post: specify which note to play by passing a pitch
   //ignore pitch if it can’t be played
   public void playNote(int pitch) {
      int i = pitch + 24;
      if(i >= 0 && i < TOTAL) {
         stringAll[i].pluck();
      }  
   }
   
   //post: return true if the character passed has a 
   //corresponding string 
   public boolean hasString(char string) {
      return KEYBOARD.indexOf(string) != -1;
   }
   
   //pre: the char given is contained in the string KEYBOARD
   //post: indicates which note to play by processing the character passed
   public void pluck(char string) {
      if(! hasString(string)) {
         throw new IllegalArgumentException();
      }
      for(int i = 0; i < TOTAL; i++) {
         if(string == KEYBOARD.charAt(i)) {
            stringAll[i].pluck();
         }
      }   
   }
   
   //post: return the sum of all samples from the strings
   public double sample() {
      double all = 0.0;
      for(int i = 0; i < TOTAL; i++) {
         all += stringAll[i].sample();
      }
      return all;
   }
   
   //post: advance the time forward one “tic”
   public void tic() {
      for(int i = 0; i < TOTAL; i++) {
         stringAll[i].tic();
      }
      num++;
   }
   
   //post: returns the number of times tic has been called
   public int time() {
      return num;
   } 
}