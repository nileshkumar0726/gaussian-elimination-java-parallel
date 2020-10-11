package project1;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GE_Parallel extends Thread {
	
	public  static double [][] A;
	public  static double [][] b;
	public  static double [][] x;
	private  static int rows;
	private  static int cols;
	private static int action;
	private int current_row;
	private int i;
	private static final Lock lock = new ReentrantLock();
	private int row_to_work;
	
	public static void setA (double [][] matrix) {
		
		A = new double [rows][cols];
		for (int i=0 ; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				A[i][j] = matrix[i][j];
			}
		}
	}
	
	public static double [][] getA () {
		double [][] tempA = new double[rows][cols];
		for (int i=0 ; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				tempA[i][j] = A[i][j];
			}
		}
		return tempA;
	}
	
	public static void setb (double [][] matrix) {
		b = new double [rows][1];
		for (int i=0 ; i<rows; i++) {
			for (int j=0; j<1; j++) {
				b[i][j] = matrix[i][j];
			}
		}
	}
	
	public static double [][] getb () {
		double [][] tempb = new double[rows][1];
		for (int i=0 ; i<rows; i++) {
			for (int j=0; j<1; j++) {
				tempb[i][j] = b[i][j];
			}
		}
		return tempb;
	}
	
	public static void setx (double [][] matrix) {
		x = new double [rows][1];
		for (int i=0 ; i<rows; i++) {
			for (int j=0; j<1; j++) {
				x[i][j] = matrix[i][j];
			}
		}
	}
	
	public static double [][] getx () {
		double [][] tempx = new double[rows][1];
		for (int i=0 ; i<rows; i++) {
			for (int j=0; j<1; j++) {
				tempx[i][j] = x[i][j];
			}
		}
		return tempx;
	}
	
	public static void setrows (int rows_passed) {
		rows = rows_passed;
	}
	
	public static int getrows () {
		return rows;
	}
	
	public static void setcols (int cols_passed) {
		cols = cols_passed;
	}
	
	public static int getcols () {
		return cols;
	}
	
	public static void setaction (int action_passed) {
		action = action_passed;
	}
	
	public static int getaction () {
		return action;
	}
	
	public void setcurrent_row (int current_row_passed) {
		current_row = current_row_passed;
	}
	
	public int getcurrent_row () {
		return current_row;
	}
	
	public void seti (int i_passed) {
		i = i_passed;
	}
	
	public int geti () {
		return i;
	}

	public void run () { 
		
		if (action == 1) { //make the leading row 1
			
			while (true) {
				
				lock.lock();
				try {
					row_to_work = current_row;
		            current_row += 1;		            
		        } finally {
		            lock.unlock();
		        }
				
				if (current_row > rows) {
					return;
				}
				
				for (int l = i + 1; l<cols; l++) {
					A[row_to_work][l] = A[row_to_work][l] - A[row_to_work][i]*A[i][l];
				}
				b[row_to_work][0] = b[row_to_work][0] - A[row_to_work][i] * b[i][0];
				A[row_to_work][i] = 0;
				
				
				
			}
			
		}
		else if (action == 2) { //subtraction step
			
			System.out.println(Thread.currentThread().getId());
		}
		
	}
	
}
