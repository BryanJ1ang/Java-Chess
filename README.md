# Java Chess 

## Game
This is a desktop Chess application that supports pass-and-play multiplayer between two players. Full support for 

I have always been an avid player Chess and frequently lost against my father when we played together. As such,
it seemed fitting to create this project as it is of personal interest as well as offering a good amount
of challenge for me to hone my technical skills.

![image](https://github.com/BryanJ1ang/Java-Chess/assets/134325602/c04d40ca-72f8-4ebb-90da-1f2cf086127f)


## The Process
Since this was my first programming project, much of the initial stages involved figuring out how to represent the different parts of Chess using classes. Then, the challenging part was the game logic 
and implementing the legal movements for each type of Chess piece. One big aspect of chess is that most pieces, such as the Queen, cannot "jump" over another piece. As such, determining if a move is legal 
involves checking if any pieces are between the current square and the desired destination square. Determining the game state was also challenging because it required creating search algorithms to check 
if either player's King is in check. Likewise, if a King is indeed in check, another algorithm must determine whether that player has any legal moves available. Although it 


## Future Improvements
![image](https://github.com/BryanJ1ang/Java-Chess/assets/134325602/f822eb9c-4b28-472b-915c-8c55d3ba849c)
One of the search algorithms for my game logic. This method returns True if white is Checkmated and determined that by indexing through a list of White pieces. If any of these piece's can make a legal move then it 
White is not in check mate and the method returns False. Instead of searching through the entire board for a valid move for each piece, this method could be better optimized by simply searching through the possible moves 
each piece could make. For example, since a rook can only go up, down, left, or right, it would be better to only search in these directions for a legal move instead of including diagonal squares. Although this is mostly 
negligible for a desktop app since a Chess board only has 64 squares.

## Citations

parts of the persistence package, including JSONWriter and JSONReader is based on code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git



