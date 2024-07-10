package MethodReferences;

import javax.swing.*;
import javax.swing.plaf.SeparatorUI;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class PlainOld{

    private static int last_id =1;
    private int id;
    public PlainOld() {
        id = PlainOld.last_id++;
        System.out.println("Creating a PlainOld Object: id = " +  id);
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of(
                "Anna", "Bob", "Chuck", "Dave"
        ));
        list.forEach(System.out::println);//method reference

        calculator(Integer::sum,5,2);
        Supplier<PlainOld> reference1 = PlainOld::new;
        PlainOld newPojo =  reference1.get();
        System.out.println("Getting array");
        PlainOld[] pojo1 =  seedArray(PlainOld::new,10);

        UnaryOperator<String> u1 = String::toUpperCase;
        calculator(String::concat,"Hello ","World");

        System.out.println(u1.apply("Hello"));

        String result = "Hello".transform(u1);
        System.out.println("Result = " + result);
        result = result.transform(String::toLowerCase);
        System.out.println("Result = "+ result);
        Function<String, Boolean> f0 = String::isEmpty;
        boolean resultBolean = result.transform(f0);
        System.out.println("Result = " +  resultBolean);

        list.sort(String::compareToIgnoreCase);
    }

    private static PlainOld[] seedArray (Supplier<PlainOld> referece, int count){
        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array,i-> referece.get());
        return array;
    }
    private static <T> void calculator (BinaryOperator<T> function, T value1, T value2){
        T result = function.apply(value1,value2);
        System.out.println("Result : " + result);
    }
}
