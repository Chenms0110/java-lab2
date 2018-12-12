package project7;

public class Square extends GeometricObject implements Colorable{
	double side;
	  
	public Square() {
		super();
		side = 1.0;
	}
	  
	public Square(double s,String color,boolean filled) {
		super(color,filled);
		side = s;
	}
	  
	@Override
	public double getArea() {
		return (side * side);
	}
	  
	@Override
	public double getPerimeter() {
		 return (side * 4);
	}
	  
	@Override
	public void howToColor() {
		System.out.println("Color it with " + getColor());
	}

	public double getSide() {
		return side;
	}

	@Override
	public String toString() {
		return "Square [ side=" + side + " area=" + getArea() + " perimeter=" + getPerimeter() + " ]";
	}
}
