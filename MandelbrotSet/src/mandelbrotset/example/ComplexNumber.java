package mandelbrotset.example;

public class ComplexNumber {
	
	double re = 0;
	double im = 0;
	
	public ComplexNumber(double re, double im){
		this.re = re;
		this.im = im;
	}
	
	public double lengthSquared(){
		return re * re + im * im;
	}

	public double length(){
		return Math.sqrt(lengthSquared());
	}
	
	public static double length(double re,double im){
		return re * re + im * im;
	}
	
}
