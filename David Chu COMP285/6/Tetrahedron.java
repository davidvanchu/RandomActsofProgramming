
public class Tetrahedron extends ThreeDimensionalShape {
	private double edgeLength;
	
	public Tetrahedron() {
		this(0);
	}
	
	public Tetrahedron(double edgeLength) {
		this.edgeLength = edgeLength;
	}
	
	@Override
	public double getArea() {
		return (Math.sqrt(3) * Math.pow(edgeLength, 2));
	}

	@Override
	public double getVolume() {
		return (Math.pow(edgeLength, 3) / (6 * Math.sqrt(2)) );
	}

	@Override
	public String toString() {
		return String.format("I am a tetrahedron. My area is %.2f and my volume is %.2f.", getArea(), getVolume());
	}

}
