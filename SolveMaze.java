class SolveMaze {
  public static void main(String[] args) {
    Maze maze = new Maze();
    SolveMaze SolvedMaze = new SolveMaze();

    MazeViewer viewer = new MazeViewer(maze);
    maze.initDemoMaze();

    MazeLocation startLocation = new MazeLocation(maze.getStart());
    int startRow = startLocation.getRow();
    int startCol = startLocation.getCol();

    System.out.println(startRow);
    System.out.println(startCol);

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

    MazeLocation previous;

    if(north.equals(maze.getFinish()) || south.equals(maze.getFinish()) || east.equals(maze.getFinish()) || west.equals(maze.getFinish()) ) {
      maze.setPath(row, col);
      return true;
    }

    boolean solved = false;
    if(!current.equals(maze.getStart())){
      maze.setDeadEnd(row, col);
    }

    if(current.equals(maze.getStart())){
      maze.setPath(row, col);
    }

    if(maze.getContents(north.getRow(), north.getCol()) == MazeContents.OPEN && !solved){
      maze.setVisited(row, col);
      setCurrent(current, row, col);
      solved = solve(maze, row-1, col);
    }

    if(maze.getContents(south.getRow(), south.getCol()) == MazeContents.OPEN && !solved){
      maze.setVisited(row, col);
      setCurrent(current, row, col);
      solved = solve(maze, row+1, col);
    }

    if(maze.getContents(east.getRow(), east.getCol()) == MazeContents.OPEN && !solved){
      maze.setVisited(row, col);
      setCurrent(current, row, col);
      solved = solve(maze, row, col + 1);
    }

    if(maze.getContents(west.getRow(), west.getCol()) == MazeContents.OPEN && !solved){
      maze.setVisited(row, col);
      setCurrent(current, row, col);
      solved = solve(maze, row, col-1);
    }
    return solved;

  }

  public void setCurrent(MazeLocation loc, int row, int col){
    loc.setRow(row);
    loc.setCol(col);
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

//  public void path(Maze maze, int row, int col, MazeLocation dir){
//    if(maze.)
//  }



}
