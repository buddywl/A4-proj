# Pseudocode

## SolveMaze.java Methods
#### mazeInit:
mazeInit() is a function that basically "runs" the maze solver. It Instantiates a new maze object and fills the mazegrid of that maze given what is inputted as a parameter. The function then calls solveMaze in an if statement. Since solveMaze() returns a boolean value, it either runs until a solution to the maze has been found, or until the entire maze has been explored. This function then prints whether or not a solution was found depending on the value returned by solveMaze().
````plaintext
mazeInit(filename):
    clear the ArrayList containing all visited squares (visited)
    Instantiate a new maze object (maze)
    
    if the filename is not empty:
        make a new 2D char array (mazeArray) containing the data read from the maze text file
        set the mazegrid of maze using the data from mazeArray
    else:
        set the mazegrid to the data defined by the demo maze
    
          Instantiate a new MazeViewer object (viewer) to display the maze
    Get the location of the start of the maze
    
    If solveMaze(maze, startRow, startCol) returns true:
        print("The maze was solved")
    else 
        print("No solution was found")
        
````

#### solveMaze:

````plaintext
solveMaze(maze, row, col):          --> returns a boolean
    Instantiate a new MazeLocation variable (current) that stores the current location at [row][col]
    
    Instantiate new MazeLocation variables that store the neighboring squares relative to current
        --> north: the square at direction NORTH of the current square
        --> south: the square at direction SOUTH of the current square
        --> east: the square at direction EAST of the current square
        --> west: the square at direction WEST of the current square
        
    if the current square is the start square:
        set the value of the MazeContents at the current location to VISITED
        add the current location to an ArrayList (visited) containing all square that have been visited
        
    if any of the neighboring squares is the finish square:
        for every square in visited:
            set the MazeContents of that square to PATH
        
        set the MazeContents of the current square to PATH
        set the MazeContents of the finish square to PATH
        return true
    
    Instantiate a new boolean variable (isSolved) storing whetehr or not the maze has been solved (defaults to false)
    if the current location is not the start of the maze:
        set the MazeContents of the current square to VISITED
        add the current square to the list of visited squares
        add a 10 millisecond delay (for animation purposes)
    
    if north is open and isSolved is false:
        isSolved = value returned by calling solve on the location of the square to the north
    
    if south is open and isSolved is false:
        isSolved = value returned by calling solve on the location of the square to the south
    
    if east is open and isSolved is false:
        isSolved = value returned by calling solve on the location of the square to the east
    
    if west is open and isSolved is false:
        isSolved = value returned by calling solve on the location of the square to the west
    
    if isSolved is false:       --> none of the neighboring sqaures are open but the maze hasn't been solved yet
        remove the current square from the list of visited squares
        set the MazeContents of the current square to DEAD_END
        
     return isSolved
````

-----
## Maze.java Methods
#### setPath:
````plaintext
setPath(row location, col location):
    set maze contents at mazegrid[row location][col location] to PATH
````

#### setVisited:
````plaintext
setVisited(row location, col location):
    set maze contents at mazegrid[row location][col location] to VISITED
````

#### setDeadEnd:
````plaintext
setDeadEnd(row location, col location):
    set maze contents at mazegrid[row location][col location] to DEAD_END
````

#### makeMaze:
````plaintext
makeMaze(filename):         --> returns a 2D array of chars
    Instantiate int variables for # of rows and cols
    try:
        Instantiate a new scanner object (scan) to read the file
        Instantiate a new string builder (sb)
        while scan.hasNext():
            append the whole line to sb
            increment # of rows
            
        set the # of cols to the length of sb/rows
        Instantiate a new 2D array of chars (maze) to store the maze data with dimensions [rows][cols]
        Instantiate a new indexing variable (count) to parse through sb
        
        for each row in the maze:
            for each col in the maze:
                set the value at the current loaction to the char at count
        
        return the maze
    catch (FileNotFoundException e):
        print("Cannot locate file)
        exit system
        throw a FileNotFoundException()

````

#### setMazeGrid:
takes in a 2D array of chars with maze data and uses that data to fill in a maze grid
````plaintext
setMazeGrid(maze):      
    define variables converting the maze data to understandable values
        --> wall = #
        --> st = S
        --> space = .
        --> fin = F
    
    set the height of the mazegrid to the number of rows in the maze
    set the width of the mazegrid to the number of columns in the maze
    make a new mazegrid with dimensions [height] x [width]

    for each row in the maze:
        for each column in the maze:
            if the char @ the current location is == wall:
                set the contents of the maze grid to WALL
            else if the char @ the current location is == st:
                set the contents of the maze grid to OPEN
                set the start of the maze to the current location
            else if the char @ the current location is == space:
                set the contents of the maze grid to OPEN
            else if the char @ the current location is == st:
                set the contents of the maze grid to OPEN
                set the finish of the maze to the current location
            
````