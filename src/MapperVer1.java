import java.util.LinkedHashMap;

/**
 * Created by Alex on 29.06.16.
 */
public class MapperVer1 {
    public static void main(String[] args) throws java.io.IOException {
        int ch;
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(16, 0.75f, false);
        LinkedHashMap<String, Integer> mapCount = new LinkedHashMap<>(16, 0.75f, false);
        LinkedHashMap<String, Integer> mapNum = new LinkedHashMap<>(16, 0.75f, false);


        String word = new String();
        String key = new String();
        String value = new String();
        String num = new String();

        while ((ch = System.in.read()) != 33) {
            if ((char) ch != ' ' && ch != 10 && ch != 9 && ch != ';')
                word = word + (char) ch;
            else {
                if (!isNumeric(word))
                    key = word;
                else if (value.isEmpty())
                    value = word;
                else
                    num = word;
                word = new String();
                if (!num.isEmpty()) {
                    MapPut(map, key, Integer.parseInt(value));
                    MapPut(mapCount, key, 1);
                    MapPut(mapNum, key, Integer.parseInt(num));
                    key = new String();
                    value = new String();
                    num = new String();
                }
            }
            //System.out.println(map);
        }
       // ReduceAVG(map, mapCount);
        map.forEach((k, v) -> System.out.printf("%s\t%s;%s\n", k, v, mapNum.get(k)));
    }

    private static void MapPut(LinkedHashMap Map, String Key, Integer Value) {
        Integer val = (Integer) Map.get(Key);
        if (val == null)
            Map.put(Key, Value);
        else
            Map.replace(Key, new Integer(val + (int) Value));
    }

    private static void ReduceAVG(LinkedHashMap MapVal, LinkedHashMap MapCnt) {
        MapVal.replaceAll((k, v) -> v = (int) v / ((int) MapCnt.get(k)));
    }

    public static boolean isNumeric(String inStr) {

        try {
            int i = Integer.parseInt(inStr);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
