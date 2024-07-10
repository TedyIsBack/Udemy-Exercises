package MethodReferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Examples {

    public static Random random = new Random();
    public static void main(String[] args) {
        String[] names  = {"LucY","Bob","Jessie","IVAN","peter"};

        UnaryOperator<String> lowerCase = String::toLowerCase;
        UnaryOperator<String> upperCase = String::toUpperCase;
        // generate intials
        UnaryOperator<String> addInitials = name -> name += " " + addInitial('A','K');
        //take first name and reverse it
        UnaryOperator<String> reverseNames = name -> name += " " + reversedName(name.split(" ")[0]);

        List<UnaryOperator<String>> functions = new ArrayList<>(List.of(
                lowerCase,upperCase,addInitials,reverseNames
        ));

        workWithNames(names,functions);
    }

    
   private static void workWithNames (String[] names, List<UnaryOperator<String>> functions){
        List<String> listNames = Arrays.asList(names);
        for (var function : functions) {
            listNames.replaceAll(s -> s.transform(function));
            System.out.println(Arrays.toString(names));//print the list after each change
        }
    }
   private static String reversedName(String name){
      StringBuilder builder = new StringBuilder(name);
      return  builder.reverse().toString();
    }
   private static String addInitial(char start, char end){
        return (char)random.nextInt((int)start,(int) end) + ".";
    }
}
