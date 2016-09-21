 Snakes and Ladders (or chutes and ladders) is a board game wherein a player moves from square to square along the board in order to reach the end. Each square corresponds to a particular action which either gives the player an advantage or disadvantage. Each time a player takes his turn, he carries out an action depending on the square on which they land (throw the dice to move, move forward, move back, etc).

The objective of this program is to find out how many turns it takes for the luckiest player to win the game.

BOARD:
We consider a version of the game where the board is composed of a series of squares. Each square can contain either:

    The character S which represents the starting square
    The character E which represents the finishing square
    A number representing how many squares a player should move forward (a positive number) or move back (a negative number)
    The character R which indicates that the player should throw the dice to move

The start and finish squares are not necessarily situated at the start and end of the board.
 
GAME:
At the start of the game, the player begins on the starting square (labeled S). They start by throwing the dice and moving the number of squares indicated on the dice. They then carry out the action which is indicated on the square. Each action takes one turn (throw the dice to move, move forward, move back). The game is won when a player reaches the finishing square (labeled E).

If we were to have the following board:  
  S 	  1 	  R 	  4 	  3 	  4 	  3 	  -5 	  2 	  -4 	  E

The fastest way to win in this case is to throw a 2 on the first throw of the dice, which will bring the player to the square R. Then they will throw the dice again and get 6, so they will move forward and land on a square with 2. On the last turn, they will not have a choice: they will move forward two squares and arrive on the square E.

The minimum number of turns to win this game is therefore 3.

Write a program that finds the minimum number of turns given the specified board.

In case a player cannot reach the finishing square, the program should display impossible.
 
INPUT:
Line 1: the number N of squares on the board
N following lines:  one square value per line (R, S, E or a number)
 
OUTPUT:
The minimum number of turns required to reach the finishing square or impossible.
 
CONSTRAINTS:
0 < N < 5000
-N < number value of a square < N
The dice has 6 sides: values are 1, 2, 3, 4, 5, 6.
S and E are unique and always exist.
 
EXAMPLE:
Input
11
S
1
R
4
3
4
3
-5
2
-4
E
Output
3
 
