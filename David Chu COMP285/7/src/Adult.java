/**
 * Extension of the Person class which implements the toString and toStringAsDBFormat methods required by Person.
 * @author chud
 *
 */
public class Adult extends Person {
	public Adult() {
		this("John", "Doe", 42);
	}
	
	public Adult(String fName, String lName, int age) {
		super(fName, lName, age);
	}

	@Override
	public String toString() {
		return String.format("Hi my name is %s %s and I am %d years old.", fName, lName, age);
	}

	@Override
	public String toStringAsDBFormat() {
		return fName + ":" + lName + ":" + age + "\n";
	}
}
