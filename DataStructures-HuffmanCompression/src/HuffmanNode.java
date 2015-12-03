
public class HuffmanNode extends HuffTree {
	private HuffTree left;
	private HuffTree right;
	
	public HuffmanNode(int freq, HuffTree left, HuffTree right) {
		frequency = freq;
		this.setLeft(left);
		this.setRight(right);
	}

	public HuffTree getLeft() {
		return left;
	}

	public void setLeft(HuffTree left) {
		this.left = left;
	}

	public HuffTree getRight() {
		return right;
	}

	public void setRight(HuffTree right) {
		this.right = right;
	}
}
