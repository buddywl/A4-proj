import java.io.*;
import java.util.*;

/* This class should implement the DisplayableMaze interface */
public class Maze implements DisplayableMaze{

    int height;
    int width;
    MazeContents[][] mazeGrid = new MazeContents[height][width];

    MazeLocation start;

    MazeLocation finish;

    /** The DemoMaze method will allow you to generate a simple maze
     * To test your code on as you develop it. Ultimately, you will want
     * to accept maze files as command line inputs or standard input.
     * * @author Tianah Gooden
     * * @version October 17th 2023
     */
    public void initDemoMaze(){ //String fileName,
        this.height = 10;
        this.width = 8;
        this.mazeGrid = new MazeContents[height][width];
        this.start = new MazeLocation(1,1);
        this.finish = new MazeLocation(8,6);

        this.mazeGrid[0][0] = MazeContents.WALL; this.mazeGrid[0][1] = MazeContents.WALL; this.mazeGrid[0][2] = MazeContents.WALL; this.mazeGrid[0][3] = MazeContents.WALL; this.mazeGrid[0][4] = MazeContents.WALL; this.mazeGrid[0][5] = MazeContents.WALL; this.mazeGrid[0][6] = MazeContents.WALL; this.mazeGrid[0][7] = MazeContents.WALL;
        this.mazeGrid[1][0] = MazeContents.WALL; this.mazeGrid[1][1] = MazeContents.OPEN; this.mazeGrid[1][2] = MazeContents.OPEN; this.mazeGrid[1][3] = MazeContents.OPEN; this.mazeGrid[1][4] = MazeContents.OPEN; this.mazeGrid[1][5] = MazeContents.OPEN; this.mazeGrid[1][6] = MazeContents.WALL; this.mazeGrid[1][7] = MazeContents.WALL;
        this.mazeGrid[2][0] = MazeContents.WALL; this.mazeGrid[2][1] = MazeContents.WALL; this.mazeGrid[2][2] = MazeContents.OPEN; this.mazeGrid[2][3] = MazeContents.WALL; this.mazeGrid[2][4] = MazeContents.WALL; this.mazeGrid[2][5] = MazeContents.OPEN; this.mazeGrid[2][6] = MazeContents.WALL; this.mazeGrid[2][7] = MazeContents.WALL;
        this.mazeGrid[3][0] = MazeContents.WALL; this.mazeGrid[3][1] = MazeContents.OPEN; this.mazeGrid[3][2] = MazeContents.WALL; this.mazeGrid[3][3] = MazeContents.OPEN; this.mazeGrid[3][4] = MazeContents.OPEN; this.mazeGrid[3][5] = MazeContents.OPEN; this.mazeGrid[3][6] = MazeContents.WALL; this.mazeGrid[3][7] = MazeContents.WALL;
        this.mazeGrid[4][0] = MazeContents.WALL; this.mazeGrid[4][1] = MazeContents.OPEN; this.mazeGrid[4][2] = MazeContents.OPEN; this.mazeGrid[4][3] = MazeContents.OPEN; this.mazeGrid[4][4] = MazeContents.WALL; this.mazeGrid[4][5] = MazeContents.WALL; this.mazeGrid[4][6] = MazeContents.OPEN; this.mazeGrid[4][7] = MazeContents.WALL;
        this.mazeGrid[5][0] = MazeContents.WALL; this.mazeGrid[5][1] = MazeContents.OPEN; this.mazeGrid[5][2] = MazeContents.WALL; this.mazeGrid[5][3] = MazeContents.OPEN; this.mazeGrid[5][4] = MazeContents.OPEN; this.mazeGrid[5][5] = MazeContents.WALL; this.mazeGrid[5][6] = MazeContents.WALL; this.mazeGrid[5][7] = MazeContents.WALL;
        this.mazeGrid[6][0] = MazeContents.WALL; this.mazeGrid[6][1] = MazeContents.OPEN; this.mazeGrid[6][2] = MazeContents.WALL; this.mazeGrid[6][3] = MazeContents.WALL; this.mazeGrid[6][4] = MazeContents.OPEN; this.mazeGrid[6][5] = MazeContents.OPEN; this.mazeGrid[6][6] = MazeContents.OPEN; this.mazeGrid[6][7] = MazeContents.WALL;
        this.mazeGrid[7][0] = MazeContents.WALL; this.mazeGrid[7][1] = MazeContents.OPEN; this.mazeGrid[7][2] = MazeContents.WALL; this.mazeGrid[7][3] = MazeContents.OPEN; this.mazeGrid[7][4] = MazeContents.OPEN; this.mazeGrid[7][5] = MazeContents.WALL; this.mazeGrid[7][6] = MazeContents.OPEN; this.mazeGrid[7][7] = MazeContents.WALL;
        this.mazeGrid[8][0] = MazeContents.WALL; this.mazeGrid[8][1] = MazeContents.OPEN; this.mazeGrid[8][2] = MazeContents.OPEN; this.mazeGrid[8][3] = MazeContents.WALL; this.mazeGrid[8][4] = MazeContents.OPEN; this.mazeGrid[8][5] = MazeContents.WALL; this.mazeGrid[8][6] = MazeContents.OPEN; this.mazeGrid[8][7] = MazeContents.WALL;
        this.mazeGrid[9][0] = MazeContents.WALL; this.mazeGrid[9][1] = MazeContents.WALL; this.mazeGrid[9][2] = MazeContents.WALL; this.mazeGrid[9][3] = MazeContents.WALL; this.mazeGrid[9][4] = MazeContents.WALL; this.mazeGrid[9][5] = MazeContents.WALL; this.mazeGrid[9][6] = MazeContents.WALL; this.mazeGrid[9][7] = MazeContents.WALL;
  }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public MazeContents getContents(int i, int j) {
        return mazeGrid[i][j];
    }

