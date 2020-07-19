package technology.touchmars.javaq.trycatch;

import java.io.*;
import java.nio.file.Paths;

public class TestAutoCloseable {

    public static void main(String[] args) {
        try(InputStream in = new FileInputStream(new File(Paths.get("").toAbsolutePath().toString()+"/src/main/java/technology/touchmars/javaq/trycatch/input.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
        ) {
            while(br.ready())
                System.out.println (br.readLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
