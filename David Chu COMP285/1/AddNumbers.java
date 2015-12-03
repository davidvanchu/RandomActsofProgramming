import java.util.Scanner;


public class AddNumbers {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter two integers.");
	
		int num1, num2 = 0;
		
		num1 = kb.nextInt();
		num2 = kb.nextInt();
		
		System.out.println("Thank you for entering the number " + num1 + " and the number " + num2 + ".");
		System.out.println("The sum of these two integers is " + (num1 + num2) + ".");
		System.out.println("\"|\"|\"the end\"|\"|\"");
	}

}
