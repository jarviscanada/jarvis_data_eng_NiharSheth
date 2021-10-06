package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * https://www.notion.so/jarvisdev/Valid-Anagram-baedd3125087410d895554c368883ab9
 */
public class ValidAnagram {

  public static void main(String[] args) {
    String s = "abcdef";
    String t = "fadbec";

    System.out.println(isAnagram(s, t));
  }

  /**
   * Compare two Strings and return true if they are valid anagrams of one another.
   *
   * @param s
   * @param t
   * @return
   */
  public static boolean isAnagram(String s, String t) {
    return sortString(s).equals(sortString(t));
  }

  /**
   * Sort String by characters.
   *
   * @param input
   * @return
   */
  public static String sortString(String input) {
    char[] characterArray = input.toCharArray();
    Arrays.sort(characterArray);
    return String.valueOf(characterArray);
  }
}
