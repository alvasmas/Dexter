import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alex on 03.07.16.
 */
public class DistinctGroups {
    public static void main(String[] args) throws java.io.IOException {
        ArrayList<String> lst;// = new ArrayList<>();
        int ch;
        String key = new String();
        String[][] array = new String[20][20], arrayFin = new String[20][20];

        array = ParseInArr();
        //arrayFin = Trans(array);
        arrayFin = GetUnique(array);
        PrintArrFormat(arrayFin);
    }

    public static String[][] ParseInArr() throws java.io.IOException {
        int ch, i = 0, j = 0, ind = 0;
        String[][] arr = new String[20][20];
        String word = new String();
        String fullLine = new String();

        Scanner sc = new Scanner(System.in);
        int kk=0;
        while (sc.hasNextLine() && kk<=20 ) {
            kk++;
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

    public static String[][] GetUnique(String[][] inArr) {
        String[][] outArr = new String[20][20];
        int k = 0;
        for (int j = 0; j < 20; j++) {
            if (inArr[0][j] == null)
                break;

            //if (inArr[0][j] != null) {
            boolean alreadyExist = false;
            for (int jj = 0; jj < k; jj++)
                if (outArr[0][jj].equals(inArr[0][j]) && outArr[1][jj].equals(inArr[1][j]))
                    alreadyExist = true;
            if (!alreadyExist) {
                outArr[0][k] = inArr[0][j];
                outArr[1][k++] = inArr[1][j];
            }
            // }
        }
        return outArr;
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

