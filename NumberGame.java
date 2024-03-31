import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attemptsLimit = 5;
        int score = 0;
        
        System.out.println("Welcome to the Number Guessing Game!");
        
        while (true) {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            
            System.out.println("A random number between " + minRange + " and " + maxRange + " has been generated.");
            
            while (attempts < attemptsLimit) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                
                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number!");
                    score++;
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
                
                attempts++;
            }
            
            if (attempts == attemptsLimit) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            }
            
            System.out.print("Your score: " + score);
            
            System.out.print("\n\nWould you like to play again? (yes/no): ");
            String playAgain = scanner.next();
            
            if (!playAgain.equalsIgnoreCase("yes")) {
                System.out.println("Thanks for playing! Your final score: " + score);
                break;
            }
        }
        
        scanner.close();
    }
}
