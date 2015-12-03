/**
 * Forces subclasses to implement getArea() which will return the area of the shape,
 * and getVolume() which returns the volume of the shape.
 * @author chud
 *
 */
public abstract class ThreeDimensionalShape extends Shape {
	public abstract double getArea();
	public abstract double getVolume();
}