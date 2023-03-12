package ui;

import model.Piecelibrary;
import model.Game;
import model.Piece;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Saves;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

// Chess application
public class Main {
    private Scanner input;
    private HashMap<String, Game> saves = new HashMap<>();

    public static void main(String[] args) {
        new Main();
    }

    // EFFECT: runs chess application
    private Main() {
        runChess();
    }

    // EFFECT: Processes user input for main menu
    private void runChess() {
        boolean keepgoing = true;
        String command;
        input = new Scanner(System.in);

        while (keepgoing) {
            displayMenu();
            command = input.nextLine();
            if (command.equals("NEW GAME")) {
                Game g1 = new Game("default", null, true);
                runGame(g1);
            }
            if (command.equals("CUSTOM GAME")) {
                customGameSetup();
            }
            if (command.equals("SAVED GAMES")) {
                loadGame();
            }
            if (command.equals("QUIT")) {
                keepgoing = false;
            }
        }
        System.out.println("Thank you for playing!");
    }


    // EFFECTS: loads a saved game
    private void loadGame() {
        System.out.println("SELECT FILE TO LOAD:");
        displaySaves();
        input = new Scanner(System.in);
        String select = input.nextLine();
        loadSave(select);
    }


    //EFFECTS: displays saved games
    private void displaySaves() {
        Saves saves = new Saves();
        for (Object o: saves.listOfSaves()) {
            String s = (String) o;
            if (s != null && saves.doesSaveExist(s)) {
                System.out.println(s);
            }
        }
    }

