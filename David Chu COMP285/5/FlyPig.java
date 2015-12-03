/**
 * The FlyPig class which is a subclass of Pig which is a subclass of Animal.
 * @author chud
 *
 */


public class FlyPig extends Pig {
	private static int numFlyPigs = 0;
	public FlyPig() {
		this("", 0);
	}
	
	public FlyPig(String name, int age) {
		super(name, age);
		setCanFly(true);
		setType("flying pig");
		numFlyPigs++;
		}

	public static int getNumFlyPigs() {
		return numFlyPigs;
	}
}
