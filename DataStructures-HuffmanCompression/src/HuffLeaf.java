
public class HuffLeaf extends HuffTree {
	
	private int value;
	
	public HuffLeaf(int value, int freq) {
		this.value = value;
		frequency = freq;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
