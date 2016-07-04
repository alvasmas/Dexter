/**
 * Created by Alex on 03.07.16.
 */
public class AsciiParser {
    public static void main(String[] args) throws java.io.IOException{
        int ch;
        while ((ch = System.in.read()) != 33) {
            System.out.printf("%s=%s\n", (char) ch, Integer.toString(ch));
        }
    }
}
