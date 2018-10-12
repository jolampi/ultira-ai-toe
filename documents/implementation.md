# Project implementation


## General structure
**A game** of size *N* consists of two players and a procedurally advancing **gamestate**. A Gamestate determines which **player** is in turn and which moves they are allowed to play in any given phase. Gamestate also contains and provides access to information about the game board. The game will continue until it is either won by another player or considered a tie, after which it will report the result to both players.

The game board called **superboard** consists of *N^2* smaller **boards** also of the size *N^2*. The unit of a board is a **mark**, which can either be a *cross*, a *nought* or *neither*. Default rules of tic-tac-toe determines the status of the board from the perspective of the superboard and further on the status of the superboard to a gamestate.

The AI player stores data from the results of passed games in a dictionary structure. In this dictionary a gamestate provides the key to the statistics for that specific state of the game.


## Time and space requirement analysis

### ultirai

#### Board.java

```python
Mark[][] createBoard(n)
  row = [n] { NONE, ..., NONE }
  board = [n] { row, ..., row }
  return board
```
Allocating a board of size *n^2* takes two linear operations in both time and space to accomplish. This is because all rows point to the same one in the beginning. Thus we get *O(n + n) = O(n)* for both.

```python
Board next(mark, x, y)
  nextBoard = shallowCopy(board)
  nextBoard[y] = shallowCopy(board[y])
  nextBoard[y][x] = mark
  return Board(nextBoard)
```
To create a board where a mark is changed in one position while not affecting the current one, a duplicate of row is created. The new row in turn will also need to be referred in a duplicate array. Since we only do shallow copies (unchanged referred contents are shared with the source array) both of these operations will have a linear time and space requirement. The rest of the operations happen in a constant time (creating a new Board object only saves the given reference) yielding us *O(n + n + 1) = O(n)*.

As effectively all boards are derived from this method, we get the time and space requirements of *O(k\*n)* for any arbitrary instance of a board where *k* is the number of times this method was called.

|             |O(time)|O(space)|
|-------------|-------|--------|
|Board()      |k\*n   |k\*n    |
|createBoard()|n      |n       |
|next()       |n      |n       |

## Areas of improvement
* Reduce the entropy of gamestates by detecting symmetries.
