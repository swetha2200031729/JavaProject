import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Locale;

import static java.util.function.Predicate.isEqual;

public class Main {
//    public static BigDecimal order(Product p) {
//        BigDecimal price = p.getPrice();
//        BigDecimal discount = BigDecimal.ZERO;
//        if(p instanceof Food ){
//            discount = ((Food)p).getBestBefore().isEqual(LocalDate.now( )) ? BigDecimal.TWO: price.multiply( BigDecimal.valueOf( 0.23 ) );
//        }
//        else if(p instanceof Drink){
//            LocalTime now = LocalTime.now();
//            if(((Drink) p).getIsAlcohol()){
//                discount = price.divide(BigDecimal.valueOf( 0.5 ));
//
//            }
//            else{
//                discount = (now.isAfter( LocalTime.of(12,30) ) && now.isBefore( LocalTime.of(20,30))) ? BigDecimal.valueOf( 3.232 ):BigDecimal.TWO ;
//
//            }
//        }
//        return  p.getPrice().subtract(discount);
//    }
    public static BigDecimal  order(Product p){
        BigDecimal price =  p.getPrice();
        return price;
    }
    public static void main(String[] args) throws CloneNotSupportedException, ParseException {
//        Product x1= new Food(1,"Pizza", BigDecimal.valueOf(320.23), LocalDate.of(2043, 8, 31),Rating.TWO_STAR );
//        Product x2 = new Drink(2,"Butter Milk",BigDecimal.valueOf( 12 ),false,Rating.THREE_STAR);
//        Product p1 = x1.applyRating( Rating.ONE_STAR );
//        Product v3 = new Drink(3,"Beer",BigDecimal.valueOf( 129 ),false,Rating.NOT_RATED);
//        Product p2 =  v3.applyRating(Rating.ONE_STAR);

        ProductFactory pf = new ProductFactory( "em-UK");
       // pf.parseReview( "10,4,Nice hot cup of tea" );

        pf.parseProduct( "D,1093,Coffee,2.44,3" );
        pf.parseReview( "1093,4,Nice Hot cup of coffee" );
        pf.parseReview( "1093,4,good coffee" );
        pf.parseReview( "1093,4,Nice coffee" );
        pf.printProductReport(1093  );

   //     Product cake = pf.createProduct( 1022,"rice-rasam",BigDecimal.valueOf( 33 ),Rating.FIVE_STAR,LocalDate.of(2025,3,21) );
 //       Product pizza = pf.createProduct( 101, "Pizza",BigDecimal.valueOf( 340 ),Rating.THREE_STAR,LocalDate.of( 2025,6,30 ));
//        Drink d2 = ((Drink) v3).clone();
//
//        System.out.println(x1);
//        System.out.println("total price after discount: "+order(x2));
//        System.out.println(v3);
//        System.out.println("total price after discount: "+order(v3));
//        System.out.println(p1);
//        System.out.println(p2);
      //  System.out.println(cake);
      //  System.out.println("/n");
//        //      pf.printProductReport(cake);
//       cake =  pf.reviewProduct( cake,Rating.FIVE_STAR,"Oh myy GODDD , it is the delicious cake i have ever ate!!!!!!" );
//        cake =  pf.reviewProduct( cake,Rating.FOUR_STAR,"O delicious cake i have ever ate!!!!!!" );
//        cake =  pf.reviewProduct( cake,Rating.ONE_STAR,"Oh myy GODDD !!!!!!" );
//      //  pizza =  pf.reviewProduct( cake,Rating.FOUR_STAR,"Oh myy GOD , it is the delicious pizza i have ever ate!!!!!!" );
    //    pizza =  pf.reviewProduct( cake,Rating.TWO_STAR," Delicious pizza i have ever had!!!!!!" );
        //pizza =  pf.reviewProduct( cake,Rating.THREE_STAR,"good pizza !!!!!!" );
//
//           pf.printProductReport(cake);
//
//       //    pf.changeLocale( "en-US" );
//
////        System.out.println(d2);
////        System.out.println(v3);
//        pf.printProducts(p ->p.getPrice().floatValue() <2, (p1,p2) -> p2.getRating().ordinal() - p1.getRating().ordinal());

   //   Comparator<Product> ratingSorter = (p1,p2) -> p1.getRating().ordinal() - p2.getRating().ordinal();
     // Comparator<Product> priceSorter = (p1,p2) -> p1.getPrice().compareTo( p2.getPrice() );
       // pf.printProducts(ratingSorter);
        //pf.printProducts(  priceSorter );



    }
}
