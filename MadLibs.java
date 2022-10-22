/ Simulates a game of Mad Libs, which prompts the user 
// for input that substitutes placeholders in a story
// without the user knowing the full story. After the 
// placeholders are entered, the program writes the input along 
// with the rest of the story to an output file. Afterwards, the 
// user can view the created mad lib or quit the program.

import java.io.*;
import java.util.*;

public class MadLibs {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      boolean runGame = true;
      
      intro();
      
      // Prompts user to create or view a mad lib 
      // until the user decides to stop quit playing.
      while (runGame == true) {
         System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
         String option = console.nextLine();
      
         if (option.toLowerCase().equals("q")) {
            System.out.println();
            runGame = false;     
         } else if (option.toLowerCase().equals("v")) {
            view(console);
            runGame = true;
         } else if (option.toLowerCase().equals("c")) {
            create(console);
            runGame = true;
         } else {
            runGame = true;
         }
      }
   }
    
   // Prints out Mad Libs program introductory message.
   public static void intro() {
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");
      System.out.println();
   }
    
   // Prints out contents of a requested file to the console.
   // Scanner console: scanner used to retrieve user input and 
   // read contents of a file
   public static void view(Scanner console) throws FileNotFoundException {   
      File viewFile = getFile(console);
      System.out.println();
      Scanner input = new Scanner(viewFile);
      while (input.hasNextLine()) {
         String line = input.nextLine();
         System.out.println(line);
      }
      System.out.println();
   }
    
   // Allows user to create a Mad Lib and prompts user for a 
   // file that contains a story with placeholders. User is
   // prompted to enter words that replace the placeholders.
   // Afterwards, the user is prompted for an output file name
   // and prints the resulting text to the output file.
   // Scanner console: scanner used to retrieve user input and 
   // read contents of a file.
   public static void create(Scanner console) throws FileNotFoundException {
      File viewFile = getFile(console);
      Scanner input = new Scanner(viewFile);
      System.out.print("Output file name: ");
      String outputFile = console.nextLine();
      PrintStream output = new PrintStream(new File(outputFile));
      System.out.println();

      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner lineScan = new Scanner(line);
         while (lineScan.hasNext()) {
            String token = lineScan.next();
            if (token.startsWith("<") && token.endsWith(">")) {            
               token = token.substring(1, token.length() - 1);
               token = token.replace("-", " ");
               if (token.toLowerCase().startsWith("a") || 
                   token.toLowerCase().startsWith("e") ||
                   token.toLowerCase().startsWith("i") ||
                   token.toLowerCase().startsWith("o") ||
                   token.toLowerCase().startsWith("u")) {
                  System.out.print("Please type an " + token + ": ");
                  token = console.nextLine();
               } else {
                  System.out.print("Please type a " + token + ": ");
                  token = console.nextLine();
               }                                   
            }
            output.print(token + " ");         
         }
         output.println();                  
      } 
      System.out.println("Your mad-lib has been created!");
      System.out.println();
   }

   // Prompts user for a file name and reads its contents
   // Scanner console: scanner used to retrieve user input and 
   // read contents of a file.
   // Returns the contents of the file that was requested
   public static File getFile(Scanner console) {
      System.out.print("Input file name: ");
      String inputFile = console.nextLine();
      File viewFile = new File(inputFile);
      
      while (!viewFile.exists()) {
         System.out.print("File not found. Try again: ");
         inputFile = console.nextLine();
         viewFile = new File(inputFile);         
      }
      return viewFile;
   } 
}
