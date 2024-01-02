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
Growing up, I have always been an avid player Chess but always lost against my father when we played together. Sadly, I still continue to lose against him even to this day, but hopefully, my Chess engine can beat him. 

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
Currently, I am in the process of implementing an AI Chess engine by optimizing a miniMax algorithm with alpha-beta pruning to search through the game tree.Testing it has been tiresome with the engine revealing multiple bugs within my code base. Currently have an engine that is capable of solving several beginner-intermediate Chess puzzles and  I will likely be able to finish an engine that's  competitive against the average person within the next few days. 

Once my engine is completed, I likely won't integrate it into my Java Swing GUI, instead, I plan on porting my app with Springboot to deploy a online Chess server with the capacity to play 1 vs 1 against another person 
or simply against my Chess engine. Naturally, this will be complemented with a front-end web app with the typical HTML, CSS, JavaScript.


## Citations
Chess piece images used in this project are were retrieved from [Wikimedia](https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent) and licensed under [Creative Commons Attribution-Share Alike 3.0 Uported](https://creativecommons.org/licenses/by-sa/3.0/deed.en).

Credit goes to Daniel Fern for his Java Tuples library ([JavaTuples](https://www.javatuples.org/index.html)).



