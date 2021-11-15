package challenges;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the
 * input string is valid. An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * <p>
 * Open brackets must be closed in the correct order.
 * <p>
 * Requirements: Using Hash Map and Stack (or LinkedList as stack)
 */
public class ValidParentheses {

  public static void main(String[] args) {
    String right = "()[]{}";
    String wrong = "([)]";
    System.out.println(valid(right)); // true
    System.out.println(valid(wrong)); // false
  }

  static boolean valid(String s) {
    // Setup HashMap with parentheses pairs
    Map<Character, Character> map = new HashMap<>();
    map.put('(', ')');
    map.put('[', ']');
    map.put('{', '}');

    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      char character = s.charAt(i);
      if (map.containsKey(character)) { // If character is an opening parentheses
        stack.push(character);
      } else if (map.containsValue(character)) { // If character is a closing parentheses
        // If stack is not empty and stack has the corresponding parentheses
        if (!stack.isEmpty() && map.get(stack.peek()) == character) {
          stack.pop();
        } else {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
}
