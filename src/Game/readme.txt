1. Cell:
It is one of the basic components of the graph in this game, it acts as a node for the graph on
which the whole is game is based on and it has 4 sub-branches/links i.e. Top, Right, Bottom
and Left. If a link / sub-branch is null then there is a wall created towards the respective side.
And if there is no wall then our player can move from one cell to another cell.
Important Properties of Cell:
a. Player Cell: verifies whether the player is in the cell or not.
b. Ending Cell/Home Cell: verify the ending point/ home point.
c. Visiting: used in Algorithms.
d. Distance Array: As such the distance from a cell to another connected cell will be the
same for all the cells (i.e. Unweighted Graphs), but this property is used in Maze
Generation (Prims and Kruskal).






2. Grid System:
Grid Systems contains a 2D array of Cells (grid), which is publically static for all other classes.
This Grid is used for the arrangement of cells i.e. Cells’ Orientation, Defining Cells’ Sub
Branches, Rendering of Cell, and Player’s Movements; all of these functions are handled by
Grid System.






3. Game Class and Runner Class:
Game Class is one of the most important classes of the Project as it does all the Rendering,
Initializing, and Event Handling. And Runner class is used to initialize Game Class.
Game class is used to handle all 3 states of the game i.e. STARTMENU, GAME, WINNER
SCREEN. Its main function is rendering all the components of the game independent of other
classes’ execution. Any changes made in the Grid can be immediately shown in rendering
because of the Game class.
Game States:

a. STARTMENU:
In this state, user has to choose difficulty of the game. Basically number of cells to
be initialized are decided in this phase.

b. GAME:
GAME is the state in which a player can generate & play the maze, as there are
different algorithms present for this purpose. Players can also use pathfinding
Algorithms to find the path towards their destination.

c. WINNER SCREEN:
WINNER SCREEN doesn’t have any special functionality it is just used to inform that
the user has reached his destination.





4. Maze Algorithms:
All Implementation of Maze Generation Algorithms i.e. DFS, Prims and Kruskal is done in this
Class.
a. DFS: It uses the same Principle as DFS, it just choosing the next cell randomly, and
removing the wall between these two cells.

b. Prims: As we were having an unweighted graph, so using prims was pointless at
some point. But we assigned each cell a random Distance and with the help of prims
algorithm we designed an MST, this MST is treated as Maze.

c. Kruskal: Kruskal Algorithm is implemented by assign cell edges random distances
and then converted them into an Adjacency Matrix. From this Adjacency Matrix, an
Edges Data List for MST is derived. From this Edges Data List, Maze is generated.






5. Path Finding:
BFS and A* Algorithms are Implemented in this Class.
a. BFS:
BFS is implemented by using Queue of Dynamic Stacks. Dynamic Stack is used
because when a destination point is found, so a path is drawn from the destination
point towards the player point.

b. A* Algorithm:
A* Algorithm is applied same as BFS, but in this Algorithm, Priority Queue is used
and the Priority is set on the top element of the stack. Stack with a top element of
the lowest distance between the point and destination has the highest priority.






6. Menu:
Menu is used in Game state, it renders the Menu for Different Algorithms, and so user can
choose the option from them to create Maze or Path. It is also used to adjust the speed of
algorithms.






7. Starter:
Stater Screen is used in Start Menu State. Its purpose is to provide a graphic interface to
choose the difficulty of the game.






8. Winner:
Winner Class is just used to inform the user that he has reached the destination point
