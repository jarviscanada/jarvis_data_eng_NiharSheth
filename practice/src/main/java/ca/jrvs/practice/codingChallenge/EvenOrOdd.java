package ca.jrvs.practice.codingChallenge;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-372cc81d61504c52aee24861d74d5097
 */
public class EvenOrOdd {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int input;

    System.out.print("Input: ");

    try {
      input = scanner.nextInt();
      System.out.println(parityCheck(input));
    } catch (InputMismatchException e) {
      System.err.println("Invalid input, enter integer value.");
    }
  }

  /**
   * Return "even" if passed number is even, return "odd" otherwise.
   *
   * @param number
   * @return
   */
  public static String parityCheck(int number) {
    if (number % 2 == 0) {
      return "even";
    } else {
      return "odd";
    }
  }
}
