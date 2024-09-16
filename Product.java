import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.math.BigDecimal.*;

public  abstract class Product  implements Rateable<Product> {

    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;
    private BigDecimal discount;



    public Rating getRating(){
        return rating;
    }
    public abstract Product applyRating(Rating newRating);
    /*{
        return  new Product(id, name, price,newRating);
    }*/

    public BigDecimal order(Product p){
        BigDecimal discount = ZERO;
        discount = ((Food)p).getBestBefore().isEqual(LocalDate.now( )) ? TWO: price.multiply( valueOf( 0.23 ) );
        return  p.getPrice().subtract(discount);

    }

    public Product(int id, String name, BigDecimal price,Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", Rating = " + rating.getStars()+
                '}';
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public BigDecimal getDiscount(){

        return  BigDecimal.valueOf(20.00 );
    }


}
 class Food extends Product implements Comparable<Food>{


    private LocalDate bestBefore;
     public int compareTo(Food f){
         return super.getName().compareTo(f.getName());
     }
    public Rating getRating(){
        return super.getRating();
    }


    @Override
    public String toString() {
        return "Food{" +
                "bestBefore=" + bestBefore +
                "} " + super.toString();
    }

    public Food(int id, String name, BigDecimal price, LocalDate bestBefore, Rating rating) {
        super( id, name, price,rating );
        this.bestBefore = bestBefore;

    }
    public LocalDate getBestBefore(){
        return bestBefore;
    }

    @Override
    public BigDecimal order(Product p ){
        LocalTime now = LocalTime.now();
        BigDecimal discount = ZERO;
        discount = (now.isAfter( LocalTime.of(12,30) ) && now.isBefore( LocalTime.of(20,30))) ? valueOf( 3.232 ): discount;
        return  p.getPrice().subtract(discount);

    }
    @Override
    public Product applyRating(Rating newRating)
    {
        return  new Food(getId(), getName(), getPrice(), bestBefore, newRating );
    }



//    @Override
//    public BigDecimal order(Product p, BigDecimal price) {
//        return null;
//    }

}
class Drink extends Product implements Cloneable{
    private boolean isAlcohol;
    public Rating getRating(){
        return super.getRating();
    }

    @Override
    protected Drink clone() throws CloneNotSupportedException{
        return (Drink) super.clone();
    }
    @Override
    public Product applyRating(Rating newRating) {
        return new Drink(getId(),getName(),getPrice(),getIsAlcohol(),newRating);
    }

    public Drink(int id, String name, BigDecimal price, boolean isAlcohol,Rating rating) {
        super( id, name, price ,rating);

        this.isAlcohol = isAlcohol;
    }
    @Override
    public BigDecimal getDiscount(){

        return super.getDiscount();
    }
    public boolean getIsAlcohol(){
        return isAlcohol;
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Override
    public BigDecimal order(Product p){
        BigDecimal discount = ZERO;
        LocalTime now = LocalTime.now();
        if(isAlcohol) {
            discount = ((Food) p).getBestBefore().isEqual( LocalDate.now() ) ?   discount : getPrice().multiply( valueOf( 0.23 ) );
        }

        else{
            discount = (now.isAfter( LocalTime.of(12,30) ) && now.isBefore( LocalTime.of(20,30))) ? valueOf( 3.232 ): TWO ;

        }
        return p.getPrice().subtract( discount );

    }

    @Override
    public String toString() {
        return "Drink{" +
                "isAlcohol=" + isAlcohol +
                "} " + super.toString();
    }
}

