package project6;

import java.text.DecimalFormat;

public class TestTriangle {

	public static void main(String[] args) {
		Triangle x1 = new Triangle(1,1.5,1,"Yellow",true);
		DecimalFormat df = new DecimalFormat("0.00"); 
		System.out.println(x1.toString());
		System.out.println("Area :" + df.format(x1.getArea()) +" and Perimeter :" + x1.getPerimeter());
		System.out.println(x1.Message());
	}
}
