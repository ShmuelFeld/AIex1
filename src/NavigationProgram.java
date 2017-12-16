
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * manage the program's flow
 */
public class NavigationProgram {
    private Algorithm theAlgorithm;
    private String path;
    private FileReader file;
    private BufferedReader reader;
    private List<String> list;
    private Matrix matrix;
    private int size;

    /**
     * ctor
     * @param pathToinputFile is the path to the input file.
     */
    NavigationProgram(String pathToinputFile) {
        this.path = pathToinputFile;
    }

    /**
     * try to open the file.
     * @return true if succeed otherwise, false.
     */
    public boolean openInputFile() {
        try {
            this.file = new FileReader(this.path);
        }catch (Exception e) {
            System.out.println("file not found");
            return false;
        }
        return true;
    }

    /**
     * read from input file.
     * @throws IOException if read is failed.
     */
    private void readFromFile() throws IOException {
        this.list = new ArrayList<String>();
        this.reader = new BufferedReader(this.file);
        String st = this.reader.readLine();
        while (st != null) {
            this.list.add(st);
            st = this.reader.readLine();
        }
    }

    /**
     * reads the content from input file, and returns the path for a given maze.
     * @return the formatted path.
     */
    public String findPath() {
        try {
            this.readFromFile();
        } catch (Exception e) {
            System.out.println("file not found");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < this.list.size(); i++) {
            sb.append(this.list.get(i));
        }
        this.algorithmChooser(this.list.get(0));
        this.size = Integer.parseInt(this.list.get(1));
        this.matrix = new Matrix(sb.toString(), this.size);
        return this.theAlgorithm.findPath(this.matrix.getRoot(), this.matrix.getGoal(), this.matrix);
    }

    /**
     * check which algorithm is wanted and creates an instance.
     * @param algoKind the line in input file that contains the info about the wanted algorithm.
     */
    private void algorithmChooser(String algoKind) {
        if (algoKind.contentEquals("IDS")) {
            this.theAlgorithm = new IDSAlgorithm();
        } else {
            this.theAlgorithm = new AStarAlgorithm();
        }
    }
}
