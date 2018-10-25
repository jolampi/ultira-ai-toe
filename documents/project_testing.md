# Testing document

Most of the testing will be done with JUnit. Test classed used are included in this repository. The `HumanPlayer` class coverage is limited to basic input checking. The `AIPlayer` class is excluded from unit tests as its behavior will need to be observed empirically. The performance of the program primarily depends on the AI which can be observed with the proper launch arguments (once included).


## AI performance

The hyphothesis was that the proportion of ties would increase when both players had more time to think their moves. Alternatively the other player might start to win more if the game is actually biased in their favor.

| ai timer (ms) | first goer wins | second goer wins | ties | avg. game length (s) |
|---------------|-----------------|------------------|------|----------------------|
| 0             | 37              | 37               | 26   | 0.002                |
| 250           | 24              | 12               | 64   | 17.117               |
| 500           | 19              | 11               | 70   | 34.346               |
| 1000          | 17              | 13               | 70   | 75.829               |
| 1500          | 18              | 13               | 69   | 104.442              |

## JUnit coverage comprehensiveness
The progress of providing coverage for relevant classes.
* [x] structure
  * [x] Dictionary
  * [x] List
* [ ] ultirai
  * [x] Board
  * [ ] Game
  * [ ] GameState
  * [ ] HumanPlayer
  * [ ] SuperBoard
