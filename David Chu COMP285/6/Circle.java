/**
 * A subclass of TwoDimensionalShape which adds a radius variable and implements the getArea function.
 * It also has functions getDiameter and setDiameter which, behind the scenes, uses the radius variable instead of
 * creating a diameter variable.
 * @author chud
 *
 */
public class Circle extends TwoDimensionalShape {
	double radius;
	
	public Circle () {
		this(0);
	}
	
	public Circle(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getDiameter() {
		return (2 * radius);
	}
	
	public void setDiameter(double diameter) {
		radius = (diameter / 2);
	}

	public double getArea() {
		return (Math.PI * Math.pow(radius, 2));
	}
	
	@Override
	public String toString() {
		return String.format("I am a circle.      My area is %.2f.", getArea());
	}
	
}
