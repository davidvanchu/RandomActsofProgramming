/**
 * Subclass of Person which adds father and mother name.
 * @author chud
 *
 */
public class Child extends Person {
	String fatherName;
	String motherName;
	
	public Child() {
		this("Jimmy", "Doe", 1, "John", "Jane");
	}
	
	/**
	 * 
	 * @param fName Child's first name
	 * @param lName Child's last name
	 * @param age Child's age
	 * @param fatherName Child's father's first name
	 * @param motherName Child's mother's first name
	 */
	public Child(String fName, String lName, int age, String fatherName, String motherName) {
		super(fName, lName, age);
		this.fatherName = fatherName;
		this.motherName = motherName;
	}
	
	public String getFatherName() {
		return fatherName;
	}
	
	public String getMotherName() {
		return motherName;
	}
	
	@Override
	public String toString() {
		return String.format("Hi my name is %s %s and I am %d years old. My father's "
				+ "name is %s, my mother's name is %s.", fName, lName, age, fatherName, motherName);
	}

	@Override
	public String toStringAsDBFormat() {
		return (fName + ":" + lName + ":" + age + ":" + fatherName + ":" + motherName + "\n");
	}
	
}
