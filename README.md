# Java Chess 

## Game
This is a desktop Chess application that supports pass-and-play multiplayer between two players. The app also includes a save game feature, as such, it could be useful for players to practice Chess openings (strategies)
and save them for future reference.

Chess is a game featuring two players, both starting off 16 pieces on opposite sides of the board. The objective of the game is to checkmate your opponent's king, that is, when one of your pieces threatens to capture your opponent's
King on your next term AND, the opponent has no legal moves to:

* move their king out of the way
* block your threatening piece with another piece 
* capture the piece threatening the king.
  
A more detailed overview of the rules of the game can be found [here](https://www.chess.com/learn-how-to-play-chess).

![image](https://github.com/BryanJ1ang/Java-Chess/assets/134325602/c04d40ca-72f8-4ebb-90da-1f2cf086127f)


## Why I chose this project 
Growing up, I have always been an avid player Chess and frequently lost against my father when we played together. As such,
it seemed fitting to create this project as it is of personal interest as well as offering a good amount
of challenge for me to hone my technical skills.

## The Process
Since this was my first programming project, much of the initial stages involved figuring out how to represent the different parts of Chess using classes. Then, the challenging part was the game logic 
and implementing the legal movements for each type of Chess piece. One big aspect of chess is that most pieces, such as the Queen, cannot "jump" over another piece. As such, determining if a move is legal 
involves checking if any pieces are between the current square and the desired destination square. Determining the game state was also challenging because it required creating search algorithms to check 
if either player's King is in check. Likewise, if a King is indeed in check, another algorithm must determine whether that player has any legal moves available. 


## Future Improvements 
![image](https://github.com/BryanJ1ang/Java-Chess/assets/134325602/f822eb9c-4b28-472b-915c-8c55d3ba849c)

The above is one of the search algorithms for my game logic. This method returns True if white is Checkmated and determined that by indexing through a list of White pieces. If any of these piece's can make a legal move then White is not in check mate and the method returns False. Instead of searching through the entire board for a valid move for each piece, this method could be better optimized by simply searching through the possible moves each piece could make. For example, since a rook can only go up, down, left, or right, it would be better to only search in these directions for a legal move instead of including diagonal squares. Although this is mostly negligible for a desktop app since a Chess board only has 64 squares, it would definitely be an area for improvement.


## Whats next?
There are definitely several sections that could use reformatting to reduce coupling and increase readability such as the Game class which could be reformatted into smaller, more cohesive classes.

Aside from that, one major feature I would like to implement is an AI Chess engine using a MinMax algorithm. This is a feature that I think would not only be really interesting but also would be great  experience for me to apply my knowledge of time complexity of algorithms.

## Citations
parts of the persistence package, including JSONWriter and JSONReader is based on code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git