    public MazeLocation getStart() {
        return this.start;
    }

    public MazeLocation getFinish() {
        return this.finish;
    }

    public static void main(String[] args) throws IOException {

        Maze newMaze = new Maze();
        char[][] mazeArray = newMaze.makeMaze("C:\\Users\\buddy\\Desktop\\CSC210\\A4-Template\\maze1");
        String maze = Arrays.deepToString(mazeArray);
        System.out.println(maze);
        System.out.println(mazeArray.length);
        System.out.println(mazeArray[0].length);

        newMaze.setMazeGrid(mazeArray);
        MazeViewer viewer = new MazeViewer(newMaze);
    }

    public void setPath(int i, int j){
      mazeGrid[i][j] = MazeContents.PATH;
    }

    public void setVisited(int i, int j){
      mazeGrid[i][j] = MazeContents.VISITED;
    }

    public void setDeadEnd(int i, int j){
      mazeGrid[i][j] = MazeContents.DEAD_END;
    }

    public char[][] makeMaze(String filename) throws IOException{
      int rows = 0;
      int cols = 0;
      try{
        Scanner scan = new Scanner(new File(filename));
        StringBuilder sb = new StringBuilder();
        while (scan.hasNext()) {
          sb.append(scan.nextLine());
          rows++;
        }
        cols = sb.length()/rows;
        char[][] maze = new char[rows][cols];
        int i = 0;

        for(int row = 0; row < rows; row++){
          for(int col = 0; col < cols; col++){
            maze[row][col] = sb.charAt(i++);
          }
        }
        return maze;
      } catch (FileNotFoundException e) {
        System.err.println("Cannot locate file.");
        System.exit(-1);
      }
      return null;
    }

    public void setMazeGrid(char[][] maze){
      String blocks = "#S.F";
      char wall = blocks.charAt(0);
      char st = blocks.charAt(1);
      char space = blocks.charAt(2);
      char fin = blocks.charAt(3);

      this.height = maze.length;
      this.width = maze[0].length;
      this.mazeGrid = new MazeContents[height][width];

      for(int row = 0; row < maze.length; row++){
        for(int col = 0; col < maze[0].length; col++){
          if(maze[row][col] == wall){
            this.mazeGrid[row][col] = MazeContents.WALL;

          } else if (maze[row][col] == st){
            this.mazeGrid[row][col] = MazeContents.OPEN;
            this.start = new MazeLocation(row, col);

          } else if (maze[row][col] == space){
            this.mazeGrid[row][col] = MazeContents.OPEN;

          } else if (maze[row][col] == fin){
            this.mazeGrid[row][col] = MazeContents.OPEN;
            this.finish = new MazeLocation(row, col);

          }

        }
      }
    }

}

