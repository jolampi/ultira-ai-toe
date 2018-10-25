# Project implementation


## General structure
**A game** of size *N* consists of two players and a procedurally advancing **gamestate**. A Gamestate determines which **player** is in turn and which moves they are allowed to play in any given phase. Gamestate also contains and provides access to information about the game board. The game will continue until it is either won by another player or considered a tie, after which it will report the result to both players.

The game board called **superboard** consists of *N^2* smaller **boards** also of the size *N^2*. The unit of a board is a **mark**, which can either be a *cross*, a *nought* or *neither*. Default rules of tic-tac-toe determines the status of the board from the perspective of the superboard and further on the status of the superboard to a gamestate.

The AI player stores data from the results of passed games in a dictionary structure. In this dictionary a gamestate provides the key to the statistics for that specific state of the game.


## Time and space requirement analysis

### structure

#### List.java
|          | List()  | add() | get() | clear() |
|----------|---------|-------|-------|---------|
| O(time)  | O(n)    | O(1)  | O(1)  | O(n)    |
| O(space) | O(n)    | O(1)  | O(1)  | O(n)    |

```javascript
add(element)
  if n == list.length
    grow()
  list[n] = element
  n++
```
New element will be added in a constant time if internal array isn't yet full. If it is, the *grow()* fuction is called, which effectively doubles the size of the array. This will take O(n) but is called rarely enought that the amortized time requirement is *O(n)*, which also goes for the space.

```javascript
get(index)
  checkIndex(index);
  return list[index]
```
*checkIndex()* does a simple bound check which happens in a constant time. Thus this method takes a constant time and since no external methods are required, space.

```javascript
clear()
  list = [D]
  n = 0
```
Effectively the same happends with the constructor, so both are handled here. Allocating the list is a linear operation, denoted as *D* for *default* here as *n* was reserved for element counter.

### ultirai

#### Board.java
|          | Board() | createBoard() | next() | toCharArray() | resolve() |
|----------|---------|---------------|--------|---------------|-----------|
| O(time)  | k\*n    | n             | n      | n^2           | n^2       |
| O(space) | k\*n    | n             | n      | n^2           | 1         |

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

```python
char[][] toCharArray(cross, nought, none)
  array = [n][n]
  for y = [0, n)
    for x = [0, n)
      array[y][x] = match(board[y][x], args)
  return array
```
Simply create a 2D array and fill it with appropriate character values. Space requirement of *O(n^2)* for creating the array. This in addition with two nested loops will yield the time requirement of *O(n^2)*.

```python
Mark resolve()
  for n horizontal, n vertical and 2 diagonal rows
    result = resolveRow(...)
    if (result != NONE) return result
  return NONE
```
This method attempts to determine the winner of the board by checking all possible *O(n + n + 2) = O(n)* rows. The helper method `resolveRow(...)` simply crawls along the given row while counting up to a sufficient streak, taking O(n) time to do so. In the worst case scenario no winner will be returned, taking this function *O(n^2)* time return the result. The method will only need a constant amount of variables however, so the space requirement is *O(1)*.

## Areas of improvement
* Reduce the entropy of gamestates by detecting symmetries.
