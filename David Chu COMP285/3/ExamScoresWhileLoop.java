/**
 * Problem 2 with while loop
 * @author chud
 * @professor Dr. Leon Deligiannidis
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExamScoresWhileLoop {
	
	public static void main(String[] args) {
		ArrayList<Integer> integerList = new ArrayList<Integer>();
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Enter grades between 0 and 100 inclusive and we will output the\n"
				+ "number of grades in each letter category, as well sa the total number of grades.");
		
		int gradeToAdd = 0;
		while (gradeToAdd >= 0){
			try {
				gradeToAdd = kb.nextInt();
			}
			catch (InputMismatchException e) {
				System.err.println("Enter an integer the next time! Program closing.");
				System.exit(-1);
			}
			if (gradeToAdd >= 0 && gradeToAdd <= 100)
				integerList.add(gradeToAdd);
		}
		
		int[] list = {0,0,0,0,0};
		
		//list[0] is the counter for A's, list[1] is the counter for B's, etc
		
		int i = 0;
		while (i < integerList.size()) {
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
			i++;
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
