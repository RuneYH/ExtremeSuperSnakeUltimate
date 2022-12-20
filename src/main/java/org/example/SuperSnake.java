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
        Random r = new Random(); // Method to create random number
        applePlacement(terminal, r); // Placing apple in terminal at random position
        terminal.flush();

        // SNAKE MOVEMENT

        SnakePart newHead; // The variable that control head of the snake. Is initiated with every movement of the snake.

        while (true) { // Method to be able to walk more than one step
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
                    snake.add(newHead); // Adding the new placement of head in the array. Makes the array one index bigger.
                    snake.get(0).removePart(terminal); // Removing the last part in terminal.
                    newHead.printPart(terminal); // Prints new head.
            //        if(!isApple)
                        snake.remove(0); // Removing the last part of the tail (index 0) from array. Makes the array return back to its real size. When removing index 0 the array adjusts by moving all index numbers down one spot.
                    break;
                case ArrowRight:
                    newHead = new SnakePart(oldHead.getX() + 1, oldHead.getY());
                    snake.add(newHead);
                    snake.get(0).removePart(terminal);
                    newHead.printPart(terminal);
                    //        if(!isApple)
                    snake.remove(0);
                    break;
                case ArrowUp:
                    newHead = new SnakePart(oldHead.getX(), oldHead.getY() - 1);
                    snake.add(newHead);
                    snake.get(0).removePart(terminal);
                    newHead.printPart(terminal);
                    //        if(!isApple)
                    snake.remove(0);
                    break;
                case ArrowLeft:
                    newHead = new SnakePart(oldHead.getX() - 1, oldHead.getY());
                    snake.add(newHead);
                    snake.get(0).removePart(terminal);
                    newHead.printPart(terminal);
                    //        if(!isApple)
                    snake.remove(0);
                    break;
            }
            terminal.flush();
        }
    }

    // APPLE PLACEMENT method
    private static void applePlacement(Terminal terminal, Random random) throws IOException { // Method that takes in objects terminal and random as arguments
        final char apple = 'A'; // Define apple variable
        terminal.setCursorPosition(random.nextInt(1,90),random.nextInt(1,90)); // Placing cursor randomly to know where to put apple
        terminal.putCharacter(apple); // Placing apple at randomly placed cursor
    }

    // EATING APPLE
    /*public static boolean isApple(SnakePart ){
        if ()
    }*/

    // GROWING FROM APPLE

    // COLLISION WITH SNAKE

    // COLLISION WITH FRAME

    // GAME OVER

    //Unicode
}