import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TransitTableService {
    ArrayList<String> data;

    public  TransitTableService() {
        data = readFileWithSplittingTextInWords("D://University//2 course//2s//ASD//lab2.2//src//file2.txt");
    }

    public  void Run() {
        for (int i = 0; i < data.size(); i++) {
            TransitTable transitTable = new TransitTable(data.get(i));
            System.out.println(data.get(i) + " " + transitTable.scanner());
        }
    }

    private ArrayList<String> readFileWithSplittingTextInWords(String nameFile)
    {
        ArrayList<String> list = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(nameFile));)
        {
            String s;
            while ((s = bufferedReader.readLine())!= null){
                list.add(s);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        String pattern = "(\\!\\?|\\?\\!)";
        ArrayList <String> words = new ArrayList<>();
        for (String line: list)
        {
            String[] splitResult;
            splitResult = line.split(pattern);
            for (String word: splitResult)
            {
                words.add(word);
            }
        }
        return words;
    }
}