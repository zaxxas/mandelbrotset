package mandelbrotset.example;

public class MandelbrotSetModel {
	
	public static final int ARRAY_HEIGHT = 5000;
	public static final int ARRAY_WIDTH = 5000;
	//the width of bounds
	private double width = 3.0;
	//the height of bounds
	private double height = 3.0;
	//the top-left X coordinate of the bounds 
	private double start_x = -1.5;
	//the top-left Y coordinate of the bounds 
	private double start_y = 1.5;
	private CellData[][] space = null;
	public static final int MAX_COUNT = 1000;
	
	public MandelbrotSetModel(){
		space = new CellData[ARRAY_HEIGHT][ARRAY_WIDTH];
	}
	
	public CellData[][] getArray(){
		return space;
	}
	
	public void calc(){

		double dx = width / ARRAY_WIDTH;
		double dy = height / ARRAY_HEIGHT;
		
		for ( int yArrayIndex = 0 ; yArrayIndex < ARRAY_HEIGHT ; yArrayIndex ++){
			double coordinateY = start_y - dy * yArrayIndex;
			for ( int xArrayIndex = 0 ; xArrayIndex < ARRAY_WIDTH; xArrayIndex ++){
				double coordinateX = start_x + dx * xArrayIndex;
				space[yArrayIndex][xArrayIndex] = calc(coordinateX, coordinateY);
			}
		}
	}
	
	private CellData calc(double coordinateX , double coordinateY){
		ComplexNumber x0 = new ComplexNumber(0,0);
		ComplexNumber constant = new ComplexNumber(coordinateX,coordinateY);
		ComplexNumber nxt = x0;
		for(int i = 0 ; i < MAX_COUNT ; i ++ ){
			//if the 
			if(nxt.length() > 2){
				return new CellData(false,i);
			}else{
				nxt = mandelbrotCalc(nxt,constant);
			}
		}
		return new CellData(true,MAX_COUNT);
	}
	
	public ComplexNumber mandelbrotCalc(ComplexNumber c,ComplexNumber constant){
		double tmp_re = c.re * c.re - c.im * c.im + constant.re;
		double tmp_im = 2 * c.re * c.im + constant.im;
		return new ComplexNumber(tmp_re,tmp_im);
	}

}
