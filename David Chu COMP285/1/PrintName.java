
public class PrintName {
	final static String name = "David Chu";
	public static void main(String[] args) {
		printName();
	}
	
	public static void printName() {
		for (int i = 0; i < 3; i++) {
			System.out.println(name);
		}
	}
}
