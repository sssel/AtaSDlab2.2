import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String lvl[] = new String[]{"lvl1","lvl2","lvl3"};
        System.out.println(lvl[0]);
        //1 level
        RegularExpressionService regularExpressionService = new RegularExpressionService();
        regularExpressionService.Run();
        System.out.println(lvl[1]);
        //2 level
        Scanner input = new Scanner (System.in);
        for (int i = 0; i < 6; i++) {
            System.out.println("Input string");
            Automat automat = new Automat(input.nextLine());
            System.out.println(automat.scanner());
        }
        System.out.println(lvl[2]);
        //3 level
        TransitTableService transitTableService = new TransitTableService();
        transitTableService.Run();
    }
}