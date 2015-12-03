
public class Cat extends Animal {
	private static int numCats = 0;

	public Cat() {
		this("", 0);
	}
	
	public Cat(String name, int age) {
		super(name, 4, false, age, "cat");
		numCats++;
	}
	
	public static int getNumCats() {
		return numCats;
	}
	
	
}