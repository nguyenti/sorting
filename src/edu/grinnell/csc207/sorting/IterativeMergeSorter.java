package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sort using iterative merge sort.
 * 
 * @author Samuel A. Rebelsky
 * @author Tiffany Nguyen
 * @author Mark Lewis
 * @author John Brady
 * 
 */
public class IterativeMergeSorter<T> extends SorterBridge<T> {
    /**
     * Sort vals using iterative merge sort. See the Sorter<T> interface for
     * additional details.
     * 
     * TEST THIS AND CHECK THE INVARIANT
     * 
     * Invariant: Subarrays of size size are sorted
     * 
     */
    /*
     * @SuppressWarnings("unchecked")
     * 
     * @Override public T[] sorti(T[] vals, Comparator<T> order) { for (int i =
     * 1; i <= vals.length / 2 + 1; i *= 2) { for (int j = i; j < vals.length; j
     * += 2 * i) { inPlaceMerge(vals, j - i, j, Math.min(j + i, vals.length),
     * order); } }
     * 
     * return vals; } // sorti(T[], Comparator<T>)
     */

    @Override
    public T[] sorti(T[] vals, Comparator<T> order) {
	int size = 1;
	while (size < vals.length) {
	    // Merge neighboring subarrays of size size
	    int numMerge = size;
	    T[] temp1 = (T[]) new Object[size];
	    T[] temp2 = (T[]) new Object[size];
	    T[] output = (T[]) new Object[size * 2];
	    if (size * 2 <= vals.length) {
		while (numMerge < vals.length) {
		    System.arraycopy(vals, numMerge - size, temp1, 0, size);
		    System.arraycopy(vals, numMerge, temp2, 0, size);
		    output = Utils.merge(order, temp1, temp2);
		    // FILL IN!
		    for (int i = 0; i < output.length; i++) {
			vals[i + numMerge - size] = output[i];
		    } // for
		    numMerge += size * 2;
		} // while
		numMerge -= size;
		int diff = vals.length - numMerge;
		if (diff > 0) {
		    int lastSize = size / 2;
		    int lastleftover = numMerge + lastSize;
		    int leftoverOLeftover = vals.length - lastleftover;
		    output = (T[]) new Object[diff];
		    temp1 = (T[]) new Object[lastSize];
		    temp2 = (T[]) new Object[leftoverOLeftover];
		    if (diff > lastSize) {
			System.arraycopy(vals, numMerge, temp1, 0, lastSize);
		    } else {
			System.arraycopy(vals, numMerge, temp1, 0, diff);
		    }
		    if (leftoverOLeftover != 0) {
			System.arraycopy(vals, lastleftover, temp2, 0,
				leftoverOLeftover);
		    }
		    output = Utils.merge(order, temp1, temp2);
		    // FILL IN!
		    for (int i = 0; i < diff; i++) {
			vals[numMerge + i] = output[i];
		    }
		}
	    } else {
		output = (T[]) new Object[vals.length];
		temp1 = (T[]) new Object[size];
		temp2 = (T[]) new Object[vals.length - size];
		System.arraycopy(vals, 0, temp1, 0, size);
		System.arraycopy(vals, size, temp2, 0,
			vals.length - size);
		output = Utils.merge(order, temp1, temp2);
		// FILL IN!
		for (int i = 0; i < vals.length; i++) {
		    vals[i] = output[i];
		}
	    }
	    // The merged subarrays are now twice as large
	    size *= 2;
	} // while
	return vals;
    } // sorti(T[], Comparator<T>)

} // IterativeMergeSorter<T>
