
public class FlyCat extends Cat {
	private static int numFlyCats = 0;
	public FlyCat() {
		this("", 0);
	}
	
	public FlyCat(String name, int age) {
		super(name, age);
		setCanFly(true);
		setType("flying cat");
		numFlyCats++;
		}

	public static int getNumFlyCats() {
		return numFlyCats;
	}
}
