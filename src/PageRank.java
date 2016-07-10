import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Alex on 10.07.16.
 */
public class PageRank {
    public static void main(String[] args) throws FileNotFoundException {
        String fn = "/Users/Alex/Desktop/Java/Stepic1/in_pageRank.txt";
        HashMap<Integer, VertexRelation> fullList;

        fullList = InitHash();

        for(Map.Entry<Integer, VertexRelation> entry: fullList.entrySet())
        {
            System.out.printf("%d\t%.3f\t{%s}\n", entry.getKey(), entry.getValue().InRank, entry.getValue().getVertexString());

            for(Map.Entry<Integer, Integer> enMap: entry.getValue().outVertexes.entrySet())
                System.out.printf("%d\t%.3f\t{}\n",enMap.getValue(), entry.getValue().InRank / entry.getValue().outVertexes.size());
        }
    }

    public static HashMap<Integer, VertexRelation> InitHash(String fn) throws FileNotFoundException {
        HashMap<Integer, VertexRelation> fullList = new HashMap<>();
        Scanner sc = new Scanner(new File(fn));
        int count = 0;
        while (sc.hasNext()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine(), "\t");
            Integer key = Integer.parseInt(st.nextToken());
            VertexRelation vr = new VertexRelation();
            vr.InRank = Double.parseDouble(st.nextToken());
            fullList.put(key, vr);

            String listOfVertexes = st.nextToken().replace("{", "").replace("}","");
            StringTokenizer stList = new StringTokenizer(listOfVertexes, ",");
            int i=0;
            while(stList.hasMoreTokens())
            {
                vr.outVertexes.put(i, Integer.parseInt(stList.nextToken()));
                i++;
            }
            count++;
        }
        sc.close();
        return fullList;
    }

    public static HashMap<Integer, VertexRelation> InitHash() {
        HashMap<Integer, VertexRelation> fullList = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int count = 0;
        while (sc.hasNext() && count < 10) {
            StringTokenizer st = new StringTokenizer(sc.nextLine(), "\t");
            Integer key = Integer.parseInt(st.nextToken());
            VertexRelation vr = new VertexRelation();
            vr.InRank = Double.parseDouble(st.nextToken());
            fullList.put(key, vr);

            String listOfVertexes = st.nextToken().replace("{", "").replace("}","");
            StringTokenizer stList = new StringTokenizer(listOfVertexes, ",");
            int i=0;
            while(stList.hasMoreTokens())
            {
                vr.outVertexes.put(i, Integer.parseInt(stList.nextToken()));
                i++;
            }
            count++;
        }
        sc.close();
        return fullList;
    }
}

class VertexRelation{
    double InRank =0.0;
    HashMap<Integer, Integer> outVertexes = new HashMap<>();

    VertexRelation() {

    }

    public String getVertexString(){
        String out ="";

        for(Map.Entry<Integer, Integer> entry: outVertexes.entrySet())
        {
            if(out.isEmpty())
                out = Integer.toString(entry.getValue());
            else
                out = out +","+Integer.toString(entry.getValue());
        }
        return out;
    }
}
