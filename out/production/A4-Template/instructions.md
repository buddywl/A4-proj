# Assignment 4:  Recursive Maze Solving

For this assignment, you will write a program for solving mazes using **recursion**. The "big idea" is as follows: each time you explore a location in the maze, you will recursively call your next step. This means that when you find the Finish, the path taken there from the start will be stored _implicitly on the call stack_ based on which calls return `true`. The goal is to write a program that can solve any maze that is passed via the command line or as user input.

## File Breakdown

There are a lot of files in this assignment, but you will not need to change most of them.

### Support Code Classes

There is a lot of support code in this assignment that you **do not** need to change:
- `MazeViewer` provides code to display your maze in a window
- `MazeContents` provides a class that will allow you to encode what is located in a square in a standardized way. It uses an `enum` data type to provide predefined values such as `WALL` and `OPEN` that you will use to encode your maze.
- `MazeDirection` is also a class that uses an enum data type that provides the cardinal directions (`NORTH`, `SOUTH`, `EAST`, and `WEST`)
- `MazeLocation` provides code to allow you to get information about a particular location in a maze, such as what its position is and what its neighbors are
- `DisplayableMaze` is an interface. You will need to implement this interface when you write your `Maze.java` class, but you do not need to change `DisplayableMaze` itself

### Classes You Will Write

- Use `Maze.java` to implement the `DisplayableMaze` interface
- Use `SolveMaze.java` to encode your solution for traversing a maze recursively. There is code provided in this class to help you pass information from SolveMaze to the `MazeViewer`, which will allow you to visualize your maze.

### Test Files

You are provided with two test mazes, `maze1` and `maze2`.  In Phase 3, you will add code to read these mazes in and solve them. Your program will be tested on these and several additional mazes during the grading process.

## Phase One: Encode a Maze

Your first goal should be to get a working `Maze` class that will allow you to instantiate a maze:
1. Start by copying all the method call signatures from the `DisplayableMaze` interface and begin to fi
ll them out (i.e., make stubs) 
2. Add the appropriate instance variables, including a variable `mazeGrid` that will store the maze contents as a **2D array** (an array of arrays) of type `MazeContents`.  (_Using nested `ArrayList`s is also an option but since we don't expect the mazes to resize, it's not really necessary and the bare array has less overhead._)

We are providing a class, `initDemoMaze`, that manually encodes a maze. You can use this method to initialize a simple maze and test your code from 1 & 2 above. If you are seeing problems (a.k.a. red squiggles), that likely means your `Maze` class isn't configured correctly yet.

## Phase Two: Recursive Exploration

For phase two you will implement a recursive "solver" for the maze.  Here are details of the process:

**Problem Statement**: 
Given a maze (showing walls, open regions, and areas that have been already explored using the `MazeContents` enum types), determine whether the Finish can be reached by moving through adjacent open squares without passing through a wall or previously visited square.

**Starting Condition**:
When initialized, the map of the maze should show every open square as unexplored, and the initial location should be the starting point.

**Stop Criteria**:
There are two possible stop conditions: one for a success, and one for a failure.

1. If the current location is the **finish** of the maze, then:
    * Mark the current square as part of the path (`MazeContents.PATH`), and 
    * Report success by returning `true`
2. If the current location is **already visited or is a wall** (`MazeContents.VISITED` or `MazeContents.WALL`), then return `false` to indicate that this is not the correct path to reach the Finish.

**Simplification Step**:
If you are not at a stop condition, you will want to recursively call your maze solver. To do this, first mark the current square as visited (`MazeContents.VISITED`), thus simplifying the problem by reducing the number of "open" squares we still need to search. 

Logically, we know that the Finish is reachable from the current square if and only if it is reachable through one of the adjacent squares, either `MazeDirection.NORTH`, `MazeDirection.SOUTH`, `MazeDirection.EAST`, or `MazeDirection.WEST`. This means that in order to conclude that we can't reach the Finish from this square (Stop Criterion 2), we may need to make up to four recursive calls (one exploring each direction).

You can implement this using a single recursive call that combines the results of exploring in all directions using a `||` operator for the logical or. If any one of these calls returns `true`, the rest will not be evaluated.  Just before returning the result of your recursive call, make sure to mark the current square as either on the path (`MazeContents.PATH`) or a dead end (`MazeContents.DEAD_END`), according to whether the result you're returning is `true` or `false`.

**Output**:
When the recursive solver finishes, the program should print a message indicating whether or not a solution was found.

### Algorithm

Here is an outline of the pseudocode you'll want to write for this phase of the project:

    Start with defining the problem statement.
    Begin exploration from the starting point.
    Determine success or failure criteria:
        If the current location is the finish, report success.
        If the current location is already visited or a wall, report failure.
    Simplify the problem by marking the current square as visited.
    Recursively explore adjacent squares until the Finish is reached or deemed unreachable.
    Print a message indicating whether a solution was found

### Animation

If you include a short delay in your program at the start of each recursive call, you will see the recursion animated by the viewer.  Here is code that will delay for 50 milliseconds:

    try { Thread.sleep(50);	} catch (InterruptedException e) {};

**Note**: Before turning in your assignment, please set the delay to **less than 10 milliseconds**, to facilitate faster grading.

## Phase Three: Reading Maze Files

Solving the same maze over and over again isn't all that interesting.  In this phase, you will make your program read maze files. Two examples are provided as the files `maze1` and `maze2`. A valid input file will contain a character grid representing the contents of a maze.

### Opening Files

For full credit, your program should read the maze in either of two ways :
* if a command line argument is provided, it should load the maze from the file of that name:
    `java MazeSolver maze1`
* if no command line argument is provided, it should read the maze from the standard input.
    `java MazeSolver < maze1`

Both of these input sources can be opened using a `Scanner`, so you should try to read maze files from both inputs using as much of the same code as possible.

### Machine-Readable Maze Encoding

In a map file grid, a `#` represents a wall, and a `.` or ` ` (a space character) represents a passageway.  The symbols `S` and `F`, respectively, represent the start and finish of the maze, and each will appear exactly once. Here is an example of a simple 6x9 maze file:

    #########
    #S..#...#
    #.#.#.#.#
    #.###.#.#
    #.....#F#
    #########

_Note: although this example shows walls completely surrounding the outer perimeter, your program should not assume this will always be the case. Don't wander off the edge of the maze! One way to accomplish this is to make sure that `getContents` returns a wall for any coordinates outside the maze boundary._

Once you can read in the file, add code to encode each square with the appropriate `MazeContents` type depending on what character you find in that square. This will allow you to read a maze from your file and translate it to a machine-readable format that your MazeSolver can explore.

## Closing Notes

Test your code on maze1 and maze2. The support code will allow you to animate your SolveMaze algorithm, turing dead ends red and the final path to the Finish green.

Good luck, Maze Explorers!