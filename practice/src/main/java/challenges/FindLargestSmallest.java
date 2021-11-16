package challenges;

public class FindLargestSmallest {

  public static void main(String[] args) {
    int[] array = {5, 2, 8, 3, 4, 9, 7, 1, 8};
    int[] minMax = minMaxForLoop(array);
    System.out.println("Minimum value in array: " + minMax[0]);
    System.out.println("Maximum value in array: " + minMax[1]);
  }

  /**
   * Exactly one for loop
   * @param array
   * @return
   */
  static int[] minMaxForLoop(int[] array) {
    int[] minMax = new int[2];
    int max = 0;
    int min = array[0];

    for(int i : array) {
      if(i > max) {
        max = i;
      }
      if(i < min) {
        min = i;
      }
    }
    minMax[0] = min;
    minMax[1] = max;
    return minMax;
  }
}
