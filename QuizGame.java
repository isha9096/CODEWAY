import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame {
    private static int score = 0;
    private static int currentQuestion = 0;
    private static final int TIME_LIMIT = 10; // Time limit per question in seconds

    private static final String[] questions = {
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "Who wrote 'Romeo and Juliet'?"
    };

    private static final String[][] options = {
        {"A. London", "B. Paris", "C. Rome", "D. Madrid"},
        {"A. Venus", "B. Mars", "C. Jupiter", "D. Saturn"},
        {"A. William Shakespeare", "B. Charles Dickens", "C. Jane Austen", "D. Mark Twain"}
    };

    private static final int[] answers = {1, 2, 0}; // Index of correct answers for each question

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Quiz Game!");

        while (currentQuestion < questions.length) {
            displayQuestion();
            startTimer();

            int userChoice = scanner.nextInt();
            checkAnswer(userChoice);

            currentQuestion++;
        }

        System.out.println("Quiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.length);
        scanner.close();
    }

    private static void displayQuestion() {
        System.out.println("\nQuestion " + (currentQuestion + 1) + ": " + questions[currentQuestion]);
        for (String option : options[currentQuestion]) {
            System.out.println(option);
        }
        System.out.print("Your answer (Enter option number): ");
    }

    private static void startTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int timeRemaining = TIME_LIMIT;

            @Override
            public void run() {
                if (timeRemaining > 0) {
                    System.out.println("Time remaining: " + timeRemaining + " seconds");
                    timeRemaining--;
                } else {
                    timer.cancel();
                    System.out.println("\nTime's up!");
                    checkAnswer(0); // Automatically mark as incorrect when time's up
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    private static void checkAnswer(int userChoice) {
        if (userChoice == answers[currentQuestion]) {
            System.out.println("Correct!");
            score++;
        } else {
            if (userChoice == 0) {
                System.out.println("You ran out of time. The correct answer was option " + (answers[currentQuestion] + 1));
            } else {
                System.out.println("Incorrect. The correct answer was option " + (answers[currentQuestion] + 1));
            }
        }
    }
}
