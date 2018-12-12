package project7;



public class Test {
	public static void main(String[] args) {
		GeometricObject[] array = {new Square(1.0,"White",true), new Square(2.0,"Yellow",false), new Square(3.0,"Blue",true),
				new Square(4.0,"Green",true), new Square(5.0,"Red",false)};
		for(int i=0;i<array.length;i++) {
			if(array[i].isFilled()) {
				System.out.println("for the " + (i+1) +" " + array[i].toString());
				((Square) array[i]).howToColor();
			}
		}
		System.out.println();
		
		Octagon x = new Octagon(5,"White",true);
		System.out.println(x.toString());
		Octagon clonex = x.clone();
		System.out.println("x.compareTo(conlex) is :" + x.compareTo(clonex));
	}
}
