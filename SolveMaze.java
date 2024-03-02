import java.io.IOException;
import java.util.*;

class SolveMaze {
   public static ArrayList<MazeLocation> visited = new ArrayList<>();
   public static ArrayList<MazeLocation> junctions = new ArrayList<>();
  public static void main(String[] args) throws IOException {
    SolveMaze SolvedMaze = new SolveMaze();

    Maze newMaze = new Maze();
    char[][] mazeArray = newMaze.makeMaze("C:\\Users\\buddy\\Desktop\\CSC210\\A4-Template\\maze1");
    String maze = Arrays.deepToString(mazeArray);

    newMaze.setMazeGrid(mazeArray);
    MazeViewer viewer = new MazeViewer(newMaze);


    MazeLocation startLocation = new MazeLocation(newMaze.getStart());
    int startRow = startLocation.getRow();
    int startCol = startLocation.getCol();

    System.out.println(SolvedMaze.solve(newMaze, startRow, startCol));
    SolvedMaze.path(newMaze);

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
//      setCurrent(current, maze.getFinish().getRow(), maze.getFinish().getCol());
//      maze.setPath(maze.getFinish().getRow(), maze.getFinish().getCol());

      return true;
    }

    boolean solved = false;
    if(!current.equals(maze.getStart())){
      maze.setPath(maze.getStart().getRow(), maze.getStart().getCol());
    }
//    else {
//      maze.setVisited(row, col);
//      try { Thread.sleep(10);	} catch (InterruptedException ignored) {}
//    }
    if(maze.getContents(north.getRow(), north.getCol()).equals(MazeContents.OPEN) && !solved){
//      mazeAlgo(maze, row, col, current, north);
      solved = solve(maze, row-1, col);
    }

    if(maze.getContents(south.getRow(), south.getCol()).equals(MazeContents.OPEN) && !solved){
//      mazeAlgo(maze, row, col, current, south);
      solved = solve(maze, row+1, col);
    }

    if(maze.getContents(east.getRow(), east.getCol()).equals(MazeContents.OPEN) && !solved){
//      mazeAlgo(maze, row, col, current, east);
      solved = solve(maze, row, col + 1);
    }

    if(maze.getContents(west.getRow(), west.getCol()).equals(MazeContents.OPEN) && !solved){
//      mazeAlgo(maze, row, col, current, west);
      solved = solve(maze, row, col-1);
    }

    return solved;

  }

  public void setCurrent(MazeLocation loc, int row, int col){
    loc.setRow(row);
    loc.setCol(col);
  }
  public void mazeAlgo(Maze maze, int row, int col, MazeLocation current, MazeLocation ahead){
//    MazeLocation rNorth = current.reverse(MazeDirection.NORTH);
//    MazeLocation rSouth = current.reverse(MazeDirection.SOUTH);
//    MazeLocation rEast = current.reverse(MazeDirection.EAST);
//    MazeLocation rWest = current.reverse(MazeDirection.WEST);
//

    maze.setVisited(row, col);
    setCurrent(current, row, col);
//    if(maze.getContents(current.getRow(), current.getCol()) == MazeContents.VISITED || maze.getContents(current.getRow(), current.getCol()) == MazeContents.WALL){
//      System.out.println("failure");
//    } else if (current == maze.getFinish()){
//      System.out.println("success");
//    }
    visited.add(current);
    try { Thread.sleep(100);	} catch (InterruptedException ignored) {};
  }

  public void deadEnd(Maze maze){
    System.out.println(visited);
    processingMethods processor = new processingMethods();
    ArrayList<MazeLocation> duplicates = processor.duplicates(visited);
    System.out.println(duplicates);
    for(MazeLocation loc : duplicates){
      maze.setDeadEnd(loc.getRow(), loc.getCol());
    }
  }

  public void path(Maze maze){
    System.out.println(visited);
    processingMethods processor = new processingMethods();
    ArrayList<MazeLocation> drawPath = processor.removeDuplicates(visited);
    System.out.println(drawPath);

    for(MazeLocation loc : drawPath){
      maze.setPath(loc.getRow(), loc.getCol());
    }

    for(int i = 0; i < drawPath.size()-1; i++){
      MazeLocation current = drawPath.get(i);
      MazeLocation next = drawPath.get(i+1);

      if(current.getRow() != next.getRow() && current.getCol() != next.getCol()){
        maze.setPath(findVisited(maze, current.getRow(), current.getCol()).getRow(), findVisited(maze, current.getRow(), current.getCol()).getCol());
      } else if (current.getRow() == next.getRow() && current.getCol() != next.getCol()+1 && current.getCol() != next.getCol()-1){
        if (current.getCol() != next.getCol()-1){
          maze.setPath(current.getRow(), current.getCol()+1);
        } else if (current.getCol() != next.getCol()+1){
          maze.setPath(current.getRow(), current.getCol()-1);
        }
        maze.setPath(current.getRow(), current.getCol()+1);
      } else if (current.getCol() == next.getCol() && current.getRow() != next.getRow()+1 && current.getRow() != next.getRow()-1){
        if (current.getRow() != next.getRow()-1){
          maze.setPath(current.getRow()+1, current.getCol());
        } else if (current.getRow() != next.getRow()+1){
          maze.setPath(current.getRow()-1, current.getCol());
        }
      }
    }

  }

  public MazeLocation findVisited(Maze maze, int row, int col){
    MazeLocation current = new MazeLocation(row, col);

    MazeLocation north = current.neighbor(MazeDirection.NORTH);
    MazeLocation south = current.neighbor(MazeDirection.SOUTH);
    MazeLocation east = current.neighbor(MazeDirection.EAST);
    MazeLocation west = current.neighbor(MazeDirection.WEST);

    MazeLocation[] directions = new MazeLocation[]{north, south, east, west};

    for (MazeLocation direction : directions) {
      MazeContents cur = maze.getContents(direction.getRow(), direction.getCol());
      if (cur == MazeContents.VISITED) {
        return direction;
      }
    }
    return current;
  }


}

