/**
 * A subclass of Animal, has an additional variable for the breed of a dog and converts regular age to dog age.
 * @author chud
 *
 */

public class Dog extends Animal {
	private static int numDogs = 0;
	private String breed;

	public Dog() {
		this("dog", 0, "generic");
	}
	
	/**
	 * 
	 * @param name name of the dog
	 * @param age age of the dog
	 * @param breed breed of the dog
	 */
	public Dog(String name, int age, String breed) {
		super(name, 4, false, age, "dog");
		this.breed = breed;
		numDogs++;
	}
	
	/**
	 * Sets the age, given in human years, to dog years.
	 * @override
	 * @param age in human years
	 */
	public void setAge(double age) {
		if (age <= 2) setAge(age * 10.5);
		else setAge(21 + (4 * (age - 2)));
	}
	
	/**
	 * 
	 * @return the number of instances of dogs
	 */
	public static int getNumDogs() {
		return numDogs;
	}
	
	/**
	 * 
	 * @return the breed of the dog
	 */
	public String getBreed() {
		return breed;
	}

	/**
	 * @return the description of the dog
	 */
	public String toString() {
		return ("I am a dog named " + this.getName() + ". I am a " +
					this.getAge() + " year old. My breed is " + this.getBreed() + ". I have " + this.getNumLegs() + " legs.");
	}
	
	
}