package org.example;

import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

// Object oriented programming!
// Holds variables of the snake's body
public class SnakePart {
    // Coordinate variables
    private int x;
    private int y;

    private final char body = 'O'; // Define snake variable

    public SnakePart(int x, int y) { // Constructor takes the two coordinate variables as arguments. Represents the snake's body.
        this.x = x;
        this.y = y;
    }

    // Getter. Makes it possible to call the private coordinate variables from main class.
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    // Static method can be called directly from inside the class. Non-static method must be called from an instantiated object.
    public void printPart(Terminal terminal) throws IOException { // Method for printing snake.
        print(terminal, body); // Printing snake by calling the print method.
    }

    public void removePart(Terminal terminal) throws IOException { // Method for removing last part of tail during movement.
        print(terminal, ' '); // Printing empty space after snake by calling the print method.
    }

    private void print(Terminal terminal, char symbol) throws IOException { // Method for printing. IOException added because setCursorPosition and putCharacter are abstract methods that needs an exception.
        terminal.setCursorPosition(x,y); // Setting cursor position.
        terminal.putCharacter(symbol); // Printing any symbol at cursor position.
    }
}
