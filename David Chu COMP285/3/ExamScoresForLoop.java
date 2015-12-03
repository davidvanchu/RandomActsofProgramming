/**
 *  Assignment 3
 *  Problem 2
 *  Write a program to read a list of exam scores given as integer percentages in the
 *  range 0 to 100. Display the local number of grades and the number of grades in each
 *  letter-grade category as follows: 90 to 100 is an A, 80 to 89 is a B, 70 to 79 is a C,
 *  60 to 69 is a D, and 0 to 59 is an F. Use a negative value score as a sentinel value
 *  to indicate the end of the input. (The negative value is used only to end the loop, so do
 *  not use it in the calculations.) For example, if the input is 98 87 86 85 78 73 72 72 72 70 66
 *  63 50 -1 the output would be
 *
 *	Total number of grades = 14
 *	Number of A's = 1
 *	Number of B's = 4
 *	Number of C's = 6
 *	Number of D's = 2
 *	Number of F's = 1
 *	USE ONLY for loops
 * 
 * @author chud
 * @professor Dr. Leon Deligiannidis
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExamScoresForLoop {

	public static void main(String[] args) {
		ArrayList<Integer> integerList = new ArrayList<Integer>();
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Enter grades between 0 and 100 inclusive and we will output the\n"
				+ "number of grades in each letter category, as well sa the total number of grades.");
		
		for (int i = 1; i > 0; i++) {
			try {
				i = kb.nextInt();
			} catch(InputMismatchException e) {
				System.err.println("Enter an integer the next time! Program closing.");
				System.exit(-1);
			}
			if (i >= 0 && i <= 100)
				integerList.add(i);
		}
		
		int[] list = {0,0,0,0,0};
		
		for (int i = 0; i < integerList.size(); i++) {
			if (integerList.get(i) >= 90)
				list[0]++;
			else if (integerList.get(i) >= 80)
				list[1]++;
			else if (integerList.get(i) >= 70)
				list[2]++;
			else if (integerList.get(i) >= 60)
				list[3]++;
			else
				list[4]++;
		}
		System.out.println("Total number of grades = " + integerList.size() +
				"\nNumber of A's = " + list[0]);
		System.out.println("Number of B's = " + list[1]);
		System.out.println("Number of C's = " + list[2]);
		System.out.println("Number of D's = " + list[3]);
		System.out.println("Number of F's = " + list[4]);
		kb.close();
	}
}
