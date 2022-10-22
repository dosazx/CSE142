import java.util.*;
import java.io.*;

public class Personality {
   public static void main(String[] args) throws FileNotFoundException {
   Scanner console = new Scanner(System.in);
   
   intro(); 
   
   System.out.print("input file name? ");
   Scanner input = new Scanner(new File(console.nextLine()));
   System.out.print("output file name? ");
   PrintStream output = new PrintStream(new File(console.nextLine()));
     
   while (input.hasNextLine()) {
      String name = input.nextLine();
      String responses = input.nextLine().toUpperCase();
      int[] countA = new int[4];
      int[] countB = new int[4];
      tally(responses, countA, countB);
      int[] percentage = computeB(countA, countB);
      String[] personalityType = personality(percentage);
      printResults(output, name, percentage, personalityType);      
   }
}
   public static void tally(String responses, int[] countA, int[] countB) {
     for (int i = 0; i < responses.length(); i++) {
         char letter = responses.charAt(i);
            if (letter == 'A') {
               if (i % 7 == 0) {
                  countA[0]++;
               } else if (i % 7 == 1 || i % 7 == 2) {
                  countA[1]++;
               } else if (i % 7 == 3 || i % 7 == 4) {
                  countA[2]++;
               } else if (i % 7 == 5 || i % 7 == 6) {
                  countA[3]++;
               }
            } else if (letter == 'B') {
                if (i % 7 == 0) {
                  countB[0]++;
               } else if (i % 7 == 1 || i % 7 == 2) {
                  countB[1]++;
               } else if (i % 7 == 3 || i % 7 == 4) {
                  countB[2]++;
               } else if (i % 7 == 5 || i % 7 == 6) {
                  countB[3]++;
               }
            }         
         }
      }
  
   public static int[] computeB(int[] countA, int[] countB) {
      int[] percentage = new int[4];
         for (int i = 0; i < percentage.length; i++) {
            double num = (countB[i] * 100.0 / (countB[i] + countA[i]));
            int percent = (int) Math.round(num);
            percentage[i] = percent;
         }
         return percentage;
   }
  
   public static String[] personality(int[] percentage) {
   String[] personality = new String[4];
      for (int i = 0; i < personality.length; i++) {
         if (percentage[i] > 50) {
            if (i == 0) {
               personality[0] = "I";
            } else if (i == 1) {
               personality[1] = "N";
            } else if (i == 2) {
               personality[2] = "F";
            } else {
               personality[3] = "P";
            }
         } else if (percentage[i] < 50) {
            if (i == 0) {
               personality[0] = "E";
            } else if (i == 1) {
               personality[1] = "S";
            } else if (i == 2) {
               personality[2] = "T";
            } else {
            personality[3] = "J";
            }
         } else {
            personality[i] = "X";
         }
      }
      return personality;
   }
  
   public static void printResults(PrintStream output, String name, int[] percentage, String[] personalityType) {
      output.print(name + ": ");
      output.print(Arrays.toString(percentage) + " = ");
   
      for (int i = 0; i < personalityType.length; i++) {
         output.print(personalityType[i]);   
      }
      output.println();
   }
   
   public static void intro() {
      System.out.println("This program processes a file of answers to the");
      System.out.println("Keirsey Temperament Sorter. It converts the");
      System.out.println("various A and B answers for each person into");
      System.out.println("a sequence of B-percentages and then into a");
      System.out.println("four-letter personality type."); 
      System.out.println();
   }
}     
        
