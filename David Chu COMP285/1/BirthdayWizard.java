import java.util.Calendar;
import java.util.Scanner;

/*
 *Given a person's year of birth, the Birthday Wizard can compute the year 
 *in which the person's nth birthday will occur or has occurred. 
 *Write statements that can be used in a Java program to perform this
 *computation for the Birthday Wizard.
 */
public class BirthdayWizard {

	public static void main(String[] args) {
		theWizard();
	}
	
	public static void theWizard() {
		Scanner kb = new Scanner(System.in);
		int birthYear, nthBirthday = 0;
		
		System.out.println("Enter the year of birth.");
		birthYear = kb.nextInt();
		System.out.println("Enter nth Birthday.");
		nthBirthday = kb.nextInt();
		
		System.out.println("The person will turn " + nthBirthday + " in " + (birthYear + nthBirthday));
		
	}

}
