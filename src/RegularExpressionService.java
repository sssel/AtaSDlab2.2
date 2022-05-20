import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionService {
    public void Run() {
        String file = "D://University//2 course//2s//ASD//lab2.2//src//file.txt";
        ArrayList<String> text = ReadFile(file);
        String regular = "^([+]|[-])([5-9]+)([5-9]+|[A-G]+)([-])$";
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher;

        for (int i = 0; i < text.size(); i++) {
            matcher = pattern.matcher(text.get(i));
            System.out.print(text.get(i) + " ");
            if (matcher.find()) {
                System.out.println("true");
            }
            else {
                System.out.println("false");
            }
        }
    }

    private ArrayList <String> ReadFile(String file){
        ArrayList<String> array = new ArrayList<String>();
        try(FileInputStream fis = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader read = new BufferedReader(reader)){
            String line = read.readLine();
            while(line != null){
                array.add(line);
                line = read.readLine();
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return array;
    }
}