import java.util.ArrayList;

class SolveMaze {
  ArrayList<MazeLocation> junctions = new ArrayList<>();
  boolean[] possibleDirections = new boolean[4];

  public static void main(String[] args) {
    Maze maze = new Maze();
    SolveMaze SolvedMaze = new SolveMaze();

    MazeViewer viewer = new MazeViewer(maze);
    maze.initDemoMaze();

    MazeLocation startLocation = new MazeLocation(maze.getStart());
    int startRow = startLocation.getRow();
    int startCol = startLocation.getCol();

    System.out.println(SolvedMaze.junctions);

    System.out.println(SolvedMaze.solve(maze, startRow, startCol));

  }

  public boolean solve(Maze maze, int row, int col){

    MazeLocation current = new MazeLocation(row, col);
    int startRow = current.getRow();
    int startCol = current.getCol();

    MazeLocation north = current.neighbor(MazeDirection.NORTH);
    MazeLocation south = current.neighbor(MazeDirection.SOUTH);
    MazeLocation east = current.neighbor(MazeDirection.EAST);
    MazeLocation west = current.neighbor(MazeDirection.WEST);


    if(north.equals(maze.getFinish()) || south.equals(maze.getFinish()) || east.equals(maze.getFinish()) || west.equals(maze.getFinish()) ) {
      maze.setPath(row, col);
      setCurrent(current, maze.getFinish().getRow(), maze.getFinish().getCol());
      maze.setPath(maze.getFinish().getRow(), maze.getFinish().getCol());

      return true;
    }

    boolean solved = false;
    if(current.equals(maze.getStart())){
      maze.setPath(maze.getStart().getRow(), maze.getStart().getCol());
    } else {
      maze.setDeadEnd(row, col);
    }

    if((maze.getContents(north.getRow(), north.getCol()).equals(MazeContents.OPEN) || maze.getContents(north.getRow(), north.getCol()).equals(MazeContents.VISITED)) && !solved){
      mazeAlgo(maze, row, col, current, north);
      possibleDirections[0] = true;
      if(hasMore(possibleDirections) > 1){
        junctions.add(current);
      }
//      solved = solve(maze, row-1, col);
    }else if((maze.getContents(south.getRow(), south.getCol()).equals(MazeContents.OPEN) || maze.getContents(south.getRow(), south.getCol()).equals(MazeContents.VISITED)) && !solved){
      mazeAlgo(maze, row, col, current, south);
      possibleDirections[1] = true;
      if(hasMore(possibleDirections) > 1){
        junctions.add(current);
      }
//      solved = solve(maze, row+1, col);
    }else if((maze.getContents(east.getRow(), east.getCol()).equals(MazeContents.OPEN) || maze.getContents(south.getRow(), south.getCol()).equals(MazeContents.VISITED)) && !solved){
      mazeAlgo(maze, row, col, current, east);
      possibleDirections[2] = true;
      if(hasMore(possibleDirections) > 1){
        junctions.add(current);
      }
//      solved = solve(maze, row, col + 1);
    }else if((maze.getContents(west.getRow(), west.getCol()).equals(MazeContents.OPEN) || maze.getContents(south.getRow(), south.getCol()).equals(MazeContents.VISITED)) && !solved){
      mazeAlgo(maze, row, col, current, west);
      possibleDirections[3] = true;
      if(hasMore(possibleDirections) > 1){
        junctions.add(current);
      }
//      solved = solve(maze, row, col-1);
    }

    if(possibleDirections[0]){
      reset(possibleDirections);
      solved = solve(maze, row-1, col);
    } else if (possibleDirections[1]){
      reset(possibleDirections);
      solved = solve(maze, row+1, col);
    } else if (possibleDirections[2]){
      reset(possibleDirections);
      solved = solve(maze, row, col+1);
    }else if (possibleDirections[3]){
      reset(possibleDirections);
      solved = solve(maze, row, col-1);
    }

    return solved;
  }

  public void setCurrent(MazeLocation loc, int row, int col){
    loc.setRow(row);
    loc.setCol(col);
  }
  public int hasMore(boolean[] list){
    int count = 0;
    for(boolean x : list){
      if(x){
        count++;
      }
    }
    return count;
  }

  public void reset(boolean[] list){
    for(boolean x : list){
      x = false;
    }
  }
  public boolean isWall(Maze maze, MazeLocation dir){
    int row = dir.getRow();
    int col = dir.getCol();
    return maze.getContents(row, col) == MazeContents.WALL;
  }

  public boolean isVisited(Maze maze, MazeLocation dir){
    int row = dir.getRow();
    int col = dir.getCol();
    return maze.getContents(row, col) == MazeContents.VISITED;
  }

  public boolean isDeadEnd(Maze maze, MazeLocation dir){
    int row = dir.getRow();
    int col = dir.getCol();
    return maze.getContents(row, col) == MazeContents.DEAD_END;
  }

  public boolean isOpen(Maze maze, MazeLocation dir){
    int row = dir.getRow();
    int col = dir.getCol();
    return maze.getContents(row, col) == MazeContents.OPEN;
  }

