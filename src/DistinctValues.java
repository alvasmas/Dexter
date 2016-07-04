import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alex on 03.07.16.
 */
public class DistinctValues {
    public static void main(String[] args) throws java.io.IOException {
        ArrayList<String> lst;// = new ArrayList<>();
        int ch;
        String key = new String();
        String[][] array = new String[20][20], arrayFin = new String[20][20];

        array = ParseInArr();
        arrayFin = Trans(array);
        PrintArrFormat(arrayFin);
    }

    public static String[][] ParseInArr() throws java.io.IOException {
        int ch, i = 0, j = 0, ind = 0;
        String[][] arr = new String[20][20];
        String word = new String();
        String fullLine = new String();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {

            fullLine = sc.nextLine();

            while (fullLine.length() > ind) {
                ch = fullLine.charAt(ind);
                if ((char) ch != ' ' && ch != 10 && ch != 9 && ch != ';' && ch != ',') {
                    word = word + (char) ch;
                    if (fullLine.length() - 1 == ind) {
                        arr[i++][j] = word;
                        word = new String();
                    }
                } else {
                    arr[i++][j] = word;
                    word = new String();
                }
                ind++;
            }
            j++;
            i = 0;
            ind = 0;
        }
        return arr;
    }

    public static String[][] Trans(String[][] inArr) {
        String[][] outArr = new String[20][20];
        int k = 0;
        for (int j = 0; j < 20; j++)
            for (int i = 1; i < 20; i++) {
                if (inArr[0][j] == null)
                    break;

                if (inArr[i][j] != null) {
                    outArr[0][k] = inArr[0][j];
                    outArr[1][k++] = inArr[i][j];
                }
            }
        return outArr;
    }

    public static ArrayList<String> ParseInLst() throws java.io.IOException {
        int ch;
        ArrayList<String> lst = new ArrayList<>();
        String line = new String();

        while ((ch = System.in.read()) != 33) {
            line = line + (char) ch;
            if (ch == 10) {
                lst.add(line);
                line = new String();
            }
        }
        return lst;
    }

    public static void PrintArr(String[][] inStr) {
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++)
                if (inStr[i][j] != null)
                    System.out.print(inStr[i][j]);
            if (inStr[0][j] != null)
                System.out.println();
        }
    }

    public static void PrintArrFormat(String[][] inStr) {
        for (int j = 0; j < 20; j++)
            if (inStr[0][j] != null) {
                System.out.println(inStr[0][j] + "," + inStr[1][j] + "\t1");
            }
    }

}

