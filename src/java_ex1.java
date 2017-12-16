/**
 * @author Shmuel Feld
 * @version 1
 */

import java.io.IOException;
import java.io.PrintWriter;

/**
 * main class
 */
public class java_ex1 {
    /**
     * main class for ex1
     * @param args arguments to program
     */
    public static void main(String[] args) throws IOException {
        NavigationProgram np = new NavigationProgram("./input.txt");
        np.openInputFile();
        PrintWriter writer = new PrintWriter("./output.txt", "UTF-8");
        writer.println(np.findPath());
        writer.flush();
        writer.close();
    }
}
