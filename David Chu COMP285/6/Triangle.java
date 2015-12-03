/**
 * Triangle adds base and height and implements getArea.
 */

/**
 * @author chud
 *
 */
public class Triangle extends TwoDimensionalShape {
	private double base, height;
	
	public Triangle() {
		this(0,0);
	}
	
	/**
	 * 
	 * @param base base of the triangle
	 * @param height height of the triangle
	 */
	public Triangle(double base, double height) {
		this.height = height;
		this.base = base;
	}
	
	public void setBase(double base) {
		this.base = base;
	}
	
	public double geBase() {
		return base;
	}

	@Override
	public double getArea() {
		return ((base * height)/2);
	}

	@Override
	public String toString() {
		return String.format("I am a triangle.    My area is %.2f.", getArea());
	}
}
