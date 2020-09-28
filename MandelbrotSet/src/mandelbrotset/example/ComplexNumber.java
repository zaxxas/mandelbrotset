package mandelbrotset.example;

public class ComplexNumber {
	/**
	 * 実部
	 */
	double real = 0;
	/**
	 * 虚部
	 */
	double imaginary = 0;

	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public double lengthSquared() {
		return real * real + imaginary * imaginary;
	}

	public double length() {
		return Math.sqrt(lengthSquared());
	}

	public static double length(double real, double imaginary) {
		return real * real + imaginary * imaginary;
	}

	public void set(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
}
