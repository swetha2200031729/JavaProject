import java.io.Console;
import java.io.PrintWriter;

public class Echo {

    public static void main(String[] args) {
        Console c = System.console();
        if(c == null){
            System.out.println("Console is not supported");
            return;

        }
        PrintWriter out= c.writer();
        out.println("To quite type : exit");
        out.println("Type values and press enter :");
        String t = null;
        while(!(t = c.readLine()).equals("exit")){
            out.println("Echo :"+t);
        }
    }

}
