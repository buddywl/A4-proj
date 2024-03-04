import java.io.*;
import java.util.*;

class SolveMaze {
    public static ArrayList<MazeLocation> visited = new ArrayList<>();

    /**
     * takes in user input in the command line to load a maze file
     * demonstrates an instance of maze solving: if a file is provided, that maze is used as default, else, demo maze is used)
     * @param args command line arguments (ignored)
     * @throws FileNotFoundException if the file does not exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        SolveMaze SolvedMaze = new SolveMaze();

        Scanner input = new Scanner(System.in);
        System.out.print("INPUT FILE NAME: ");
        String filename = input.nextLine();
        String maze = "";

        if(filename != ""){
            File filepath = new File((args.length > 0) ? args[0] : filename);
            maze = filepath.getAbsolutePath();
        }

        SolvedMaze.maze(maze);
    }

    /**
     * Takes in a filename of a text file containing maze data to construct and display the maze using MazeViewer.
     * Then, it displays the maze and solves it.
     *
     * @param filename the name of the file
     * @throws FileNotFoundException if the file does not exist
     */
    public void maze(String filename) throws FileNotFoundException {
        SolveMaze SolvedMaze = new SolveMaze();

        visited.clear();
        Maze maze = new Maze();

        if (filename != ""){
            char[][] mazeArray = maze.makeMaze(filename);
            maze.setMazeGrid(mazeArray);
        } else {
            maze.initDemoMaze();
        }

        MazeViewer viewer = new MazeViewer(maze);

        MazeLocation startLocation = new MazeLocation(maze.getStart());
        int sRow = startLocation.getRow();
        int sCol = startLocation.getCol();

        if(SolvedMaze.solve(maze, sRow, sCol)){
            System.out.println("The maze was solved!");
        } else {
            System.out.println("No solution was found.");
        }
    }

    /**
     * Intakes a maze and the current location (row, col) and recursively solves the maze by
     * visiting open squares and determining whether or not that path is a dead end or not. If its
     * a dead end, that path is marked in red, and if the finish is found, the solution path is
     * highlighted in a darker green.
     *
     * @param maze (Maze) the maze to be solved
     * @param row (int) the row value of the current location
     * @param col (int) the column value of the current location
     * @return (boolean) whether or not the maze was solved
     */
    public boolean solve(Maze maze, int row, int col) {
        MazeLocation current = new MazeLocation(row, col);

        MazeLocation north = current.neighbor(MazeDirection.NORTH);
        MazeLocation south = current.neighbor(MazeDirection.SOUTH);
        MazeLocation east = current.neighbor(MazeDirection.EAST);
        MazeLocation west = current.neighbor(MazeDirection.WEST);

        if (north.equals(maze.getFinish()) || south.equals(maze.getFinish()) || east.equals(maze.getFinish()) || west.equals(maze.getFinish())) {
            maze.setPath(maze.getStart().getRow(), maze.getStart().getCol());
            for(MazeLocation element : visited){
                maze.setPath(element.getRow(), element.getCol());
            }
            maze.setPath(current.getRow(), current.getCol());
            maze.setPath(maze.getFinish().getRow(), maze.getFinish().getCol());
            return true;
        }

        boolean solved = false;
        if (!current.equals(maze.getStart())) {
            maze.setVisited(current.getRow(), current.getCol());
            visited.add(current);
            try { Thread.sleep(10);} catch (InterruptedException ignored) {};
        }

        if (maze.getContents(north.getRow(), north.getCol()).equals(MazeContents.OPEN) && !solved) {
            solved = solve(maze, row - 1, col);
        }
        if (maze.getContents(east.getRow(), east.getCol()).equals(MazeContents.OPEN) && !solved) {
            solved = solve(maze, row, col + 1);
        }
        if (maze.getContents(south.getRow(), south.getCol()).equals(MazeContents.OPEN) && !solved) {
            solved = solve(maze, row + 1, col);
        }
        if (maze.getContents(west.getRow(), west.getCol()).equals(MazeContents.OPEN) && !solved) {
            solved = solve(maze, row, col - 1);
        }
        if(!solved){
            visited.remove(current);
            maze.setDeadEnd(current.getRow(), current.getCol());
        }
        return solved;
    }

}

