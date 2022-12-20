package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import javax.swing.*;
import javax.swing.text.Position;

public class SuperSnake {

    public static void main(String[] args) throws Exception {
        // Creating terminal
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false); // Make cursor invisible

        //Snake setup
        List<SnakePart> snake = new ArrayList<>(); // Arraylist contains variables (coordinates of snake start position)
        snake.add(new SnakePart(5,10));
        snake.add(new SnakePart(6,10));
        snake.add(new SnakePart(7,10));
        snake.add(new SnakePart(8,10));
        snake.add(new SnakePart(9,10));
        snake.add(new SnakePart(10,10));
        for (SnakePart snakePart : snake) { // Running through array
            snakePart.printPart(terminal); // Print snake
        }

        // Apple placement
        Apple apple = new Apple(terminal); // ?? Calling from Apple class. Placing apple in terminal at random position

        terminal.flush();

        // SNAKE MOVEMENT

        SnakePart newHead; // The variable that control head of the snake. Is initiated with every movement of the snake.

        boolean alive = true;

        while (alive) { // Method to be able to walk more than one step
            KeyStroke keyStroke = null; // Defined as null because the user have not used Key function yet
            do { // In order to not use CPU power on continuously checking for KeyStroke, we created a 5 m.sec pause between every check.
                Thread.sleep(5); // Take a break for 5 m.sec
                keyStroke = terminal.pollInput(); // Checking for input every 5 m.sec
            }
            while (keyStroke == null); // Keeps looping as long as there's no keystroke


            SnakePart oldHead = snake.get(snake.size()-1); // The body variable that removes the end of snake tail after movement, so following heads instructions

            // Takes input and navigates inside the terminal
            switch (keyStroke.getKeyType()){
                case ArrowDown:
                    newHead = new SnakePart(oldHead.getX(), oldHead.getY() + 1); // Call class SnakePart. Add one to Y axis to go down (upper row is 0)

                    if (newHead.isCollision(snake)){ // When newHead is equal to snake the game is over
                        // GAME OVER
                        alive = false; // When alive is false the while loop ends the game
                    }

                    snake.add(newHead); // Adding the new placement of head in the array. Makes the array one index bigger.
                    snake.get(0).removePart(terminal); // Removing the last part in terminal.
                    newHead.printPart(terminal); // Prints new head.

                    if(apple.isApple(newHead)) {
                        apple = new Apple(terminal);
                        // score
                    }
                    else {
                        snake.remove(0); // Removing the last part of the tail (index 0) from array.
                        // Makes the array return back to its real size.
                        // When removing index 0 the array adjusts by moving all index numbers down one spot.
                    }
                    break;
                case ArrowRight:
                    newHead = new SnakePart(oldHead.getX() + 1, oldHead.getY());

                    if (newHead.isCollision(snake)){
                        // GAME OVER
                        alive = false;
                    }

                    snake.add(newHead);
                    snake.get(0).removePart(terminal);
                    newHead.printPart(terminal);

                    if(apple.isApple(newHead)) {
                        apple = new Apple(terminal);
                        // score
                    }
                    else {
                        snake.remove(0);
                    }
                    break;
                case ArrowUp:
                    newHead = new SnakePart(oldHead.getX(), oldHead.getY() - 1);

                    if (newHead.isCollision(snake)){
                        // GAME OVER
                        alive = false;
                    }

                    snake.add(newHead);
                    snake.get(0).removePart(terminal);
                    newHead.printPart(terminal);

                    if(apple.isApple(newHead)) {
                        apple = new Apple(terminal);
                        // score
                    }
                    else {
                        snake.remove(0);
                    }
                    break;
                case ArrowLeft:
                    newHead = new SnakePart(oldHead.getX() - 1, oldHead.getY());

                    if (newHead.isCollision(snake)){
                        // GAME OVER
                        alive = false;
                    }

                    snake.add(newHead);
                    snake.get(0).removePart(terminal);
                    newHead.printPart(terminal);

                    if(apple.isApple(newHead)) {
                        apple = new Apple(terminal);
                        // score
                    }
                    else {
                        snake.remove(0);
                    }
                    break;
            }
            terminal.flush(); // Prints all code above into terminal
        }
        // When Game Over
        for (SnakePart snakePart : snake) { // Loop through snake array
            snakePart.removePart(terminal); // Removing all the snake parts that is found in the loop
        }
        terminal.setCursorPosition(apple.x, apple.y); // Set cursor on the position of apple
        terminal.putCharacter(' '); // Replace apple with empty space
        gameOver(terminal); // Call gameOver method
    }
    private static void gameOver(Terminal terminal) throws IOException {
        String gameOver = "GAME OVER dude..."; // The text that appears when game is over
        terminal.setCursorPosition(25,10); // Placing the game over text
        for (char c : gameOver.toCharArray()) { // The string is made into a char array
            terminal.putCharacter(c); // Printing each char in char array
        }
        terminal.flush();
    }

    // COLLISION WITH FRAME

    // SCORE

}