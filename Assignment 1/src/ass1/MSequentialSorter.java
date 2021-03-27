package ass1;

import java.util.ArrayList;
import java.util.List;

/**
 * Had assistance from the following for implementing merge sort:
 * https://www.geeksforgeeks.org/merge-sort/
 * https://stackoverflow.com/questions/30875497/merge-sort-list-java
 */
public class MSequentialSorter implements Sorter {

  /**
   * Sorts a list by splitting it in half, and merging the halves while recursively calling sort()
   * on each half.
   * @param <T>
   * @param list The list to be sorted using merge sort.
   * @return
   */
  @Override
  public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
    // Copy the list data into a new list
    List<T> listCopy = new ArrayList<>(list);

    // There are 0 or 1 elements in the list, no need to sort
    if(listCopy.size() < 2){
      return listCopy;
    }

    // Find the mid point of the list for splitting purposes
    int mid = listCopy.size()/2;

    // Return the 2 halves of a list merged together.
    // Recursively calls sort() on the 'left' and 'right' halves.
    return merge(sort(listCopy.subList(0,mid)),
        sort(listCopy.subList(mid, listCopy.size())));
  }

  /**
   * Takes two halves of a list (left and right) and merges them into one list while maintaining
   * ordering.
   * @param left
   * @param right
   * @param <T>
   * @return
   */
  public <T extends Comparable<? super T>> List<T> merge(List<T> left, List<T> right){
    // Keep track of where in each list we are up to.
    int leftIndex = 0;
    int rightIndex = 0;
    // New list to return.
    List<T> merged = new ArrayList<>();

    // Loop until all of the left and right halves have been sorted and merged into a list.
    while (leftIndex < left.size() && rightIndex < right.size()) {
      if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
        merged.add(left.get(leftIndex++));
      } else {
        merged.add(right.get(rightIndex++));
      }
    }

    // If for some reason there are elements left in each half, add them to the merged list.
    merged.addAll(left.subList(leftIndex, left.size()));
    merged.addAll(right.subList(rightIndex, right.size()));

    return merged;
  }
}