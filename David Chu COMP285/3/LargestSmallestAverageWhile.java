/**
 * Problem 1 with while loop
 * @author chud
 * @professor Dr. Leon Deligiannidis
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LargestSmallestAverageWhile {

	public static void main(String[] args) {
		ArrayList<Integer> integerList = new ArrayList<Integer>();
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Enter as many positive integers as you'd like and we will calculate\n"
				+ "the largest and smallest integer, as well as the average of the entered integers.\n"
				+ "End the input with a negative number such as -1.");
		
		int intToAdd = 0;
		while (intToAdd >= 0) {
			try {
				intToAdd = kb.nextInt();
			}
			catch (InputMismatchException e) {
				System.err.println("Enter an integer the next time! Program closing.");
				System.exit(-1);
			}
			if (intToAdd >= 0)
				integerList.add(intToAdd);
		}
		int largestInt = 0;
		int smallestInt = Integer.MAX_VALUE;
		int average = 0;
		
		int i = 0;
		while (i < integerList.size()) {
			if (integerList.get(i) > largestInt) {
				largestInt = integerList.get(i);
			}
			if (integerList.get(i) < smallestInt) {
				smallestInt = integerList.get(i);
			}
			average += integerList.get(i);
			i++;
		}
		
		average /= integerList.size();
		
		System.out.println("The largest integer is " + largestInt + " and the smallest integer is "
				+ smallestInt + " and the average of all the integers entered is " + average);
		kb.close();
	}
}