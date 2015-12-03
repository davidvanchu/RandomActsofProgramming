/**
 * The generic Animal class, which is to be used for more specific subclasses of animals such as dogs, cats, etc.
 * @author chud
 * 
 */

public class Animal {
	private String name;
	private int numLegs;
	private boolean canFly;
	private double age;
	/** The type of the animal should be the same as the subclass name */
	private String type;
	private static int numAnimals = 0;

	public Animal() {
		this("", 0, false, 0, "");
	}
	
	public Animal(String name, int numLegs, boolean canFly, double age, String type) {
		this.name = name;
		this.numLegs = numLegs;
		this.canFly = canFly;
		this.age = age;
		this.type = type;
		numAnimals++;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the numLegs
	 */
	public int getNumLegs() {
		return numLegs;
	}
	/**
	 * @param numLegs the numLegs to set
	 */
	public void setNumLegs(int numLegs) {
		this.numLegs = numLegs;
	}
	/**
	 * @return the canFly
	 */
	public boolean isCanFly() {
		return canFly;
	}
	/**
	 * @param canFly the canFly to set
	 */
	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}
	/**
	 * @return the age
	 */
	public double getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(double age) {
		this.age = age;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the numAnimals, the number of instances of animals.
	 */
	public static int getNumAnimals() {
		return numAnimals;
	}
	/**
	 * @param numAnimals the numAnimals to set
	 */
	public static void setNumAnimals(int numAnimals) {
		Animal.numAnimals = numAnimals;
	}
	
	/**
	 * @return default description of the animal
	 */
	public String toString () {
		if (canFly)
			return ("I am a " + type + " named " + name + ". I am a " + 
					age + " year old. I have " + numLegs + " legs and I can fly.");
		else
			return ("I am a " + type + " named " + name + ". I am a " +
					age + " year old. I have " + numLegs + " legs.");
			
	}
}
