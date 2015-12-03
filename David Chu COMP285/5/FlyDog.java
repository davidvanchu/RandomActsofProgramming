public class FlyDog extends Dog {
	private static int numFlyDogs = 0;
	public FlyDog() {
		this("", 0, "");
	}
	
	public FlyDog(String name, int age, String breed) {
		super(name, age, breed);
		setCanFly(true);
		setType("flying dog");
		numFlyDogs++;
		}

	public static int getNumFlyDogs() {
		return numFlyDogs;
	}
	
	public String toString() {
		return ("I am a " + this.getType() + " named " + this.getName() + ". I am a " +
					this.getAge() + " year old. My breed is " + this.getBreed() + ". I have " + this.getNumLegs() + " legs and I can fly.");
	}
	
}
