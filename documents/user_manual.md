# User manual

The program can be launched with `java -jar ultira-au-toe.jar`. A game of user versus AI will be played with a coin toss deciding which one will start.

The grid is numbered from left to right, up to down starting from 1. For example, a board of size 3 would have to boards in the following order:
```
1  2  3
4  5  6
7  8  9
```
The board of size 4 would have labels from 1 to 16 and so on.

Generally the board played next is decided by which move was played last. For example, a move to the position 3 would mean that the next player will play the 3rd board. In the beginning and when pointed to a solved board the player is allowed to pick the board they want to play on first in the same vein.


## Launch parameters

* `-size=n` The size of the board. Clamped to `3...6`. `3` is the default.
* `-timer=ms` The amount of time the AI is allowed to simulate any scenario. `0` is the minimum, `1000` the default.
* `-demo` The AI will play against itself rather than player.
* `-benchmark` Overrides `-demo`. Launches the benchmark function rather than displays a game.
* `-threads=n` Used with `-benchmark`. The amount of games ran simultaneously. Clamped to `1...8`. `2` is the default.

The benchmark utility pits two different instances of the AI against each other across 100 games. The amount of threads used in this process shouldn't exceed the amount of CPU cores on the testing machine. As the AI training is measured with the system time, any excessive competition would effectively result in less training per game that perceived.
