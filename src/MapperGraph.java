import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Alex on 10.07.16.
 */
public class MapperGraph {
    public static void main(String[] args) throws FileNotFoundException {
        String fn = "/Users/Alex/Desktop/Java/Stepic1/in_mapperGraph";
        HashMap<Integer, VertexRelation> fullList;

        fullList = InitHash(fn);

        for(Map.Entry<Integer, VertexRelation> entry: fullList.entrySet())
        {
            System.out.printf("%d\t%s\t{%s}\n", entry.getKey(), entry.getValue().InRank, entry.getValue().getVertexString());

            for(Map.Entry<Integer, Integer> enMap: entry.getValue().outVertexes.entrySet())
             if(!entry.getValue().InRank.equals("INF"))
                System.out.printf("%d\t%d\t{}\n",enMap.getValue(), Integer.parseInt(entry.getValue().InRank)+1);
            else
                 System.out.printf("%d\t%s\t{}\n",enMap.getValue(), entry.getValue().InRank);

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
            vr.InRank = st.nextToken();
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
            vr.InRank = st.nextToken();
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
    String InRank = "";
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

