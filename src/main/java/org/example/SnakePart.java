package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class SnakePart {
    private int x;
    private int y;

    private final char body = 'O';

    public SnakePart(int x, int y) { //constructor
        this.x = x;
        this.y = y;
    }

    //getter

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //static method can be called directly from inside the class, non static method must be called from an instantiated object
    public void printPart(Terminal terminal ) throws IOException {
        //print snake
        print(terminal, body);

    }

    public void removePart(Terminal terminal ) throws IOException {
        //print snake
        print(terminal, ' ');

    }

    private void print(Terminal terminal, char symbol ) throws IOException {
        //print snake
        terminal.setCursorPosition(x,y);
        terminal.putCharacter(symbol);

    }


}
