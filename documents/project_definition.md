# Project Definition

This project aims to create an artificial intelligence for playing **Ultimate Tic-Tac-Toe (UTTT)**; a highly more complex variation of the trivial base game.

## Algorithms and Data Structures

The AI will primarily use *Monte Carlo tree search* approach to gather and utilize data over multiple repeats of playing the game. The data will be stored in tree or dictionary structure. Lists will be used to keep a track of passed turns. An alpha-beta pruning may be utilized to some degree.

## The problem

UTTT is played on 3x3 regular Tic-Tac-Toes at once. However, the board played next on is generally decided by the opponent's previous turn. While this makes the marks spread unevenly across different boards, winning a certain local board may or may not be valuable in the big picture. This makes it difficult to create any simple heuristics from a given situation. The Monte Carlo approach was chosen for this reason. A complementary alpha-beta pruning may be implemented here to improve the decision-making.

## Input

The input will be a repeated integer between 1 and 9 that will represent the next move or board played on until the game is over.

## Time and memory requirements

The implemented data structures will work as expected. The actual time that the AI will take before ending up on a move can be adjusted.

## Sources

https://en.wikipedia.org/wiki/Ultimate_tic-tac-toe
https://en.wikipedia.org/wiki/Monte_Carlo_tree_search
https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
