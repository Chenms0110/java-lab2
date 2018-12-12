package project7;

import java.text.DecimalFormat;

public class Octagon extends GeometricObject implements Cloneable,Comparable<Octagon>{

	double side;
	
	public Octagon() {
		super();
		side = 1.0;
	}
	
	public Octagon(double s,String color,boolean filled) {
		super(color,filled);
		side = s;
	}
	
	@Override
	public Octagon clone() {
		Octagon o = null;
		try {
			o = (Octagon)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
	@Override
	public int compareTo(Octagon o) {
		if(getArea()>o.getArea()) {
			return 1;
		}
		else if(getArea() < o.getArea()) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		double area = (2 + 4/Math.sqrt(2))*side*side;
		return area;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return (side * 8);
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00"); 
		return "Octagon [side=" + side + ", getArea()=" + df.format(getArea()) + ", getPerimeter()=" + getPerimeter() + "]";
	}

}
