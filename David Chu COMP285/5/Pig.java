/**
 * Pig is a subclass of Animal
 * @author chud
 *
 */
public class Pig extends Animal{
	private static int numPigs = 0;
	
	public Pig() {
		this("", 0);
	}
	
	public Pig(String name, int age) {
		super(name, 4, false, age, "pig");
		numPigs++;
	}
	
	public static int getNumPigs() {
		return numPigs;
	}
}
