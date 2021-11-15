package challenges;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such
 * that each number is the sum of the two preceding ones, starting from 0 and 1.
 *
 * Given n, calculate F(n).
 */
public class Fibonacci {

  public static void main(String[] args) {
    int number = Integer.parseInt(args[0]);

    System.out.println("Recursive method: " + fibonacciRecursive(number));

    int[] memo = new int[number + 1];
    for (int i = 0; i < memo.length; i++) {
      memo[i] = -1;
    }
    System.out.println("Dynamic method: " + fibonacciDynamic(number, memo));
  }

  static int fibonacciRecursive(int number) {
    if (number == 0) {
      return 0;
    } else if (number == 1) {
      return 1;
    } else {
      return fibonacciRecursive(number - 1) + fibonacciRecursive(number - 2);
    }
  }

  static int fibonacciDynamic(int number, int[] memo) {
    if (memo[number] != -1) {
      return memo[number];
    }

    int result;
    if (number == 0) {
      result = 0;
    } else if (number == 1) {
      result = 1;
    } else {
      result = fibonacciDynamic(number - 1, memo) + fibonacciDynamic(number - 2, memo);
    }
    memo[number] = result;
    return result;
  }
}
