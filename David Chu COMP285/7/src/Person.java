/**
 * The Person class. Stores name and age, since everyone has one. Requires subclasses to implement toString and
 * toStringAsDBFormat
 * @author chud
 *
 */
public abstract class Person {
	String fName, lName;
	int age;
	
	public Person(String fName, String lName, int age) {
		this.fName = fName;
		this.lName = lName;
		this.age = age;
	}
	
	public String getFName() {
		return fName;
	}
	
	public String getLName() {
		return lName;
	}
	
	public int getAge() {
		return age;
	}
	
	public abstract String toString();

	public abstract String toStringAsDBFormat();
}
