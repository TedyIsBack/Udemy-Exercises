package LmabdaIntro;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        //COMPARATOR
     /*   List<Person> people = new ArrayList<>(List.of(
                new Main.Person("Lucy","Van Pelt"),
                new Person("Sally","Brown"),
                new Person("Linus","Van Pelt"),
                new Person("Peppermint", "Patty"),
                new Person("Charlie","Brown")
        ));

        //Using annonymous class to compare
        var comparatorLastName = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName.compareTo(o2.lastName);
            }
        };

        //Using lambda to compare
        people.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
        System.out.println(people);

        interface EnhancedComparator<T> extends Comparator<T>{
            int secondLevel(T o1,T o2);
        }

        var comparatorMix = new EnhancedComparator<Person>(){

            public int compare(Person o1, Person o2) {
                int result = o1.lastName.compareTo(o2.lastName);
                return result == 0 ? secondLevel(o1,o2) : result;
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName.compareTo(o2.firstName);
            }
        };

        //EnhancedComparator is not functional interface so it can be used for lambda
        people.sort(comparatorMix);
        System.out.println(people);
    }*/


        List<String> list = new ArrayList<>(List.of(
                "alpha","bravo","charlie","delta"
        ));

        for(String s : list){
            System.out.println(s);
        }
        list.removeIf(s -> s.equals("bravo")); // predicate usage
        System.out.println("-".repeat(10));
        list.forEach((s) -> System.out.println(s)); // consumer
        System.out.println("-".repeat(10));
        list.addAll(List.of("echo","easy","earnest"));
        list.removeIf(s -> s.startsWith("ea"));
        System.out.println("RemoveIf ");
        list.forEach((s) -> System.out.println(s)); // consumer



        System.out.println();
        String prefix = "nato";
        list.forEach((var s) -> {
            char first = s.charAt(0);
            System.out.println(prefix + " " + s + " means " + first);
        });

        System.out.println("\nCalculator" + "-".repeat(5));
        var result = calculator((a, b) -> a+b,5,2);
        int result1 = calculator((a,b) -> {return a + b + 1;},5,2);

        var result2 = calculator((a,b) -> a/b,10.0,2.5);
        var result3 = calculator(
                (a,b) -> a.toUpperCase() + " "+ b.toUpperCase(),
                "Ralph","Kramden");

        var coords = Arrays.asList(
                new double[] {47.2160, -95.2348},
                new double[] {29.1566, -89.2495},
                new double[] {35.1556, -90.0659}
        );

        coords.forEach(s -> System.out.println(Arrays.toString(s)));
        BiConsumer<Double,Double> p1 = (lat,lng) ->
                System.out.printf("[lat:%.2f lon:%.3f]%n",lat,lng);

        var firstPoint = coords.get(0);
        processPoint(firstPoint[0],firstPoint[1],p1);

        System.out.println("-".repeat(10));
        coords.forEach(s-> processPoint(s[0],s[1],p1));

        list.replaceAll(s -> s.charAt(0) + " - " + s.toUpperCase());
        list.forEach(s -> System.out.println(s));

        String[] emptyStrings = new String[10];
        System.out.println(Arrays.toString(emptyStrings));
        Arrays.fill(emptyStrings,"");
        System.out.println(Arrays.toString(emptyStrings));
        Arrays.setAll(emptyStrings,i -> (i+1)+". ");//IntFunction
        System.out.println(Arrays.toString(emptyStrings));

        String[] names = {"Ann","Bob","Carol","David","Ed","Fred"};
        String[] randomList = randomlySelectedValues(15,names,
                () -> new Random().nextInt(0,names.length));
        System.out.println(Arrays.toString(randomList));

    }


    public static <T> T calculator(BinaryOperator<T> function, T value1, T value2){

        T reuslt = function.apply(value1,value2);
        System.out.println("Result of operation: " + reuslt);
        return reuslt;
    }

    public  static <T> void processPoint(T t1, T t2, BiConsumer<T,T> consumer){
        consumer.accept(t1,t2);
    }

    public static String[] randomlySelectedValues(int count, String[] values, Supplier<Integer> s){
//На произволен принцип избира стойности от values,
// които да върне като нов масив от string с дължина count
// като, за да ги зададе, използва Supplier

     String[] selectedValues = new String[count];
     for(int i =0; i < count;i++){
         selectedValues[i] = values[s.get()];
     }
     return selectedValues;
    }
}
