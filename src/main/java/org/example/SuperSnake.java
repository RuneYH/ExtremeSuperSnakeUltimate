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

    //terminal created
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        //Snake setup / Variables
        List<SnakePart> snake = new ArrayList<>();
        snake.add(new SnakePart(5,10));
        snake.add(new SnakePart(6,10));
        snake.add(new SnakePart(7,10));
        snake.add(new SnakePart(8,10));
        snake.add(new SnakePart(9,10));
        snake.add(new SnakePart(10,10));
        for (SnakePart snakePart : snake) {
            snakePart.printPart(terminal);
        }


        Random r = new Random(); //apple random placement

        applePlacement(terminal, r); //calling in snake movement method

        terminal.flush();

        //SNAKE MOVEMENT

        while (true) { //method to be able to walk more than one step
            //SnakeHead position

            KeyStroke keyStroke = null; //the user have not used Key function yet
            do {                        //until the user presses keyStroke, we are creating a mini break of 5 m.sec before next command
                Thread.sleep(5);
                keyStroke = terminal.pollInput(); //NextInput from user for each 5 m.sec
            }
            while (keyStroke == null);

            SnakePart newHead;
            SnakePart oldHead = snake.get(snake.size()-1);

            //switch
            // takes input and navigates inside the terminal
            switch (keyStroke.getKeyType()){
                case ArrowDown:
                    newHead = new SnakePart(oldHead.getX(), oldHead.getY() + 1);
                    snake.add(newHead);
                    snake.get(0).removePart(terminal);
                    newHead.printPart(terminal);
            //        if(!isApple)
                        snake.remove(0);
                    break;
                case ArrowRight:
                    newHead = new SnakePart(oldHead.getX() +1, oldHead.getY());
                    snake.add(newHead);
                    snake.get(0).removePart(terminal);
                    newHead.printPart(terminal);
                    //        if(!isApple)
                    snake.remove(0);
                    break;
                case ArrowUp:
                    newHead = new SnakePart(oldHead.getX(), oldHead.getY() -1);
                    snake.add(newHead);
                    snake.get(0).removePart(terminal);
                    newHead.printPart(terminal);
                    //        if(!isApple)
                    snake.remove(0);
                    break;
                case ArrowLeft:
                    newHead = new SnakePart(oldHead.getX() -1, oldHead.getY());
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

    //APPLE PLACEMENT method
    private static void applePlacement(Terminal terminal, Random r) throws IOException {
        //Apple placement
        final char apple = 'A'; //define apple variable
        terminal.setCursorPosition(r.nextInt(1,90),r.nextInt(1,90)); //cursor placement
        terminal.putCharacter(apple); //apple placement
    }


    //GameOver


    //Unicode

}