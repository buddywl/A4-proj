import java.io.*;
import java.util.Scanner;

/** Class containing a file reading demo */
public class ReadFileDemo {
    /** The main method reads from a file and prints the contents. */
    public static void main(String[] args) {
        // Figuring out input source
        Scanner file = null;
        if (args.length >0) {
            String filename = args[0];
            try {
                file = new Scanner(new File(filename));
            } catch (FileNotFoundException e) {
                System.err.println("Cannot locate file.");
                System.exit(-1);
            }
        } else {
            file = new Scanner(System.in);
        }

        // reading from input
        while (file.hasNextLine()) {
            String line = file.nextLine();
            System.out.println("Happy birthday, " + line);
        }
        file.close();
    }
} 