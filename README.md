# Chess Board

## Phase 1

A Chessboard application able to play games with either a default or custom arrangement of pieces. The app is fully 
functional, able to move/capture pieces and detect conditions for the game to end (check-mate, run out of time etc.) The 
application will also allow saving the states of the game to allow players to return to specific spots in a game and 
attempt different moves. This application would naturally be used by Chess players to play with their friends, to
analyze specific situations or to simply mess around with crazy set up of pieces. Growing up, I have always played 
chess on and off on various apps on my phone. As such, this app is of personal interest as I hope to implement a Chess
with the features I want.



*User Stories*
- As a user, I would like to be able to move my pieces.
- As a user, I would like to know if a move I am trying to make is invalid
- As a user, I would like to be able to forfeit a match.
- As a user, I would like to be able to offer to draw the match.
- As a user, I would like to be able to add pieces to the board to create a custom setup of the board.
- As a user, I would like to be able to save my game in the middle of a match and resume it later on
- As a user, I would like to be able to save my custom setups of a board and play it later


## INSTRUCTIONS FOR GRADER:
- Run MainGooey under UI 
- Start new game and move pieces around.
- Press save game on the left, type name of save into text box and hit enter to save current game. Save as many times as you like!
- Continue playing or return to main menu
- To load a saved game, go to main menu and press "Saved Games"
- Choose from the list of saved games and you will be prompted to either load/delete the selected save
- Visual component consists of the chess board


## Citation:
All chess piece images are the work of Cburnett.
Retrieved from https://commons.wikimedia.org/wiki/File:Chess_klt45.svg



## Phase 4: Task 2
New game created
White pawn moved from E2 to E4
Black pawn moved from D7 to D5
White pawn captures black pawn from E4 to D5
Black pawn moved from E7 to E6
White pawn captures black pawn from D5 to E6
Black queen captures white pawn from D8 to D2
Game saved as: Save 1
New game created
Loaded from saved file: Save 1
White pawn captures black pawn from E6 to F7
Black queen captures white pawn from D2 to C2
White queen captures black queen from D1 to C2
Game saved as: Save 2
Save deleted: Save 1
New game created
Loaded from saved file: Save 2
Black knight moved from G8 to F6

## Phase 4: Task 3
One major area of improvement for my code is coupling. For example, some of my methods in 
PieceLibrary depend on a very exact set of String to retrieve a Piece. As such, if some of my
methods have a minor spelling error for a parameter, then bugs would likely occur.

Another area would be to refactor my Game class as I feel it does not follow 
the single responsibility principle. Instead, I would move the many methods relating to 
changing turns to the player class which I feel would improve the cohesion more.

