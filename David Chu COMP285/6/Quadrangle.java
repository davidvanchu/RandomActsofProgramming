/**
 * This abstract class is a subclass of TwoDimensionalShape,
 * which adds height and width variables and setters and getters for each.
 * @author chud
 *
 */
public abstract class Quadrangle extends TwoDimensionalShape {
	private double height, width;
	
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	
}
