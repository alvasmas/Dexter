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
        //String fn = "D:\\!Java\\Stepic\\Dexter\\in_graph.txt";
        //String fn = "/Users/Alex/Desktop/Java/Stepic1/in_graphOriginal.txt";
        String fn = "/Users/Alex/Desktop/Java/Stepic1/in_graph2.txt";

        //int ribNumber = getLineCount(fn);

        Graph graph = new Graph();
        graph.InitVertexList();
        if(graph.Calc() == 0)
          System.out.println(graph.vertexDexter.get(graph.lastVertex));
        else
            System.out.println("-1");
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
        int lineCount = 10;
        //graphMap = new int[lineCount - 2][3];
        Scanner sc = new Scanner(System.in);
        int count = 0;
        while (sc.hasNextLine() && count < lineCount) {
            StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");

            if (count == 0) {
                vertexCount = Integer.parseInt(st.nextToken());
                ribCount = Integer.parseInt(st.nextToken());
                graphMap = new int[ribCount][3];
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

    public int Calc(){
        if(!checkIfHasEnd() || !checkIfHasStart())
            return -1;
        int minTemp=1000,
            startFrom = this.firstVertex,
            vertexFrom = 0,
            vertexFromWight = 0,
                count=0;

        this.vertexList.remove(startFrom);
        while(!this.vertexList.isEmpty()) {

            count++;
            int VertNum = 0;

            for(int k : this.vertexDexter.keySet()) {
                if(this.vertexList.containsKey(k) || this.vertexList.isEmpty())
                    continue;
                VertNum = -1;
                // Went through full rib list and find min value
                for (int i = 0; i < this.ribCount; i++)
                    //if (this.graphMap[i][0] == VertNum)
                    //check though all vertex which in main hashmap
                      if (!this.vertexList.containsKey(this.graphMap[i][0]))
                        if (minTemp > this.graphMap[i][2]) {
                            minTemp = this.graphMap[i][2];
                            VertNum = this.graphMap[i][0];
                        }
                // find such rib which has lowest value
                if(VertNum == -1) return VertNum;

                for (int i = 0; i < this.ribCount; i++)
                    if (this.graphMap[i][0] == VertNum)
                        // found min iterator
                        // means chosen vertex found
                        if (minTemp == this.graphMap[i][2]) {
                            vertexFrom = this.graphMap[i][0];
                            vertexFromWight = this.vertexDexter.get(vertexFrom);
                            this.vertexDexter.replace(this.graphMap[i][1], minTemp + vertexFromWight);
                            this.vertexList.remove(this.graphMap[i][1]);

                            minTemp = 1000;
                            vertexFromWight = 0;
                            this.graphMap[i][2]=1000;
                        }
            }
        }
        return 0;
    }

    public boolean checkIfHasEnd()
    {
        boolean result=false;
        for(int i=0;i<this.ribCount;i++)
            if(this.graphMap[i][1] == this.lastVertex)
                result = true;
        return result;
    }

    public boolean checkIfHasStart()
    {
        boolean result=false;
        //System.out.println(this.graphMap.);
        for(int i=0;i<this.ribCount;i++)
            if(this.graphMap[i][0] == this.firstVertex)
                result = true;
        return result;
    }

}