/**
 * Course: JHU 605.621 
 * Assignment PA 1
 * Author: Mike Macey
 * Description: This program initializes two arrays, one of size n,
 * the other of size m. The size of m is the square root of n.
 * Insertion sort is used to insert random numbers into both arrays.
 * From there, binary search is implemented to find an element
 * within each array. The two arrays are merged once the smaller array
 * has no more room for insertion. An output file is produced that
 * analyzes the complexity of all three algorithms over both array.
 */
import java.io.FileWriter;
import java.io.IOException;

public class FileRunner {
	
	// Initialize small array variables for analysis
	static int sortStepM;
	static int sortCountM;
	static int sortSizeM;
	static int searchStepM;
	static int searchCountM;
	static int searchSizeM;
	
	// Initialize large array variables for analysis
	static int sortStepN;
	static int sortCountN;
	static int sortSizeN;
	static int searchStepN;
	static int searchCountN;
	static int searchSizeN;
	
	// Initialize array merge variables for analysis 
	static int mergeStep;
	static int mergeCount;
	static int mergeSize;

	public static void main(String[] args) throws IOException {

		int[] inputSet = {100, 10000, 500000, 1000000};

		// Set up file path destination
		String path = "/Users/maceyma/Desktop/605.621/PA_1/MMacey_PA_1_Output_File.txt";
		
		FileWriter writer = new FileWriter(path);

		// Loop through each input size in the designated input set
		for (int input : inputSet) {
			
			// Re-initialize small array variables for analysis 
			sortStepM = 0;
			sortCountM = 0;
			sortSizeM = 0;
			searchStepM = 0;
			searchCountM = 0;
			searchSizeM = 0;

			// Re-initialize large array variables for analysis 
			sortStepN = 0;
			sortCountN = 0;
			sortSizeN = 0;
			searchStepN = 0;
			searchCountN = 0;
			searchSizeN = 0;
			
			// Re-initialize array merge variables for analysis 
			mergeStep = 0;
			mergeCount = 0;
			mergeSize = 0;

			// Construct large array from arbitrary starting size (25)
			int[] n = new int[25];
			int inputCounter = 0;
			

			// Set up long array values
			for (int i = 0; i < n.length; i++) {
				int randomNumber = (int) (Math.random() * 1000 + 1);
				insertionSortN(n, randomNumber, i);
				sortSizeN = sortSizeN + n.length;
				sortCountN++;
				inputCounter++;

			}

			// Loop through entire input size to carry out the analysis over the set construct
			while (inputCounter <= input) {

				// Construct small array based off large array size
				int[] m = new int[(int) (Math.sqrt(n.length * 1.0))];

				
				for (int i = 0; i < m.length && inputCounter <= input; i++) {

					int randomNumber = (int) (Math.random() * 1000 + 1);
					insertionSortM(m, randomNumber, i);
					sortSizeM = sortSizeM + m.length;
					sortCountM++;
					inputCounter++;

				}

				// Pick random number to be sought after in binary search
				int searchNumber = (int) (Math.random() * 1000 + 1);
				
				// Initialize binary search on small array
				binarySearchM(m, searchNumber);
				searchSizeM = searchSizeM + m.length;
				searchCountM++;
				
				// Initialize binary seach on large array
				binarySearchN(n, searchNumber);
				searchSizeN = searchSizeN + n.length;
				searchCountN++;
				
				// Initialize merging of small and large arrays
				if(m[m.length-1] != 0) {
					n = mergeArrays(m, n);
					mergeSize = mergeSize + m.length + n.length;
					mergeCount++;
				}

			}
			
			// Format output for algorithm analysis
			writer.write("---------- Analysis for Input of Size " + input + " ----------\n");
			writer.write("INSERTION SORT - small array (m)\n");
			writer.write("Total steps executed during all insertion sorts: " + sortStepM + "\n");
			writer.write("Number of insertion executed: " + sortCountM + "\n");
			writer.write("Average number of steps per insertion sort: " + (1.0*sortStepM / sortCountM) + "\n");
			writer.write("Average array size: " + (1.0*sortSizeM/sortCountM) + "\n\n");
			
			writer.write("INSERTION SORT - large array (n)\n");
			writer.write("Total steps executed during all insertion sorts: " + sortStepN + "\n");
			writer.write("Number of insertion executed: " + sortCountN + "\n");
			writer.write("Average number of steps per insertion sort: " + (1.0*sortStepN / sortCountN) + "\n");
			writer.write("Average array size: " + (1.0*sortSizeN/sortCountN) + "\n\n");
			
			writer.write("BINARY SEARCH - small array (m)\n");
			writer.write("Total steps executed during all binary searches: " + searchStepM + "\n");
			writer.write("Number of searches executed: " + searchCountM + "\n");
			writer.write("Average number of steps per binary search: " + (1.0*searchStepM / searchCountM) + "\n");
			writer.write("Average array size: " + (1.0*searchSizeM/searchCountM) + "\n\n");
			
			writer.write("BINARY SEARCH - large array (n)\n");
			writer.write("Total steps executed during all binary searches: " + searchStepN + "\n");
			writer.write("Number of searches executed: " + searchCountN + "\n");
			writer.write("Average number of steps per binary search: " + (1.0*searchStepN / searchCountN) + "\n");
			writer.write("Average array size: " + (1.0*searchSizeN/searchCountN) + "\n\n");
			
			writer.write("MERGE ARRAYS - (m + n)\n");
			writer.write("Total steps executed during all array merges: " + mergeStep + "\n");
			writer.write("Number of merges executed: " + mergeCount + "\n");
			writer.write("Average number of steps per array merge: " + (1.0*mergeStep / mergeCount) + "\n");
			writer.write("Average array size: " + (1.0*mergeSize/mergeCount) + "\n\n\n\n");
			
		}
		writer.close();
	}
	
