/**
 * Rectangle implements getArea which returns the area.
 * @author chud
 *
 */
public class Rectangle extends Quadrangle {
	
	public Rectangle() {
		this(0,0);
	}
	
	public Rectangle(double height, double width) {
		setHeight(height);
		setWidth(width);
	}

	@Override
	public double getArea() {
		return (getHeight() * getWidth());
	}

	@Override
	public String toString() {
		return String.format("I am a rectangle.   My area is %.2f.", getArea());
	}

}
