
public class Sphere extends ThreeDimensionalShape {
	private double radius;
	
	public Sphere() {
		this(0);
	}
	
	public Sphere(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	@Override
	public double getArea() {
		return (4 * Math.PI * Math.pow(radius, 2));
	}

	@Override
	public double getVolume() {
		return (4/3 * Math.PI * Math.pow(radius, 3));
	}

	@Override
	public String toString() {
		return String.format("I am a sphere.      My area is %.2f and my volume is %.2f.", getArea(), getVolume());
	}
	
}
