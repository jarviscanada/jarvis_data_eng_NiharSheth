package challenges;

import java.util.Scanner;

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 */
public class StringToInteger {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter String to be converted to an integer: ");
    String input = scanner.next();

    try {
      System.out.println("Parsing method: " + parsingMethod(input));
    } catch (NumberFormatException e) {
      System.err.println("Invalid input type.");
      e.printStackTrace();
    }
  }

  static int parsingMethod(String input) throws NumberFormatException {
    return Integer.parseInt(input);
  }
}