    // EFFECTS: Deletes selected save file
    private void deleteSave(String select) {
        Saves saves = new Saves();
        try {
            saves.open();
        } catch (FileNotFoundException e) {
            System.out.println("Unexpected error has occurred!");
        }
        try {
            saves.removeFile(select);
        } catch (FileNotFoundException e) {
            System.out.println("Unexpected error has occurred!");
        }
        saves.close();

        try {
            Files.deleteIfExists(Path.of("./data/" + select + ".json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(select + " has been deleted!");
    }

    // EFFECTS: fetches and runs/deletes a saved game
    private void loadSave(String file) {
        System.out.println("LOAD OR DELETE SELECTED FILE?");
        input = new Scanner(System.in);
        String command = input.nextLine();
        if (command.equals("LOAD")) {
            try  {
                JsonReader jreader = new JsonReader("./data/" + file + ".json");
                Game g = jreader.read();
                runGame(g);
            } catch (IOException e) {
                System.out.println("Unexpected error has occurred!");
            }
        }
        if (command.equals("DELETE")) {
            deleteSave(file);
        }
    }


    // EFFECTS: adds new save
    private void newSave(Game g, String destination) {

        JsonWriter jwriter = new JsonWriter("./data/" + destination + ".json");
        Saves saves = new Saves();
        try {
            jwriter.open();
            jwriter.write(g);
            jwriter.close();
        } catch (Exception error) {
            System.out.println("Unexpected Error Has Occurred");
        }

        try {
            saves.open();
            saves.addFile(destination);
            saves.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unexpected Error Has Occurred");
        }
    }


    // EFFECTS: Processes the setup of a custom board
    private void customGameSetup() {
        Game g1 = new Game("custom", null, true);
        String command;
        input = new Scanner(System.in);

        Boolean setup = true;
        while (setup) {
            customSetUpCommandsDisplay();
            command = input.nextLine();
            if (command.equals("START GAME")) {
                setup = false;
                runGame(g1);
            }
            if (command.equals("ADD PIECE")) {
                customAddPiece(g1);
            }
            if (command.equals("SAVE BOARD")) {
                customSave(g1);
            }
            if (command.equals("MAIN MENU")) {
                setup = false;
            }
        }
    }

    // creates a custom save
    private void customSave(Game g) {
        System.out.println("Please enter name of save!");
        String command;
        input = new Scanner(System.in);
        command = input.nextLine();
        newSave(g, command);
        System.out.println("Game has been saved to" + command);
    }


    // EFFECTS: Processes adding a piece to the board
    private void customAddPiece(Game g) {
        String command;
        Piecelibrary c1 = new Piecelibrary();
        input = new Scanner(System.in);
        System.out.println("SELECT COLOUR");
        System.out.println("    WHITE");
        System.out.println("    BLACK");
        command = input.nextLine();

        if (command.equals("WHITE")) {
            Piece p = customChooseWhichPieceToAdd("WHITE", c1);
            customAddToWhere(p, g, "TYPE", "WHITE");
        }

        if (command.equals("BLACK")) {
            Piece p = customChooseWhichPieceToAdd("BLACK", c1);
            customAddToWhere(p, g, "TYPE", "BLACK");
        }
    }


    // MODIFIES: this
    // EFFECTS: prompts and handles type of piece to add to board
    private Piece customChooseWhichPieceToAdd(String colour, Piecelibrary c) {
        String command;
        input = new Scanner(System.in);
        piecesMenu();
        command = input.nextLine();

        return c.retrievePieceFromLibrary(command, colour);
    }

    // MODIFIES: this
    // EFFECTS: prompts and processes where to add piece onto the board
    private void customAddToWhere(Piece p, Game g, String type, String colour) {
        System.out.print("SELECT SQUARE");
        String command;
        input = new Scanner(System.in);
        command = input.nextLine();
        int xcord = lettersToNumbers(command.substring(0, 1));
        int ycord = numbersToJavaIndexes(command.substring(1, 2));

        g.addPiece(p, xcord, ycord);
        System.out.print(colour + " " + type + " added to " + command);
    }


    // EFFECTS: displays menu for setting up a custom board
    private void customSetUpCommandsDisplay() {
        System.out.println("CUSTOM SETUP");
        System.out.println("    START GAME");
        System.out.println("    ADD PIECE");
        System.out.println("    SAVE BOARD");
        System.out.println("    MAIN MENU");
    }

    // EFFECTS: displays options for pieces to place on board
    private void piecesMenu() {
        System.out.println("KING");
        System.out.println("QUEEN");
        System.out.println("ROOK");
        System.out.println("BISHOP");
        System.out.println("KNIGHT");
        System.out.println("PAWN");
    }

    // MODIFIES: this
    // EFFECTS: runs a chess game
    private void runGame(Game g1) {
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

    // MODIFIES: this
    // EFFECTS: prompts user and processes which piece to move
    private void gameCommandMovePiece(Game g) {
        String command;
        String from;

        System.out.println("Select piece to move");
        command = input.nextLine();
        Piece movingpiece = g.getBd().getPiece(lettersToNumbers(command.substring(0, 1)),
                numbersToJavaIndexes(command.substring(1, 2)));
        if (movingpiece != null) {
            from = command;
            if (g.isPieceTurnToMove(movingpiece)) {
                squareToMoveTo(g, movingpiece, from);
            }
        } else {
            System.out.println("Square is empty");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user and processes where to move piece to
    private void squareToMoveTo(Game g, Piece movingpiece, String from) {
        String command;
        String to;

        System.out.println("Select square to move to");
        command = input.nextLine();
        if (g.canBeMovedThere(movingpiece, lettersToNumbers(command.substring(0, 1)),
                numbersToJavaIndexes(command.substring(1, 2)))) {
            g.movePiece(movingpiece, lettersToNumbers(command.substring(0, 1)),
                    numbersToJavaIndexes(command.substring(1, 2)));
            to = command;
            System.out.println(movingpiece.getType() + " moved from " + from + " to " + to);
        } else {
            System.out.println(movingpiece.getType() + " cannot be moved there");
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes in game commands
    private void gameCommands(Game g) {
        String command = input.nextLine();
        if (command.equals("MOVE")) {
            gameCommandMovePiece(g);
        }
        if (command.equals("FORFEIT") && g.getPlayer1turn()) {
            System.out.println("GAME OVER: WHITE FORFEITS!");
            g.gameOver();
        }
        if (command.equals("FORFEIT") && g.getPlayer2turn()) {
            System.out.println("GAME OVER: BLACK FORFEITS!");
            g.gameOver();
        }
        if (command.equals("DRAW")) {
            drawRequest(g);
        }
        if (command.equals("SAVE")) {
            customSave(g);
        }
        if (command.equals("MAIN MENU")) {
            g.gameOver();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts player to accept or decline draw offer
    private void drawRequest(Game g) {
        if (g.getPlayer1turn()) {
            System.out.println("White wants to draw. Accept?");
            yesOrNo();
            isDrawAccepted(g);
        }
        if (g.getPlayer2turn()) {
            System.out.println("Black wants to draw. Accept?");
            yesOrNo();
            isDrawAccepted(g);
        }
    }

    // EFFECTS: Displays a yes or no option
    private void yesOrNo() {
        System.out.println("YES");
        System.out.println("NO");
    }

    // MODIFIES: this
    // EFFECTS: Evaluates if draw is accepted
    private void isDrawAccepted(Game g) {
        String command = input.nextLine();
        if (command.equals("YES")) {
            System.out.println("GAME OVER: Draw!");
            g.gameOver();
        }
        if (command.equals("NO")) {
            System.out.println("Draw declined!");
        }
    }

    // REQUIRES: coordinate is a number from 1-8
    //EFFECTS: Maps chess coordinate to array indexing
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
        return 7;
    }

    // REQUIRES: coordinate is a letter from A-H
    // EFFECTS: Maps chess coordinate to array indexing
    private int lettersToNumbers(String coordinate) {
        if (coordinate.equals("A")) {
            return 0;
        }
        if (coordinate.equals("B")) {
            return 1;
        }
        if (coordinate.equals("C")) {
            return 2;
        }
        if (coordinate.equals("D")) {
            return 3;
        }
        if (coordinate.equals("E")) {
            return 4;
        }
        if (coordinate.equals("F")) {
            return 5;
        }
        if (coordinate.equals("G")) {
            return 6;
        }
        return 7;
    }

    //EFFECTS: displays command options in a match
    private void gameMenuCommands() {
        System.out.println("MOVE");
        System.out.println("FORFEIT");
        System.out.println("DRAW");
        System.out.println("SAVE");
        System.out.println("MAIN MENU");
    }


    // EFFECTS: displays main menu to user
    private void displayMenu() {
        System.out.println("MAIN MENU:");
        System.out.println("  NEW GAME");
        System.out.println("  CUSTOM GAME");
        System.out.println("  SAVED GAMES");
        System.out.println("  QUIT");
    }

}
