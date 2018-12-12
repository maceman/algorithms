// Libraries to be used in program
import java.io.FileWriter; 

public class Runner {
	
	static int valueCounter = 0;

	public static void main(String args[]) {
	
		int index = 1;

		// Set up 25 test cases
		String test_set[][] = {		{"101","000","000101"},// 1
						{"000","101","101000"},// 2
						{"0","11101101","001101011"},// 3
						{"0","11","101"},// 4
						{"1010","1111","10010111"},// 5
						{"1","0","01"},// 6
						{"011","001","111111"},// 7
						{"000000","111111","010101010101"},// 8
						{"","",""},// 9
						{"01000","11111","000000"},// 10
						{"010111","011100","0000"},// 11
						{"010","111","110101000000"},// 12
						{"111000101010","001111111111","111000101010001111111111"},// 13
						{"111111111111111111111111111111",
						"000000000000000000000000000000",
						"101010101010101010101010101010101010101010101010101010101010"},// 14
						{"011100100","110010000110",""},// 15
						{"000000110000111","111010110000001","111010110000001000000110000111"},// 16
						{"001","100","111111"},// 17
						{"1001010","0110101","10010100110101"},// 18
						{"111000111000","100000000001","11"},// 19
						{"00","01","1000"},// 20
						{"100000001","011111110","0111111101000000010"},// 21
						{"100000001","011111110","011111110100000001"},// 22
						{"111","000","101011"},// 23
						{"1100","0011","10100001"},// 24
						{"01010101","11111110","01010111000001111"}// 25
					};
		
		for(String[] str : test_set) {
			System.out.println("Test Case: " + index);
			System.out.println("Result: " + interleaving(str[2], str[0], str[1]));
			System.out.println("Input Length: " + str[2].length());
			System.out.println("Value Counter: " + valueCounter);
			System.out.println();
			index++;
		}
		
	} // end main
	
	/**
	* Method determines if two strings, x and y, are an interleaving
	* of a larger string, s
	* @param s - a suspected superposition of x and y
	* @param x - first substring
	* @param y - second substring
	* @return boolean
	*/
	public static boolean interleaving(String s, String x, String y) {
		
		// Re-initialize counter
		valueCounter = 0;
		
		// Initialize memoization database
		char db[][] = new char[x.length()+2][y.length()+2];
	
		// Format database for display
		db[0][0] = ' ';
		db[0][1] = 'X';
		db[1][0] = 'Y';
		
		// Add substrings to database for display
		for(int i=0; i < db.length-2; i++) {
			db[i+2][0] = y.charAt(i);
		}
		for(int j=0; j < db.length-2; j++) {
			db[0][j+2] = x.charAt(j);
		}
		
		// Check substring lengths to determine
		// algorithm compatibility
		if(x.length() + y.length() != s.length()) {
			return false;
		}
		
		// Intialize looping through strings and database
		for(int i=0; i < db.length-1; i++) {
			for(int j=0; j < db[i].length-1; j++) {
			
				// Set length of substring x,y combination 
				int n = i + j - 1;

				// Set first db cell to 't' (true)
				if(i == 0 && j == 0) {
					db[i+1][j+1] = 't';
					valueCounter++;
				}

				// Compute first column of memoization db for
				// value comparison
				else if(i == 0) {
					if(y.charAt(j-1) == s.charAt(n)) {
						db[i+1][j+1] = db[i+1][j];
						valueCounter++;
					}
					else {
						db[i+1][j+1] = 'f';
						valueCounter++;
					}
				}

				// Compute first row of memoization db for
				// value comparison
				else if(j == 0) {
					if(x.charAt(i-1) == s.charAt(n)) {
						db[i+1][j+1] = db[i][j+1];
						valueCounter++;
					}
					else {
						db[i+1][j+1] = 'f';
						valueCounter++;
					}
				}
			
				// Compare substring x values to last
				// value in superstring s
				else if(x.charAt(i-1) == s.charAt(n)) {
					db[i+1][j+1] = db[i][j+1];
					valueCounter++;
				}

				// Compare substring y values to last 
				// value insuperstring s
				else if(y.charAt(j-1) == s.charAt(n)) {
					db[i+1][j+1] = db[i+1][j];
					valueCounter++;
				}
				else {
					db[i+1][j+1] = 'f';
					valueCounter++;
				}
			}
		}
	
		// Print memoization database if strings are small
		// enough to view in console
		if(x.length() < 10 && y.length() < 10) {
			printDB(db);
		}

		// Checks the last element of the memoization database
		// to determine if strings are interleaving
		if(db[x.length()+1][y.length()+1] == 't') {
			return true;
		}
		else {
			return false;
		}
	} // end interleaving

	/**
	 * This method prints out the memoization database
	 * @param base - a double char array
	 */
	public static void printDB(char[][] base) {
		for(int i = 0; i < base.length; i++) {
			for(int j = 0; j < base[i].length; j++) {
				System.out.print("[" + base[i][j] + "] ");
			}
			System.out.println();
		}
	} // end printDB

} // end Runner
