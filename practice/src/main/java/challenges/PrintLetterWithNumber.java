package challenges;

/**
 * Given a String which contains only lower and upper case letters, print letter index after each
 * character. For example, the index of a is 1, b is 2, A is 27, and so on.
 */
public class PrintLetterWithNumber {

  public static void main(String[] args) {
    String input = "abceeABCEE";
    System.out.println(printWithNumber(input));
  }

  static String printWithNumber(String input) {
    String output = "";
    char[] inputCharArray = input.toCharArray();

    for (char c : inputCharArray) {
      int number = c - 38;
      if (number > 52) {
        number = number - 58;
      }
      output = output + c + number;
    }
    return output;
  }

}
