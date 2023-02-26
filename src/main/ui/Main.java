package ui;

import model.Game;
import model.Piece;

import java.util.Scanner;

public class Main {
    private Scanner input;


    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        runChess();
    }

    private void runChess() {
        boolean keepgoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepgoing) {
            displayMenu();
            command = input.nextLine();
            if (command.equals("New Game")) {
                gameDefault();
            }

            if (command.equals("Custom Game")) {
                Game g1 = new Game("custom");
            }
            if (command.equals("Quit")) {
                keepgoing = false;
            }
        }
        System.out.println("Thank you for playing!");
        }




    private void gameDefault() {
        Game g1 = new Game("default");
        while (g1.getGamestatus()) {
            if (g1.getPlayer1turn()) {
                System.out.println("Whites Turn:");
                gameMenuCommands();
                gameCommands(g1);
            }
            if (g1.getPlayer2turn()) {
                System.out.println("Blacks turn:");
                gameMenuCommands();
                gameCommands(g1);
            }
        }
    }

    private void gameCommandMovePiece(Game g) {
        String command;
        String from;

        System.out.println("Select piece to move");
        command = input.nextLine();
        Piece movingpiece = g.getBd().getPiece(lettersToNumbers(command.substring(0,1)),
                numbersToJavaIndexes(command.substring(1,2)));
        if (movingpiece != null) {
            from = command;
            if (g.isPieceTurnToMove(movingpiece)) {
                squareToMoveTo(g, movingpiece, from);
            }
        } else {
            System.out.println("Square is empty");
        }
    }

    private void squareToMoveTo(Game g, Piece movingpiece, String from) {
        String command;
        String to;

        System.out.println("Select square to move to");
        command = input.nextLine();
        if (g.canBeMovedThere(movingpiece, lettersToNumbers(command.substring(0,1)),
                numbersToJavaIndexes(command.substring(1, 2)))) {
            g.movePiece(movingpiece, lettersToNumbers(command.substring(0,1)),
                    numbersToJavaIndexes(command.substring(1, 2)));
            to = command;
            System.out.println(movingpiece.getType() + " moved from " + to + " " + from);
        } else {
            System.out.println("Piece cannot be moved there");
        }
    }

    private void gameCommands(Game g) {
        String command = input.nextLine();
        String from;
        String to;
        if (command.equals("Move Piece")) {
            gameCommandMovePiece(g);
        }

        if (command.equals("Forfeit") && g.getPlayer1turn()) {
            System.out.println("Game Over: White Surrenders!");
            g.gameOver();
        }

        if (command.equals("Forfeit") && g.getPlayer2turn()) {
            System.out.println("Game Over: Black Surrenders!");
            g.gameOver();
        }

        if (command.equals("Draw") && g.getPlayer1turn()) {
            System.out.println("White wants to draw. Accept?");
            yesOrNo();
            isDrawAccepted(g);
        }
        if (command.equals("Draw") && g.getPlayer2turn()) {
            System.out.println("Black wants to draw. Accept?");
            yesOrNo();
            isDrawAccepted(g);
        }

    }

    private void yesOrNo() {
        System.out.println("Yes");
        System.out.println("No");
    }

    private void isDrawAccepted(Game g) {
        String command = input.nextLine();
        if (command.equals("Yes")) {
            System.out.println("Game over: Draw!");
            g.gameOver();
        }
        if (command.equals("No")) {
            System.out.println("Draw declined!");
        }
    }

    private void gameMenuCommands() {
        System.out.println("Move Piece");
        System.out.println("Forfeit");
        System.out.println("Draw");
        System.out.println("Quit");
    }


    private int numbersToJavaIndexes(String n) {
        if (n.equals("8")) {
            return 0;
        }
        if (n.equals("7")) {
            return 1;
        }
        if (n.equals("6")) {
            return 2;
        }
        if (n.equals("5")) {
            return 3;
        }
        if (n.equals("4")) {
            return 4;
        }
        if (n.equals("3")) {
            return 5;
        }
        if (n.equals("2")) {
            return 6;
        }
        if (n.equals("1")) {
            return 7;
        } else {
            return -1;
        }
    }

    private int lettersToNumbers(String str) {
        if (str.equals("A")) {
            return 0;
        }
        if (str.equals("B")) {
            return 1;
        }
        if (str.equals("C")) {
            return 2;
        }
        if (str.equals("D")) {
            return 3;
        }
        if (str.equals("E")) {
            return 4;
        }
        if (str.equals("F")) {
            return 5;
        }
        if (str.equals("G")) {
            return 6;
        }
        if (str.equals("H")) {
            return 7;
        } else {
            return -1;
        }
    }

    private void runGame() {
        gameMenuCommands();
    }

    private void newDefaultGame() {
        Game g1 = new Game("default");
    }

    // EFFECTS: displays main menu to user
    private void displayMenu() {
        System.out.println("MAIN MENU:");
        System.out.println("  New Game");
        System.out.println("  Custom Game");
        System.out.println("  Quit");
    }

}
