import java.util.Scanner;


public class IsMultiple {
	static int num1, num2;
	public static void main(String[] args) {
		entry();
	}
	
	public static void entry() {
		num1 = num2 = 0;
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter two integers.");
				
		num1 = kb.nextInt();
		num2 = kb.nextInt();
				
		if (num1 == 0 || num2 == 0) {
			System.out.println("Please enter an integer other than 0.");
			entry();
		}
		else if (num1 % num2 == 0) {
			System.out.println(num2 + " is a multiple of " + num1 + ".");
		}

		else if (num2 % num1 == 0) {
			System.out.println(num1 + " is a multiple of " + num2 + ".");
		}
		else {
			System.out.println(num1 + " is not a multiple of " + num2 + " and " + num2 + " is not a"
					+ " multiple of " + num1 + ".");
			entry();
		}
		kb.close();
	}

}
