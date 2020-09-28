package mandelbrotset.example;

public class Cell {
	/**
	 * 発散するか否か
	 */
	boolean isConvergent = false;
	/**
	 * 計算した回数
	 */
	int calcCount = 0;

	public Cell(boolean isConvergent, int calcCount) {
		this.isConvergent = isConvergent;
		this.calcCount = calcCount;
	}
}
