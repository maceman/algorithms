/**
 * Course: JHU 605.621 
 * Assignment PA 2
 * Author: Mike Macey
 * Description: This program initializes two arrays (holding the 
 * same values) and runs two sorting algorithms over them:
 * a standard version of quicksort and a modified version of
 * quicksort using median-of-three as a partitioning component.
 * 
 */

import java.io.FileWriter;
import java.io.IOException;

public class Runner {
	
	// Initialize algorithm step counters
	static int stdStepCount = 0;
	static int modStepCount = 0;

	public static void main(String[] args) throws IOException {

		// Set up file path and input sizes
		String path = "/Users/maceyma/Desktop/605.621/PA_2/MMacey_PA_2_Output_File.txt";
		FileWriter writer = new FileWriter(path);
		int[] inputSet = {100, 10000, 500000, 1000000};
		
		for(int input : inputSet) {
			
			int[] data = new int[input];
			int[] copy = new int[input];
			
			// Set up array values
			for (int i = 0; i < data.length; i++) {
				int element =  (int) (Math.random() * 1000 + 1);
				data[i] = element;
				copy[i] = element;
			}
			
			// Run algorithms on same data
			StandardQuicksort(data, 0, data.length-1);
			ModifiedQuicksort(copy, 0, copy.length-1);
			
			// Format output for algorithm analysis
			writer.write("---------- Analysis for Input of Size " + input + " ----------\n");
			writer.write("STANDARD Qiucksort\n");
			writer.write("Total steps executed: " + stdStepCount + "\n");
			writer.write("MEDIAN-OF-THREE Quicksort\n");
			writer.write("Total steps executed: " + modStepCount + "\n\n");
			
			// Set step counts back to zero
			stdStepCount = 0;
			modStepCount = 0;
			
		}
		
		writer.close();
				
	} // end main
	
	/**
	 * This method performs a standard version of quicksort
	 * @param data - an array
	 * @param p - index of first element in array
	 * @param r - index of last element in array
	 * @return - sorted array
	 */
	static int[] StandardQuicksort(int[] data, int p, int r) {
		
		if(p < r) {
			int q = Partition(data, p, r);
			stdStepCount++;
			StandardQuicksort(data, p, q - 1);
			stdStepCount++;
			StandardQuicksort(data, q + 1, r);
			stdStepCount++;
		}
		stdStepCount++;
		
		return data;
		
	} // end StandardQuicksort - implemented based on CLRS code repository
	
	/**
	 * This method performs a modified version of quicksort
	 * on an array partitioning by median-of-three
	 * logic
	 * @param data - an array
	 * @param p - index of first element in array
	 * @param r - index of last element in array
	 * @return - sorted array
	 */
	static int[] ModifiedQuicksort(int[] data, int p, int r) {
		
		if(p < r) {
			int q = Partition(data, p, r);
			modStepCount++;
			ModifiedQuicksort(data, p, q - 1);
			modStepCount++;
			ModifiedQuicksort(data, q + 1, r);
			modStepCount++;
		}
		modStepCount++;
		
		return data;
		
	} // end ModifiedQuicksort - implemented based on CLRS code repository
	
	/**
	 * This method partitions an array for quicksort
	 * based on the first and last elements of the
	 * given array
	 * @param data - an array
	 * @param p - index of first element in array
	 * @param r - index of last element in array
	 * @return - partition value
	 */
	static int Partition(int[] data, int p, int r) {
		
		int x = data[r];
		int i = p - 1;
		stdStepCount = stdStepCount + 2;
		
		for(int j = p; j < r; j++) {
			if(data[j] <= x) {
				i = i + 1;
				exchange(data, i, j);
				stdStepCount = stdStepCount + 3;
			}
			stdStepCount++;
		}
		
		exchange(data, i + 1, r);
		stdStepCount++;
		return i + 1;
		
	} // end Partition - implemented based on CLRS code repository
	
	/**
	 * This method finds the median of the first, middle
	 * and last element of a given array to use as a
	 * partition value
	 * @param data - an array
	 * @param p - index of first element in array
	 * @param r - index of last element in array
	 * @return - partition value
	 */
	static int MedianOfThreePartition(int[] data, int p, int r) {
		
		int i = data[r];
		int j = data[p];
		int k = data[Math.floorDiv((r + p), 2)];
		modStepCount = modStepCount + 3;
		
		if(i > k) {
			if(k > j) {
				modStepCount = modStepCount + 2;
				return k;
			}
			else if(j > k) {
				modStepCount = modStepCount + 3;
				return j;
			}
			else {
				modStepCount = modStepCount + 3;
				return i;
			}
		}
		else {
			if(i > j) {
				modStepCount = modStepCount + 2;
				return i;
			}
			else if(j > i) {
				modStepCount = modStepCount + 3;
				return j;
			}
			else {
				modStepCount = modStepCount + 3;
				return k;
			}
		}
		
	} // end MedianOfThreePartition
	
	/**
	 * Utility method to exchange two elements
	 * @param data - an array
	 * @param a - the first element to be exchanged
	 * @param b - the second element to be exchanged
	 */
	static void exchange(int[]data, int a, int b) {
		
		int temp = data[a];
		data[a] = data[b];
		data[b] = temp;
		stdStepCount = stdStepCount + 3;
		
	} // end exchange
	
	/**
	 * Utility method to help print arrays.
	 * @param data - an array
	 */
	static void printArray(int data[]) {

		for (int i = 0; i < data.length; ++i) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
		
	} // end printArray

} // end Runner
