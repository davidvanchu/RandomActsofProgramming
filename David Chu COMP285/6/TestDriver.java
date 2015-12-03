/**
 * This is the test driver class which will create an array of Shape using Shape's various subclasses. It will then
 * print out the area of the shape, and if it's an instance of a ThreeDimensionalShape, it will also print
 * the volume of the shape.
 * @author chud
 *
 */
public class TestDriver {
	private static Shape[] shapes = new Shape[7];
	public static void main(String[] args) {
		
		//initialize shape objects
		shapes[0] = new Circle(5);
		shapes[1] = new Rectangle(2, 7);
		shapes[2] = new Square(8);
		shapes[3] = new Triangle(9,3);
		shapes[4] = new Sphere(5);
		shapes[5] = new Cube(8);
		shapes[6] = new Tetrahedron(9);
		
		/* 
		 * s.getClass().toString().substring(6).toLowerCase() gets the name of the class and cuts off the "class" part
		 * then makes all the letters lower case.
		 */
		for (Shape s : shapes) {
			System.out.printf("I am a " + s.getClass().toString().substring(6).toLowerCase() + ". My area is %.2f. ", s.getArea());
			if (s instanceof ThreeDimensionalShape)
				System.out.printf("\tI'm a 3D Shape! My volume is %.2f.\n", ((ThreeDimensionalShape)s).getVolume());
			else
				System.out.println();
		}
	}

}
