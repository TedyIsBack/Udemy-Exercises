package ConvenienceMethods;

import java.util.*;
import java.util.function.*;


class PersonEh {
    private String firstName;
    private String lastName;

    public PersonEh(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
public class Main {
    public static void main(String[] args) {

        //chaining lambdas
        String name = "Tim";
        Function<String, String> uCase = String::toUpperCase;
        System.out.println(uCase.apply(name));//TIM

        Function<String, String> lastNames = s -> s.concat(" Buncha");

        Function<String, String> uCaseLastName = uCase.andThen(lastNames);
        //andThen & compose are conveniences methods

        System.out.println("\nandThen" + "-".repeat(10));
        System.out.println(uCaseLastName.apply(name));//TIM Buncha


        uCaseLastName = uCase.compose(lastNames);
        System.out.println("\nCompose" + "-".repeat(10));
        System.out.println(uCaseLastName.apply(name));


        System.out.println("\nf0" + "-".repeat(10));
        //методите не е нужно да връщат един и същ резултат.
        //Тук само s-> split.(" ") ще върне масив ,а другите връщат string
        Function<String, String[]> f0 = uCase //take string and return string
                .andThen(s -> s.concat(" Buchaka"))
                //take a string (from previous method reference) and return string
                .andThen(s -> s.split(" "));//take a string and return String array
        System.out.println(Arrays.toString(f0.apply(name)));

        System.out.println("\nf1" + "-".repeat(10));
        Function<String, String> f1 = uCase //TIM
                .andThen(s -> s.concat(" Buchaka"))//TIM Buchaka
                .andThen(s -> s.split(" ")) // String[] = "TIM" , "Buchaka"
                .andThen(s -> s[1].toUpperCase() + ", " + s[0]); //BUCHAKA, TIM
        System.out.println(f1.apply(name));

        System.out.println("\nf2" + "-".repeat(10));
        Function<String, Integer> f2 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "))
                .andThen(s-> String.join(", ",s))
                .andThen(String::length);
        System.out.println(f2.apply(name)); //TIM, Buchalka

        String[] names = {"Ann","Bob","Carol"};
        Consumer<String> s0 = s-> System.out.println(s.charAt(0));
        Consumer<String> s1 = System.out::println;
        Arrays.asList(names).forEach(s0
                .andThen(s-> System.out.println(" - "))
                .andThen(s1));

        Predicate<String> p1 = s-> s.equals("TIM");
        Predicate<String> p2 = s-> s.equalsIgnoreCase("Tim");
        Predicate<String> p3 = s-> s.startsWith("T");
        Predicate<String> p4 = s-> s.endsWith("e");

        Predicate<String> combine1 = p1.or(p2);
        System.out.println("\ncombie1: " + combine1.test(name));

        Predicate<String> combine2 = p3.and(p4);
        System.out.println("combie2: " + combine2.test(name));

        Predicate<String> combine3 = p3.and(p4).negate();//negate return the opposite
        System.out.println("combie3: " + combine3.test(name));


        //record  Person(String firstName, String lastName){}
        List<PersonEh> list = new ArrayList<>(List.of(//can work with inner record Person
                new PersonEh("Peter", "Pan"),
                new PersonEh("Peter", "PumkinEater"),
                new PersonEh("Minnie", "Mouse"),
                new PersonEh("Mickey", "Mouse")
        ));
        list.sort((o1,o2) -> o1.getLastName().compareTo(o2.getLastName()));
        list.forEach(System.out::println);

        System.out.println("-".repeat(10));
        list.sort(Comparator.comparing(PersonEh::getLastName));
        list.forEach(System.out::println);

        System.out.println("-".repeat(10));
        list.sort(Comparator.comparing(PersonEh::getLastName)
                .thenComparing(PersonEh::getFirstName));
        list.forEach(System.out::println);

        System.out.println("-".repeat(10));
        list.sort(Comparator.comparing(PersonEh::getLastName)
                .thenComparing(PersonEh::getFirstName)
                .reversed());
        list.forEach(System.out::println);
     }
}
