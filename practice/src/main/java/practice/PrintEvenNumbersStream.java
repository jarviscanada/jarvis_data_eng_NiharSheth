package practice;

import java.util.Arrays;
import java.util.List;

public class PrintEvenNumbersStream {

  public static void main(String[] args) {
    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    List<Integer> numbersList = Arrays.asList(numbers);

    numbersList.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
  }
}
