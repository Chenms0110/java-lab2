package project6;

public class Triangle extends GeometricObject {

	private double side1,side2,side3;
	
	public Triangle() {
		super(); //默认白色，填充。
		side1 = 1.0;
		side2 = 1.0;
		side3 = 1.0;
	}
	
	public Triangle(double s1,double s2,double s3,String color,boolean filled) {
		super(color,filled);
		if(((s1+s2)>s3)&&((s1+s3)>s2)&&((s2+s3)>s1)) {
			side1 = s1;
			side2 = s2;
			side3 = s3;
		}
		else {
			System.out.println("三角形三条边数值关系出现问题，设为默认三角形。");
			side1 = 1.0;
			side2 = 1.0;
			side3 = 1.0;
		}
	}
	@Override
	public double getArea() {
		double s,area;
		s = getPerimeter() / 2;
        area = Math.sqrt(s * ( s - side1 ) * ( s - side2 ) * ( s - side3 ));
        return area;
	}
	
	@Override
	public double getPerimeter(){
		return (side1 + side2 + side3);
	}
	
	@Override
	public String toString() {
		return "Triangle: side1=" + side1 + ", side2=" + side2 + ", side3=" + side3;
	}
	
	public double getSide1() {
		return side1;
	}
	public double getSide2() {
		return side2;
	}
	public double getSide3() {
		return side3;
	}
	
}

