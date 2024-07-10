package LmabdaIntro;

@FunctionalInterface //that's verify it's functional interface
public interface Operation <T>{//functional interface

    T operate(T value1, T value2);
}
