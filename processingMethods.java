import java.util.*;
public class processingMethods {
  public static boolean[] isEqualTo(float[] arr, float val) {
    // add your code here
    // replace code below with your own return statement
    boolean[] indicator = new boolean[arr.length];
    for (int i = 0; i < arr.length; i++) {
      indicator[i] = (arr[i] == val);
    }
    return indicator;
  }

  public static boolean[] logicalNot(boolean[] arr, int lo, int hi) {
    // add your code here
    // replace code below with your own return statement
    int range = hi - lo;
    boolean[] indicator = new boolean[range];
    for (int i = lo; i < hi; i++) {
      if (!arr[i]) {
        indicator[i] = true;
      }
    }
    return indicator;
  }


  public <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

    // Create a new ArrayList
    ArrayList<T> newList = new ArrayList<T>();
    ArrayList<T> temps = new ArrayList<T>();

    // Traverse through the first list
    for (T element : list) {

      // If this element is not present in newList
      // then add it
      if (!newList.contains(element)) {
        newList.add(element);
      } else {
        temps.add(element);
      }
    }

    // return the new list
    return new ArrayList<T>(removeCommon(temps, newList));

  }

  public <T> ArrayList<T> duplicates(ArrayList<T> list) {

    // Create a new ArrayList
    ArrayList<T> newList = new ArrayList<T>();
    ArrayList<T> temps = new ArrayList<T>();

    // Traverse through the first list
    for (T element : list) {

      // If this element is not present in newList
      // then add it
      if (!newList.contains(element)) {
        newList.add(element);
      }else{
        temps.add(element);
      }
    }

    // return the new list
    return temps;

  }
  public <T> ArrayList<T> removeCommon(ArrayList<T> A1, ArrayList<T> A2) {
// Prepare a union
    ArrayList<T> union = new ArrayList<T>(A1);
    union.addAll(A2);
// Prepare an intersection
    ArrayList<T> intersection = new ArrayList<T>(A1);
    intersection.retainAll(A2);
// Subtract the intersection from the union
    union.removeAll(intersection);
    return union;
  }




}