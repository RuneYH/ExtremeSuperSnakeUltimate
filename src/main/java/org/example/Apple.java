package org.example;

import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.Random;

public class Apple {
    Random random = new Random();
    int x = random.nextInt(3,75);
    int y = random.nextInt(3,20);

    public Apple(Terminal terminal) throws IOException { // Constructor that takes in objects terminal and random as arguments
        final char apple = '\u014F'; // Define apple variable
        terminal.setCursorPosition(x,y); // Placing cursor randomly to know where to put apple
        terminal.putCharacter(apple); // Placing apple at randomly placed cursor
    }

    public boolean isApple(SnakePart head) {
        // Test if coordinates of snake and apple are in sync
//        System.out.println("snake: " + head.getX() + " " + head.getY()); // Print snake coordinates to compare with apple
//        System.out.println("apple: " + x + " " + y); // Print apple coordinates to compare with snake
        return x == head.getX() && y == head.getY(); // Statement is true if apple x and y equals snake x and y
    }
}
