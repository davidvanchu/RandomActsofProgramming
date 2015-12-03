/**
 * The MiniPig class, a subclass of Pig which is a subclass of Animal.
 * @author chud
 *
 */
public class MiniPig extends Pig {
	private static int numMiniPigs = 0;
	public MiniPig() {
		this("", 0);
	}
	
	public MiniPig(String name, int age) {
		super(name, age);
		setType("mini pig");
		numMiniPigs++;
		}

	public static int getNumMiniPigs() {
		return numMiniPigs;
	}
}
