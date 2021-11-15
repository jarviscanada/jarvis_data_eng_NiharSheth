package practice;

import java.util.Arrays;

public class Sorting {

  public static void main(String[] args) {
    int[] numbers = {13, 6, 4, 1, 2, 11, 5, 8, 12, 10, 9, 3, 7};

    System.out.println(
        "Insertion sort: " + Arrays.deepToString(new int[][]{insertionSort(numbers)}));
    System.out.println("Bubble sort: " + Arrays.deepToString(new int[][]{bubbleSort(numbers)}));
  }

  static int[] insertionSort(int[] numbers) {
    for (int count = 1; count < numbers.length; count++) {
      int reference = numbers[count];
      int check = count - 1;

      while (check >= 0 && numbers[check] > reference) {
        numbers[check + 1] = numbers[check];
        check--;
      }
      numbers[check + 1] = reference;
    }
    return numbers;
  }

  static int[] bubbleSort(int[] numbers) {
    for (int count = 0; count < numbers.length; count++) {
      for (int check = 0; check < numbers.length - 1 - count; check++) {
        if (numbers[check] > numbers[check + 1]) {
          int temp = numbers[check + 1];
          numbers[check + 1] = numbers[check];
          numbers[check] = temp;
        }
      }
    }
    return numbers;
  }
}
