/**
 * Assignment 3
 * Problem 1
 * Write a program to read a list of nonnegative integers and to display the largest integer,
 * the smallest integer, and the average of all the integers. The user indicates the
 * end of the input by entering a negative sentinel value that is not used in finding the
 * largest, smallest, and the average. The average should be a value of type double so that
 * it is computed with a fractional part. USE ONLY for loops
 * 
 * 
 * @author chud
 * @professor Dr. Leon Deligiannidis
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LargestSmallestAverageForLoop {
	
	public static void main(String[] args) {
		ArrayList<Integer> integerList = new ArrayList<Integer>();
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Enter as many positive integers as you'd like and we will calculate\n"
				+ "the largest and smallest integer, as well as the average of the entered integers.\n"
				+ "End the input with a negative number such as -1.");
		
		for (int i = 1; i > 0; i++) {
			try {
				i = kb.nextInt();
			} catch(InputMismatchException e) {
				System.err.println("Enter an integer the next time! Program closing.");
				System.exit(-1);
			}
			if (i >= 0)
				integerList.add(i);
		}
		int largestInt = 0;
		int smallestInt = Integer.MAX_VALUE;
		double average = 0;
		
		for (int i = 0; i < integerList.size(); i++) {
			if (integerList.get(i) > largestInt) {
				largestInt = integerList.get(i);
			}
			if (integerList.get(i) < smallestInt) {
				smallestInt = integerList.get(i);
			}
			average += integerList.get(i);
		}
		
		average /= integerList.size();
		
		System.out.print("The largest integer is " + largestInt + " and the smallest integer is "
				+ smallestInt + " and the average of all the integers entered is ");
		System.out.printf("%.2f", average);
		kb.close();
		System.exit(-1);
	}
}
