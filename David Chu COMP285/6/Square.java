/**
 * Subclass of rectangle which restricts the dimensions width and height to be the same. setHeight and setWidth are overridden
 * to change both height and width when changing either one.
 * @author chud
 *
 */
public class Square extends Rectangle {
	
	public Square() {
		this(0);
	}
	
	public Square(double sideLength) {
		setHeight(sideLength);
	}
	
	@Override
	public void setHeight(double height) {
		super.setHeight(height);
		super.setWidth(height);
	}
	
	@Override
	public void setWidth(double width) {
		setHeight(width);
	}
	
	@Override
	public String toString() {
		return String.format("I am a square.      My area is %.2f.", getArea());
	}
}
