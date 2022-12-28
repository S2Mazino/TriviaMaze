# TriviaMaze

### Project Scope
The Computer Science Trivia Maze will be a Java based application that uses text based
console to generate a GUI for the ease of use from the player. This maze will be an 4x4 square
where the start of the maze is at the top left and end is at the bottom right. To move from “room”
to “room” the player will have to answer a randomly selected question from a SQLite database.
Trivia Maze will be developed using Eclipse as the main IDE.

### Project Features
This program will contain an interactive trivia maze where depending on if the user got the
question right they will be allowed to progress to the next room of their choice. If they got the
question wrong that door will be permanently locked. It will incorporate a randomly selected
question and answer pair for each room direction choice. When a question is answered
incorrectly the game will lock that door. When a direction is selected it will determine what the
legal directions are and once the question is answered it will check to see if the player has won
the game or if there is no further path to the end meaning the player has lost

### Project Design and Implementation Contstraints
  - DIC1: The Player must maintain an internet connection so that the maze can retrieve the
questions from the database. Being that the questions are randomly selected and that there
may not be enough questions in the database. There may be repeated questions.
  - DIC2: A Maze of NxN is set at N = 4 in order to keep processing time low and questions
database at a reasonable amount.
  - DIC3: Start of maze is at top left; end of maze at bottom right.

### UML Design
![maze_uml](https://user-images.githubusercontent.com/44010904/209758400-0909cc33-9cd9-4f0e-9805-44f28b8dbcaa.JPG)

### Demo Sample
How to play the game
![maze_help](https://user-images.githubusercontent.com/44010904/209759068-2970b7a7-b26b-4e8a-a999-386d9ab165d9.JPG)

Starting a new game

![image](https://user-images.githubusercontent.com/44010904/209760055-6e65db6e-c471-4915-b79e-6641814eae75.png)

Choosing a direction and correct answer

![image](https://user-images.githubusercontent.com/44010904/209760170-2aea4ecb-ed7c-4e93-bde3-c62032ec5c1e.png)

Choosing a direction and wrong answer

![image](https://user-images.githubusercontent.com/44010904/209760210-f54f46e1-8bc0-4b08-b159-58d630ed1b0e.png)

Winning Scenerio

![image](https://user-images.githubusercontent.com/44010904/209760407-d5ca8aa2-80bb-4f98-8d16-4b05127297fc.png)

Losing Scenerio

![image](https://user-images.githubusercontent.com/44010904/209760507-ef137e5e-b81c-41ab-a657-fa513003fc68.png)



