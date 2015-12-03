
public class FlyMiniPig extends FlyPig {
	private static int numFlyMiniPigs = 0;
	public FlyMiniPig() {
		this("", 0);
	}
	
	public FlyMiniPig(String name, int age) {
		super(name, age);
		setType("flying mini pig");
		numFlyMiniPigs++;
		}

	public static int getNumFlyMiniPigs() {
		return numFlyMiniPigs;
	}
}
