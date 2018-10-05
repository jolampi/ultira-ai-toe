# Project implementation


## Program structure
**A game** of size *N* consists of two players and a procedurally advancing **gamestate**. A Gamestate determines which **player** is in turn and which moves they are allowed to play in any given phase. Gamestate also contains and provides access to information about the game board. The game will continue until it is either won by another player or considered a tie, after which it will report the result to both players.

The game board called **superboard** consists of *N^2* smaller **boards** also of the size *N^2*. The unit of a board is a **mark**, which can either be a *cross*, a *nought* or *neither*. Default rules of tic-tac-toe determines the status of the board from the perspective of the superboard and further on the status of the superboard to a gamestate.

The AI player stores data from the results of passed games in a dictionary structure. In this dictionary a gamestate provides the key to the statistics for that specific state of the game.


## Time and memory analysis
nul


## Areas of improvement
* Reduce the entropy of gamestates by detecting symmetries.
