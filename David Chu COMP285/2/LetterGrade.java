import java.util.Scanner;


public class LetterGrade {

	public static void main(String[] args) {
		getLetterGrade();
	}
	public static void getLetterGrade() {
		System.out.println("Please enter your numberical grade.");
		Scanner kb = new Scanner(System.in);
		
		float grade = kb.nextFloat();
		
		if (grade > 100 || grade < 0) {
			System.err.println("Enter a grade between 0-100 inclusive. Goodbye.");
			System.exit(-1);
		}
		if (grade >= 90)
			System.out.println('A');
		else if (grade >= 80)
			System.out.println('B');
		else if (grade >= 70)
			System.out.println('C');
		else if (grade >= 60)
			System.out.println('D');
		else
			System.out.println('F');
		
	}

}
