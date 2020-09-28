package mandelbrotset.example;

public class MandelbrotSetModel {
	/**
	 * セルの数（縦）
	 */
	public static final int ARRAY_HEIGHT = 5000;
	/**
	 * セルの数（横）
	 */
	public static final int ARRAY_WIDTH = 5000;
	/**
	 * 横軸の幅
	 */
	private double width = 3.0;
	/**
	 * 縦軸の幅
	 */
	private double height = 3.0;
	// the top-left X coordinate of the bounds
	/**
	 * 描画スタート位置(X座標) - 左端のX座標(MIN)
	 */
	private double startX = -1.5;
	/**
	 * 描画スタート位置(Y座標) - 上端のY座標(MIN)
	 */
	private double startY = 1.5;
	private Cell[][] space = null;
	/**
	 * 発散条件（この回数For文を回して計算を繰り返し2を超えなければ発散しないと判定する）
	 */
	public static final int MAX_COUNT = 1000;

	public MandelbrotSetModel() {
		space = new Cell[ARRAY_HEIGHT][ARRAY_WIDTH];
	}

	public Cell[][] getArray() {
		return space;
	}

	public void calc() {
		double dx = width / ARRAY_WIDTH;
		double dy = height / ARRAY_HEIGHT;

		for (int yArrayIndex = 0; yArrayIndex < ARRAY_HEIGHT; yArrayIndex++) {
			double coordinateY = startY - dy * yArrayIndex;
			for (int xArrayIndex = 0; xArrayIndex < ARRAY_WIDTH; xArrayIndex++) {
				double coordinateX = startX + dx * xArrayIndex;
				space[yArrayIndex][xArrayIndex] = judgeIfConvergent(coordinateX, coordinateY);
			}
		}
	}

	/**
	 * 対象の座標が発散するか否かを判定する。
	 */
	private Cell judgeIfConvergent(double coordinateX, double coordinateY) {
		ComplexNumber start = new ComplexNumber(0, 0);
		// 現在の座標
		final ComplexNumber constant = new ComplexNumber(coordinateX, coordinateY);
		ComplexNumber next = new ComplexNumber(start.real, start.imaginary);

		for (int i = 0; i < MAX_COUNT; i++) {
			if (next.length() > 2) {
				// ２を超えたら発散したと判断する
				return new Cell(false, i);
			} else {
				// 漸化式の計算
				next.set(next.real * next.real - next.imaginary * next.imaginary + constant.real,
						2 * next.real * next.imaginary + constant.imaginary);
			}
		}
		return new Cell(true, MAX_COUNT);
	}
}
