import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Alex on 04.07.16.
 */
public class Dexter {
    public static void main(String[] args) throws java.io.IOException {
        String fn = "/Users/Alex/Desktop/Java/Stepic1/in_graph.txt";
        //int ribNumber = getLineCount(fn);

        Graph graph = new Graph(fn);
        int[][] graphMap = new int[0][0];
    }

    public static int getLineCount(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        int count = 0;
        while (sc.hasNext() && sc.nextLine() != null)
            count++;
        sc.close();
        return count;
    }

}

class Graph {
    protected int ribCount = 0, vertexCount = 0, firstVertex = 0, lastVertex = 0;
    int[][] graphMap;

    public Graph() {

    }

    public Graph(int _ribCount, int _vertexCount, int _firstVertex, int _lastVertex, int[][] _graphMap) {
        ribCount = _ribCount;
        vertexCount = _vertexCount;
        firstVertex = _firstVertex;
        lastVertex = _lastVertex;
        graphMap = _graphMap;
    }

    public Graph(String fn) throws FileNotFoundException {
        int lineCount = Dexter.getLineCount(fn);
        graphMap = new int[lineCount-2][3];
        Scanner sc = new Scanner(new File(fn));
        int count = 0;
        while (sc.hasNext()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");

            if (count == 0) {
                vertexCount = Integer.parseInt(st.nextToken());
                ribCount = Integer.parseInt(st.nextToken());
            } else if (count >= lineCount - 1) {
                firstVertex = Integer.parseInt(st.nextToken());
                lastVertex = Integer.parseInt(st.nextToken());
            } else {
                graphMap[count-1][0] = Integer.parseInt(st.nextToken());
                graphMap[count-1][1] = Integer.parseInt(st.nextToken());
                graphMap[count-1][2] = Integer.parseInt(st.nextToken());
            }
            count++;
        }
        sc.close();
    }
}