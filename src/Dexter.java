import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Alex on 04.07.16.
 */
public class Dexter {
    public static void main(String[] args) throws java.io.IOException {
        String fn = "D:\\!Java\\Stepic\\Dexter\\in_graph.txt";
        //int ribNumber = getLineCount(fn);

        Graph graph = new Graph(fn);
        graph.InitVertexList();
        graph.Calc();
        System.out.println();
        //int[][] graphMap = new int[0][0];
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
    int[][] graphMap, vertexInitMap, vertexDexterMap;
    HashMap<Integer, Integer> vertexList = new HashMap<>(),
                              vertexDexter = new HashMap<>();

    public Graph() {

    }

    public Graph(int _ribCount, int _vertexCount, int _firstVertex, int _lastVertex, int[][] _graphMap) {
        ribCount = _ribCount;
        vertexCount = _vertexCount;
        firstVertex = _firstVertex;
        lastVertex = _lastVertex;
        graphMap = _graphMap;
    }

    protected void InitVertexList() {
        for (int i = 1; i <= this.vertexCount; i++)
            this.vertexList.put(i, 0);
        vertexDexter = (HashMap<Integer, Integer>)vertexList.clone();
    }

    public Graph(String fn) throws FileNotFoundException {
        this();
        int lineCount = Dexter.getLineCount(fn);
        graphMap = new int[lineCount - 2][3];
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
                graphMap[count - 1][0] = Integer.parseInt(st.nextToken());
                graphMap[count - 1][1] = Integer.parseInt(st.nextToken());
                graphMap[count - 1][2] = Integer.parseInt(st.nextToken());
            }
            count++;
        }
        sc.close();
    }

    public void Calc(){
        int minTemp=1000,
            startFrom = this.firstVertex;

        this.vertexList.remove(startFrom);
        while(!this.vertexList.isEmpty()) {

            int VertNum = 0;

            for(int k : this.vertexDexter.keySet()) {
                if(this.vertexList.containsKey(k))
                    continue;
                VertNum = k;
                // Went through full rib list and find min value
                for (int i = 0; i < this.ribCount; i++)
                    if (this.graphMap[i][0] == VertNum)
                        if (minTemp > this.graphMap[i][2])
                            minTemp = this.graphMap[i][2];
                // find such rib which has lowest value
                for (int i = 0; i < this.ribCount; i++)
                    if (this.graphMap[i][0] == VertNum)
                        // found min iterator
                        // means choisen vertex found
                        if (minTemp == this.graphMap[i][2]) {
                            this.vertexDexter.replace(this.graphMap[i][1], minTemp);
                            this.vertexList.remove(this.graphMap[i][1]);
                            minTemp = 1000;
                        }
            }
        }
    }
}