  public void mazeAlgo(Maze maze, int row, int col, MazeLocation current, MazeLocation ahead){
    MazeLocation rNorth = current.reverse(MazeDirection.NORTH);
    MazeLocation rSouth = current.reverse(MazeDirection.SOUTH);
    MazeLocation rEast = current.reverse(MazeDirection.EAST);
    MazeLocation rWest = current.reverse(MazeDirection.WEST);


    maze.setVisited(row, col);
    setCurrent(current, row, col);
    if(maze.getContents(current.getRow(), current.getCol()) == MazeContents.VISITED || maze.getContents(current.getRow(), current.getCol()) == MazeContents.WALL){
      System.out.println("failure");
    } else if (current == maze.getFinish()){
      System.out.println("success");
    }
//    if(junctions.size() == 0){
//      maze.setPath(row, col);
//    }
//    if(!((maze.getContents(rNorth.getRow(), rNorth.getCol()) == MazeContents.DEAD_END || maze.getContents(rSouth.getRow(), rSouth.getCol()) == MazeContents.DEAD_END || maze.getContents(rEast.getRow(), rEast.getCol()) == MazeContents.DEAD_END || maze.getContents(rWest.getRow(), rWest.getCol()) == MazeContents.DEAD_END) && maze.getContents(ahead.getRow(), ahead.getCol()) == MazeContents.VISITED)) {
//      maze.setPath(row, col);
//    }
    try { Thread.sleep(500);	} catch (InterruptedException ignored) {};
  }

























import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

  /**
   * @author Prashant Ghimire
   * The purpose of this class is to :
   * -> find the start point of the maze and drive all the way to the goal. Also, it trace the correct path that it moves on.
   * -> different methods, which are described below are used to accomplish this task.
   */
  public class Maze {
    private char[][] theMaze;
    private int colStart, rowStart;
    private int rows, cols;
    private String outputFilename; // this is a way to pass the output filename from the main method to this class

    public Maze(String filename) throws IOException {
      try {
        this.outputFilename = filename;
        Scanner scan = new Scanner(new File(filename));
        StringBuilder sb = new StringBuilder();
        while (scan.hasNext()) {
          sb.append(scan.nextLine());
          this.rows++;
        }
        this.cols = sb.length() / this.rows;
        this.theMaze = new char[this.rows][this.cols];
        int m = 0;
        System.out.println();
        for (int i = 0; i < this.rows; i++) {
          for (int j = 0; j < this.cols; j++) {
            this.theMaze[i][j] = sb.charAt(m++);
          }
        }
        scan.close();
        findStart();
        solve(this.rowStart, this.colStart);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        System.out.println("ERROR : " + e.getMessage());
      }
    }

    /**
     * instantiate the index value value of 'S' to this.rowStart, this.colStart
     */
    private void findStart() {
      for (int i = 0; i < this.rows; i++) {
        for (int j = 0; j < this.cols; j++) {
          if (theMaze[i][j] == 'S') {
            this.rowStart = i;
            this.colStart = j;
          }
        }
      }
    }

    /**
     * @param row  , current row position of the char
     * @param col, current column index of the char
     * @return true if solved, false otherwise.
     * <p>
     * Program Description :
     * the program checks if any side positions are ' ' and are "solved"
     * remember these all are if statements and more than one of them can run and return values being at the same index.
     * if we had done return solve(row, col + 1) , the program would exit without checking other
     * possibilities. (infinite recursion/ stack overflow are other possibilities). The problem
     * is not with the single path, the problem arises when we have MULTIPLE path to go from a point and only one
     * of those path works.
     * how this algorithm handles this problem is it will wait to see if those paths are really valid at junctions.
     * it fills the position that row,col were pointing right before with '.' (can be any other characters except ' ', the advantage is it will prevent infinite
     * recursion. Infinite recursion occurs when function(a,b) and function(c,d) keep calling each other infinitely because the condition is always true)
     * if one path is not valid , solved = false; will return but the other "trees" will still be exploring the path.
     * this process continues until we reach 'G'.
     */
    private boolean solve(int row, int col) {
      char right = this.theMaze[row][col + 1];
      char left = this.theMaze[row][col - 1];
      char up = this.theMaze[row - 1][col];
      char down = this.theMaze[row + 1][col];
      if (right == 'G' || left == 'G' || up == 'G' || down == 'G') {
        this.theMaze[row][col] = '.'; // adding path trace mark to the position we ARE once
        // we're sure we reached the
        // destination
        try {
          File file = new File(this.outputFilename + ".solved"); // creating an output file and naming
          //according to the problem's requirement
          PrintWriter writer = new PrintWriter(file);
          for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
              writer.print(this.theMaze[i][j]);
            }
            writer.println();
          }
          writer.close();
        } catch (FileNotFoundException e) {
          System.out.println("ERROR : " + e.getMessage());
        }
        return true; // return true once we reach the destination.
      }

      boolean solved = false;
      if (this.theMaze[row][col] != 'S') {
        this.theMaze[row][col] = '.'; // we don't want to mess up by changing value of 'S' when we start our journey.
      }
      if (right == ' ' && !solved) {
        solved = solve(row, col + 1);
      }
      if (down == ' ' && !solved) {
        solved = solve(row + 1, col);
      }
      if (left == ' ' && !solved) {
        solved = solve(row, col - 1);
      }
      if (up == ' ' && !solved) {
        solved = solve(row - 1, col);
      }
      if (!solved) {
        this.theMaze[row][col] = ' ';  // unsolved path are no good. so lets clear our mark.
      }
      return solved;      // return false if nothing matches.(execute in one branch)
      // I suppose all solved: boolean will return false and the solve() function will finally return false when
    }              // no path exists. Eventually every path will lead to dead end.
  }


}



