/* Juan Carlos T. Matias
IT201A */

import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.math.*;

public class LabExer6A {

    public static void GuessTime() {

        // Variables Area
        Scanner s = new Scanner(System.in);

        String filename = "D:\\VS Studio Code Workshop\\2ND SEM MIDTERMS\\LabExer6A\\words.txt";
        Path pathfile = Paths.get(filename.toString());     // Converts the filepath to String (removes extra \)

        String temporary[] = new String[100];
        String lines[];
        String hidden;
        String show;
        String guess;

        Random rd = new Random();

        boolean cond = true;
        boolean guessedletter = false;

        int i = 0;
        int j = (int)((Math.random() * 11) + 1);

        System.out.println("Welcome to the Genshin Character Guessing Game!");

        try {
            InputStream is = Files.newInputStream(pathfile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));      // Reads the text file specified in the filepath

            String word = null;

            while ((word = br.readLine()) != null) {
                temporary[i] = word;    // Reads every line in the words text file until it's empty/null
                ++i;
            }

            lines = new String[i];  
            lines = temporary;  // Extracts the data from the words text file

            hidden = lines[j];  // Picks a random line from the words text file
            show = hidden;

            for (int k = 0; k < hidden.length()-2; k++) {
                show = show.replace(show.charAt(rd.nextInt(show.length())),'?');    // Replaces some of the given character with "?"
            }

            System.out.println("The character you have to guess is: " + show);

            do {    // Do while statement. The program will continuously run until the user guesses the correct character!
                
                System.out.print("Enter a letter or Guess the word: ");     // Scanner for inputting a letter/the word!
                guess = s.nextLine();

                if (guess.length() > 1) {

                    if (guess.equalsIgnoreCase(hidden)) {
                        System.out.println("Correct! The character is: " + hidden + "!");
                        System.out.println("Congratulations! You've guessed the character!!!");     // Correct guess condition
                        System.exit(0);
                    }

                    else {
                        System.out.println("Sorry, the correct character was: " + hidden + " :(");      // Wrong guess condition
                        System.out.println("Better luck next time!");
                        System.exit(0);
                    }

                }

                else {  // Condition if the user guesses a single character instead
                    guessedletter = false;
                    for (int k = 0; k < hidden.length(); k++ ) {
                        if (guess.equalsIgnoreCase(String.valueOf(hidden.charAt(k)))) {
                            show = show.substring(0, k) + guess + show.substring(k+1);      // Replaces a "?" if a correct letter was guessed
                            guessedletter = true;
                        }
                    }
                }

                if (guessedletter == true) {
                    System.out.println("You've guessed " + guess.toUpperCase() + " correctly!");    // Shows that the user guessed a correct letter
                    System.out.println(show);                                                       // Along with the word with replaced letters instead of "?"
                }

                else {
                    System.out.println("You've guessed a wrong letter. Try again!");    // Shows the user that they guessed a wrong letter
                    System.out.println(show);   
                }

            } 
            while (cond = true);

        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());    // Exception handler
        }

    }
    public static void main(String[] args) {    // Main Method
        GuessTime();
    }
}