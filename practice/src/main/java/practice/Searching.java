package practice;

import java.util.Arrays;

public class Searching {

  public static void main(String[] args) {
    int[] array = {1, 3, 5, 6, 8, 9, 10};
    int targetA = 5;
    int targetB = 7;

    System.out.println("Linear search for " + targetA + ": " + linearSearch(array, targetA));
    System.out.println("Linear search for " + targetB + ": " + linearSearch(array, targetB));

    System.out.println("Binary search for " + targetA + ": " + binarySearch(array, targetA));
    System.out.println("Binary search for " + targetB + ": " + binarySearch(array, targetB));
  }

  /**
   * Basic linear search
   *
   * @param array
   * @param target
   * @return
   */
  static int linearSearch(int[] array, int target) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Iterative binary search.
   *
   * @param array
   * @param target
   * @return
   */
  static int binarySearch(int[] array, int target) {
    Arrays.sort(array);
    int min = array[0];
    int max = array[array.length - 1];
    int middle;
    while (min != max) {
      middle = min + (max - min) / 2;

      if (array[middle] < target) {
        min = middle + 1;
      } else if (array[middle] > target) {
        max = middle - 1;
      } else {
        return middle;
      }
    }
    return -1;
  }
}
