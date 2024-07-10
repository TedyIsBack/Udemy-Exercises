package FunctionInterfaces;

import javax.print.attribute.standard.RequestingUserName;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Main {
    static Random random = new Random();

    public static void main(String[] args) {

        String[] firstNames = {"Bob","Miele","Flora","Anna","Steven","Mackel"};
        Arrays.asList(firstNames).replaceAll(name -> name.toUpperCase());
        //Arrays.setAll(firstNames,i -> firstNames[i].toUpperCase());
        List<String> backedByArray = Arrays.asList(firstNames);
        backedByArray.replaceAll(name -> name += " " + getRandomChar('A','D') + ".");

        backedByArray.replaceAll(name -> name += " " + getReversedName(name.split(" ")[0]));
        //name.split(" ")[0] : от предишния ред сме получили например MIELE C.
        //разделяме name спрямо празните места и взимаме първата част
        //тоест взимаме MIELE и обърщаме името

        Arrays.asList(firstNames).replaceAll(name ->{
            StringBuilder builder = new StringBuilder(name);
            builder.reverse();
            name += " " + builder.toString().split(" ")[1];
            return name;
        });
        System.out.println(Arrays.toString(firstNames));
        List<String> names = new ArrayList<>(Arrays.asList(firstNames));

        names.forEach(n -> System.out.println(n));

        System.out.println("\nUsing removeIf with list (because if i use it with array it will throw and exception) ");
        System.out.println("\nRemoving the same first and last name ------- ");
        names.removeIf(name -> name.split(" ")[0].equals(lastName(name)));
        names.forEach(n -> System.out.println(n));


        //UnaryOperator / Function
        /*String list = "1234567890";
        //System.out.println(everySecondSchar(list));

        UnaryOperator<String> predicate =
                source -> {
                    StringBuilder returnVal = new StringBuilder();
                    for (int i = 0; i < source.length(); i++) {
                        if (i % 2 == 1) {
                            returnVal.append(source.charAt(i));
                        }
                    }
                    return returnVal.toString();
                };

        //System.out.println(predicate.apply("1234567890"));*/

    }

    public static String lastName(String name){

        String[] builder = name.split(" ");
        if(builder[0].equals(builder[2]))
            return builder[2];
        return null;
    }

    public static String getReversedName(String name){
        return new StringBuilder(name).reverse().toString();
    }
    public static  char getRandomChar(char start, char end){
        return (char) random.nextInt((int) start,(int) end);
    }



}

