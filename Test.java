import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;
class Player {
    public String name;
    public int score;
    public Player (String name, int score) {
        this.name = name;
        this.score = score;
    }
}
public class Test{

//    public static void main(String[] args) throws InterruptedException {
//        var c = new CopyOnWriteArrayList<>( List.of("1","2","3","4"));
//        Runnable r = () -> {
//            try {
//                Thread.sleep(150);
//            }
//            catch(InterruptedException e){
//                System.out.println(e);
//            }
//            c.set(3,"four");
//            System.out.print(c + " ");
//        };
//        Thread t = new Thread(r);
//        t.start();
//        for(var s:c){
//            System.out.print(s+" ");
//            Thread.sleep(100);
//        }
//
//
//       public static void main(String[] args) {
//           String[] towns = {"boston","paris","bangkok","oman"};
//           Comparator ms = ( a,  b) -> a.compareTo( b );
//          Arrays.sort(towns,ms);
//          System.out.println(Arrays.binarySearch(towns, "oman", ms));
//       }

    public static void main (String[] args){

        Locale locale = new Locale("en", "US");
        LocalDate today = LocalDate.of(2018, 12, 17);
        Object FormalStyle = null;
        String mToday=
            today.format( DateTimeFormatter.ofLocalizedDate(FormalStyle.MEDIUM). withLocale(locale));
        String sToday =
            today.format(DateTimeFormatter.ofLocalizedDate( FormatStyle.SHORT).
                withLocale(locale));
        System.out.println(mToday);
        System.out.println(sToday);

    }

    }


