import java.io.*;
import java.util.*;

class SolveMaze {
  public static ArrayList<MazeLocation> visited = new ArrayList<>();
  public static void main(String[] args) throws IOException {
    SolveMaze SolvedMaze = new SolveMaze();

    Scanner input = new Scanner(System.in);
    System.out.print("INPUT FILE NAME: ");
    String filename = input.nextLine();

    File filepath = new File((args.length > 0) ? args[0] : filename);
    String maze = filepath.getAbsolutePath();

    SolvedMaze.maze(maze);
    SolvedMaze.maze();
  }
  public void maze(String filename) throws IOException {
    SolveMaze SolvedMaze = new SolveMaze();

    reset(visited);
    Maze maze = new Maze();

    char[][] mazeArray = maze.makeMaze(filename);
    maze.setMazeGrid(mazeArray);

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

  public void maze(){
    SolveMaze SolvedMaze = new SolveMaze();

    reset(visited);
    Maze maze = new Maze();
    maze.initDemoMaze();

    MazeViewer viewer = new MazeViewer(maze);

    MazeLocation start = new MazeLocation(maze.getStart());
    int sRow = start.getRow();
    int sCol = start.getCol();

    if(SolvedMaze.solve(maze, sRow, sCol)){
      System.out.println("The maze was solved!");
    } else {
      System.out.println("No solution was found.");
    }
  }

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
      try {
        Thread.sleep(10);
      } catch (InterruptedException ignored) {
      }
    }
//    else {
//      maze.setVisited(row, col);
//      try {
//        Thread.sleep(10);
//      } catch (InterruptedException ignored) {
//      }
//    }

    if (maze.getContents(north.getRow(), north.getCol()).equals(MazeContents.OPEN) && !solved) {
      solved = solve(maze, row - 1, col);
    }
    if (maze.getContents(south.getRow(), south.getCol()).equals(MazeContents.OPEN) && !solved) {
      solved = solve(maze, row + 1, col);
    }
    if (maze.getContents(east.getRow(), east.getCol()).equals(MazeContents.OPEN) && !solved) {
      solved = solve(maze, row, col + 1);
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

  public <T> void reset(ArrayList<T> A){
    A.clear();
  }

//  public void setCurrent(MazeLocation loc, int row, int col) {
//    loc.setRow(row);
//    loc.setCol(col);
//  }
//
//  public void mazeAlgo(Maze maze, int row, int col, MazeLocation current, MazeLocation ahead) {
////    MazeLocation rNorth = current.reverse(MazeDirection.NORTH);
////    MazeLocation rSouth = current.reverse(MazeDirection.SOUTH);
////    MazeLocation rEast = current.reverse(MazeDirection.EAST);
////    MazeLocation rWest = current.reverse(MazeDirection.WEST);
////
//
//    maze.setVisited(row, col);
//    setCurrent(current, row, col);
////    if(maze.getContents(current.getRow(), current.getCol()) == MazeContents.VISITED || maze.getContents(current.getRow(), current.getCol()) == MazeContents.WALL){
////      System.out.println("failure");
////    } else if (current == maze.getFinish()){
////      System.out.println("success");
////    }
//    visited.add(current);
//    try {
//      Thread.sleep(100);
//    } catch (InterruptedException ignored) {
//    }
//    ;
//  }
}

