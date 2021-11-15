package challenges;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such
 * that they add up to target. You may assume that each input would have exactly one solution, and
 * you may not use the same element twice. You can return the answer in any order.
 */
public class TwoSum {

  public static void main(String[] args) {
    int[] nums = {2, 7, 11, 15};
    int target = 9;
    int[] indicesA = twoSumBruteForce(nums, target);
    int[] indicesB = twoSumSorting(nums, target);
    int[] indicesC = twoSumLinear(nums, target);
    System.out.println("Brute force method: " + Arrays.toString(indicesA));
    System.out.println("Sorting method: " + Arrays.toString(indicesB));
    System.out.println("HashMap method: " + Arrays.toString(indicesC));
  }

  /**
   * Brute force method that checks sums for every number until the target it matched.
   * @param nums Array of integers
   * @param target Sum to be found
   * @return
   */
  static int[] twoSumBruteForce(int[] nums, int target) {
    int[] indices = {-1, -1};
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length - 1; j++) {
        if (nums[i] + nums[j] == target) {
          indices[0] = i;
          indices[1] = j;
        }
      }
    }
    return indices;
  }

  static int[] twoSumSorting(int[] nums, int target) {
    int[] indices = {-1, -1};
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > target) {
        continue;
      }

      for (int j = i + 1; j < nums.length - 1; j++) {
        if (nums[j] > target) {
          continue;
        }
        if (nums[i] + nums[j] == target) {
          indices[0] = i;
          indices[1] = j;
        }
      }
    }
    return indices;
  }

  /**
   * HashMap implementation that takes advantage of the improved time complexity.
   * @param nums Array of integers
   * @param target Sum to be found
   * @return
   */
  static int[] twoSumLinear(int[] nums, int target) {
    int[] indices = {-1, -1};
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int difference = target - nums[i];
      if (map.containsKey(difference)) {
        indices[0] = i;
        indices[1] = map.get(difference);
      } else {
        map.put(nums[i], i);
      }
    }
    return indices;
  }
}
