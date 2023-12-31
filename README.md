# Java Chess 

## The Game
This is a desktop Chess application that supports pass-and-play multiplayer between two players. The app also includes a save game feature, as such, it could be useful for players to practice Chess openings (strategies)
and save them for future reference.

Chess is a game featuring two players, both starting off 16 pieces on opposite sides of the board. The objective of the game is to checkmate your opponent's king, that is, when one of your pieces threatens to capture your opponent's
King on your next term AND, the opponent has no legal moves to:

* move their king out of the way
* block your threatening piece with another piece 
* capture the piece threatening the king.
  
A more detailed overview of the rules of the game can be found [here](https://www.chess.com/learn-how-to-play-chess).

![image](https://github.com/BryanJ1ang/Java-Chess/assets/134325602/e7c7e1ff-d127-477f-9a29-9d25fb792fb3)

## Why I chose this project 
Growing up, I have always been an avid player Chess and frequently lost against my father when we played together. As such,
it seemed fitting to create this project as it is of personal interest as well as offering a good amount
of challenge for me to hone my technical skills.

## The Process
#### Languages and Libraries
* Java
* Java Swing 
* JUnit 

Since this was my first programming project, much of the initial stages involved figuring out how to represent the different parts of Chess using classes. Then, the challenging part was the game logic 
and implementing the legal movements for each type of Chess piece. One big aspect of chess is that most pieces, such as the Queen, cannot "jump" over another piece. As such, determining if a move is legal 
involves checking if any pieces are between the current square and the desired destination square. Determining the game state was also challenging because it required creating search algorithms to check 
if either player's King is in check. Likewise, if a King is indeed in check, another algorithm must determine whether that player has any legal moves available. 


## Whats Next?
Currently, I am developing an AI Chess engine by optimizing a miniMax algorithm to search through the game tree. Future
plans involve porting the game logic and AI engine to Springboot to deploy a multiplayer Chess server and connecting it 
to a JavaScript front-end.


## Citations
Parts of the persistence package, including JSONWriter and JSONReader are based on code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

Chess piece images used in this project are were retrieved from [Wikimedia](https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent) and licensed under [Creative Commons Attribution-Share Alike 3.0 Uported](https://creativecommons.org/licenses/by-sa/3.0/deed.en).