	/**
	 * Method to insert element into small sorted array.
	 * @param data - an array
	 * @param key - an element to be inserted into array
	 * @param n - the level at which the array is filled
	 * @return mapping of the last insertion point into the array
	 */
	static int insertionSortM(int data[], int key, int n) {

		if (n >= data.length) {
			sortStepM++;
			return n;
		}

		int i;
		sortStepM++;
		
		for (i = n - 1; (i >= 0 && data[i] > key); i--) {
			data[i + 1] = data[i];
			sortStepM += 3;
		}

		data[i + 1] = key;
		sortStepM++;

		return (n + 1);
		
	} //insertedSortM

	/**
	 * Method to insert element into large sorted array.
	 * @param data - an array
	 * @param key - an element to be inserted into array
	 * @param n - the level at which the array is filled
	 * @return mapping of the last insertion point into the array
	 */
	static int insertionSortN(int data[], int key, int n) {

		if (n >= data.length) {
			sortCountN++;
			return n;
		}

		int i;
		sortCountN++;
		
		for (i = n - 1; (i >= 0 && data[i] > key); i--) {
			data[i + 1] = data[i];
			sortStepN += 3;
		}

		data[i + 1] = key;
		sortStepN++;

		return (n + 1);
		
	} // insertionSortN

	/**
	 * Method to perform binary search on small array.
	 * @param data - an array
	 * @param x - an element to find in array
	 * @return index of array (-1 = not found)
	 */
	public static int binarySearchM(int data[], int x) {

		int left = 0;
		int right = data.length - 1;
		searchStepM += 2;

		while (left <= right) {

			int pivot = (left + right) / 2;
			searchStepM += 2;

			if (data[pivot] == x) {
				searchStepM++;
				return pivot;
			}

			if (data[pivot] < x) {
				searchStepM++;
				left = pivot + 1;
			}

			else {
				searchStepM++;
				right = pivot - 1;
			}
		}

		return -1;

	} // end binarySearchM

	/**
	 * Method to perform binary search on large array.
	 * @param data - an array
	 * @param x - an element to find in array
	 * @return index of array (-1 = not found)
	 */
	public static int binarySearchN(int data[], int x) {

		int left = 0;
		int right = data.length - 1;
		searchStepN += 2;

		while (left <= right) {

			
			int pivot = (left + right) / 2;
			searchStepN += 2;

			if (data[pivot] == x) {
				searchStepN++;
				return pivot;
			}

			if (data[pivot] < x) {
				searchStepN++;
				left = pivot + 1;
			}

			else {
				searchStepN++;
				right = pivot - 1;
			}
		}

		return -1;

	} // end binarySearchN
	
	/**
	 * Method to merge to sorted arrays.
	 * @param m - small array
	 * @param n - large array
	 * @return sorted merged array of m + n
	 */
	public static int[] mergeArrays(int[] m, int[] n) {

		int[] data = new int[m.length + n.length];
		int i = 0;
		int j = 0;
		int k = 0;
		mergeCount += 4;

		while (i < m.length && j < n.length) {
			
			mergeStep++;

			if (m[i] < n[j]) {
				data[k] = m[i];
				i++;
				mergeStep += 2;
			} else {
				data[k] = n[j];
				j++;
				mergeStep += 2;
			}
			k++;
			mergeStep++;
		}

		while (i < m.length) {
			data[k] = m[i];
			i++;
			k++;
			mergeStep += 4;
		}

		while (j < n.length) {
			data[k] = n[j];
			j++;
			k++;
			mergeStep += 4;
		}

		return data;
		
	} // end mergeArrays

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

} // FileRunner
