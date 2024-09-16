//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static String secretWord = input.nextLine();
    private static String currentWord = "";
    private static String guessedLetters = "";
    private static int guessesLeft = 5;


    public static void main(String[] args) {
        System.out.println("Welcome to Hangman!");
        initializeWord();
        while (guessesLeft > 0)
        {
            printCurrentState();
            System.out.print("Enter a letter: ");
            char letter = input.nextLine().charAt(0);
            char lower = Character.toLowerCase(letter);
            if (!Character.isLetter(lower))
            {
                System.out.println("Letters only!");
                continue;
            }

            if (addGuess(lower) == false)
            {
                System.out.println("You already guessed " + letter + "!");
                continue;
            }

            if (!checkGuess(lower))
            {
                guessesLeft--;
            }
            else
            {
                currentWord = updateWord(lower);
                if (checkForWin())
                {
                    break;
                }
            }


        }
        if (guessesLeft > 0)
        {
            System.out.println(currentWord);
            System.out.println("Congratulations! You got the secret word!");
        }
        else
        {
            System.out.println("Sorry, you lose!");
            System.out.println("The secret word was: " + secretWord);
        }
    }




    public static void printCurrentState()
    {
        System.out.println("");
        System.out.println("");
        System.out.println("Word: " + currentWord);
        System.out.println(guessesLeft + " incorrect guesses left");
        System.out.println("Guessed letters: " + guessedLetters);
        System.out.println("");
        System.out.println("");
    }

    public static String updateWord(char guess)
    {
        String newString = "";
        for(int i = 0; i < currentWord.length(); i++)
        {
            if (Character.isLetter(currentWord.charAt(i)))
            {
                newString += currentWord.charAt(i);
            }
            else if (Character.isWhitespace(currentWord.charAt(i)))
            {
                newString += "" + currentWord.charAt(i);
            }
            else if (secretWord.charAt(i/2) == guess)
            {
                newString += guess;
            }
            else
            {
                newString += '_';
            }
        }
        return newString;
    }

    public static boolean checkGuess(char guess)
    {
        if(secretWord.indexOf(guess) >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean checkForWin()
    {
        if (currentWord.indexOf('_') >= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static void initializeWord()
    {
        for (int i = 0; i < secretWord.length(); i++)
        {
            currentWord += '_';
            currentWord += " ";
        }
    }

    public static boolean addGuess(char guess)
    {

        if (guessedLetters.indexOf(guess) >= 0)
        {
            return false;
        }
        else
        {

            guessedLetters += guess;
            return true;
        }
    }

}