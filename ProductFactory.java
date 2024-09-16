import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Predicate;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductFactory {
    //  private Product product;
//  private Review[] reviews = new Review[5];

    private Map<Product, List<Review>> products = new HashMap<>();
    private ResourceFormatter formatter;
    private ResourceBundle config = ResourceBundle.getBundle( "config" );
    private MessageFormat reviewFormat = new MessageFormat( config.getString( "review.data.format" ) );
    private MessageFormat productFormat = new MessageFormat( config.getString("product.data.format") );
    private static Map<String, ResourceFormatter> formatters = Map.of( "en-UK", new ResourceFormatter( Locale.ENGLISH ),
            "en-US", new ResourceFormatter( Locale.US ),
            "fr-FR", new ResourceFormatter( Locale.FRANCE ),
            "ca-Canada", new ResourceFormatter( Locale.CANADA ) );
    private static final Logger logger = Logger.getLogger(ProductFactory.class.getName());
    public ProductFactory(Locale locale) {
        this( locale.toLanguageTag() );
    }
   
    public ProductFactory(String languageTag) {
        changeLocale( languageTag );
    }

    public void changeLocale(String languageTag) {
        formatter = formatters.getOrDefault( languageTag, formatters.get( "en-Uk" ) );
    }

    public static Set<String> getSupportedLocals() {
        return formatters.keySet();
    }

    public Product reviewProduct(int id, Rating rating, String comments) {
        Product res = null;
        try {
            res = reviewProduct( findProduct( id ), rating, comments );
        } catch (ProductFacotyException e) {
            logger.log( Level.SEVERE,null,e );
        }
        return res;
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {
//        if(reviews[reviews.length - 1] != null){
//            reviews = Arrays.copyOf(reviews,reviews.length +5);
//        }
        List<Review> reviews =  products.get( product );
        products.remove( product, reviews );
        reviews.add( new Review( rating, comments ) );
        product = product.applyRating(
                Rateable.convert( (int) Math.round( reviews.stream().mapToInt( r -> r.getRating().ordinal() ).average().orElse( 0 ) ) ) );
       int sum =0;
//        for (Review review : reviews){
//            sum += review.getRating().ordinal();
//        }
        //       boolean reviewed = false ;
//        while(i< reviews.length && !reviewed){
//            if(reviews[i] ==null){
//                reviews[i] = new Review( rating,comments );
//                reviewed = true;
//            }
//            sum += reviews[i].getRating().ordinal();// converts into numarical values its an eenum method
//            i++;
//        }
        product = product.applyRating( Rateable.convert( Math.round( (float) sum / reviews.size() ) ) );
        products.put( product, reviews );
        return product;

    }

    public void printProducts(Predicate<Product> filter, Comparator<Product> sorted) {
//        List<Product> productList = new ArrayList<>( products.keySet() );
//        productList.sort( sorted );

        StringBuilder txt = new StringBuilder();
        products.keySet().stream().sorted(sorted).filter(filter).forEach(p -> txt.append( formatter.formatProduct( p ) ).append( '\n' ) );
        System.out.println(txt);
    }

    public void printProductReport(int id) {
        try {
            printProductReport( findProduct( id ) );
        } catch (ProductFacotyException e) {
            logger.log( Level.INFO,e.getMessage() );

        }
    }

    public void printProductReport(Product product) {
        List<Review> reviews = products.get( product );
        StringBuilder txt = new StringBuilder();

        txt.append( formatter.formatProduct( product ) );
        txt.append( '\n' );
        Collections.sort( reviews );
        if(reviews.isEmpty()){
            txt.append( formatter.getText( "no.reviews" ) );
        } else{
            txt.append(reviews.stream().map( r -> formatter.formatReview(  r ) + '\n')
                    .collect( Collectors.joining()) );
        }
//        for (Review review : reviews) {
//            if (review == null) {
//                break;
//            }
//
//            txt.append( formatter.formatReview( review ) );
//            txt.append( "\n" );
//        }
//        if (reviews.isEmpty()) {
//            txt.append( formatter.getText( "no.reviews" ) );
//            txt.append( "\n" );
//        }
//        System.out.println( txt );
    }

    public void parseReview(String text) throws ParseException {
        try {
            Object[] values = reviewFormat.parse( text );
            reviewProduct( Integer.parseInt( (String)values[0] ),Rateable.convert(Integer.parseInt( (String) values[0] )), (String)values[2] );
        }catch(ParseException e){
            logger.log(Level.WARNING,"Error parsing review "+ text,e);
        }
    }
    public void  parseProduct(String text){
        try {
            Object[] values = productFormat.parse( text );
            int id = Integer.parseInt( (String)values[1] );
            String name = (String)values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String)values[3]));
            Rating rating = Rateable.convert(Integer.parseInt((String) values[4]));
            boolean isAlcohol = Boolean.parseBoolean((String) values[5]);
            switch ((String) values[0]) {
                case "D":
                    createProduct( id, name, price, isAlcohol, rating );
                    break;

                case "F":
                    LocalDate bestBefore = LocalDate.parse( (String) values[5] );
                    createProduct( id, name, price, rating, bestBefore );
            }

        }
        catch(ParseException | NumberFormatException | DateTimeParseException e){
            logger.log(Level.SEVERE,"ERROR parsing product" + text+ " "+e.getMessage());
        }
    }
    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
//        switch(productType){
//            case "Food":
//                product = new Food( 32,"cake", BigDecimal.valueOf( 1200 ), LocalDate.of(2024,5,21),Rating.NOT_RATED );
//                return product ;
//            case "Drink":
//                 product = new Drink( 44,"margreta",BigDecimal.valueOf( 1200 ),false ,Rating.THREE_STAR);
//                 return product;
//            default:
//                throw new IllegalArgumentException("Unknown product type: " + productType);
//
//
////        }

        Product product = new Food( id, name, price, bestBefore, rating );

        products.putIfAbsent( product, new ArrayList<>() );
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, boolean isAlcohol, Rating rating) {
        Product product = new Drink( id, name, price, isAlcohol, rating );
        products.putIfAbsent( product, new ArrayList<>() );
        return product;
    }

    public Product findProduct(int id) throws ProductFacotyException{
//        Product result = null;
//        for(Product product : products.keySet()){
//            if(product.getId() == id){
//                result = product;
//                break;
//            }
//        }
//        return result;
        return (Product) products.keySet().stream()
                .filter( p -> p.getId() == id )
                .findFirst().orElseThrow( () -> new ProductFacotyException("Product with id " +id +"not found"));
    }

    private static class ResourceFormatter {
        private Locale locale;
        private ResourceBundle resourceBundle;

        private NumberFormat moneyFormat;

        private ResourceFormatter(Locale locale) {
            this.locale = locale;
            resourceBundle = ResourceBundle.getBundle( "resources", locale );

            moneyFormat = NumberFormat.getCurrencyInstance( locale );
        }

        private String formatProduct(Product product) {
            return MessageFormat.format(
                    resourceBundle.getString( "product" )
                    , product.getName()
                    , moneyFormat.format( product.getPrice() )
                    , product.getRating().getStars() );
        }

        private String formatReview(Review review) {
            return MessageFormat.format( resourceBundle.getString( "review" ),
                    review.getRating().getStars(),
                    review.getComments() );
        }

        private String getText(String key) {
            return resourceBundle.getString( key );
        }
    }


}

