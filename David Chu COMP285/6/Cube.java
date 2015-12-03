
public class Cube extends ThreeDimensionalShape {
	private double edgeLength;
	
	public Cube() {
		this(0);
	}
	
	public Cube(double edgeLength) {
		this.edgeLength = edgeLength;
	}
	
	@Override
	public double getArea() {
		return (6 * Math.pow(edgeLength, 2));
	}

	@Override
	public double getVolume() {
		return (Math.pow(edgeLength, 3));
	}

	@Override
	public String toString() {
		return String.format("I am a cube.        My area is %.2f and my volume is %.2f.", getArea(), getVolume());
	}

}
