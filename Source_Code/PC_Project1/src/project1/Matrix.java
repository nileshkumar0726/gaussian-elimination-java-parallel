package project1;

public class Matrix {
		
	public static void main (String [] args) {
		
		int N = Integer.parseInt(args[0]);
		int rows = N;
		int cols = N;
		double [] [] matrix = new double [rows] [cols];
		double [][] b = new double [rows][1]; //just so that it can be treated as a 1-d matrix
		double [][] x = new double [rows][1];

		for (int i=0; i<matrix.length; i++) {
		    for (int j=0; j<matrix[i].length; j++) {
		        matrix[i][j] = (Math.random());
		    }
		    b[i][0] = Math.random();
		    x[i][0] = 0.0;
		}
		
//		double [][] matrix = {{2,3,2}, {1,-1,2}, {2,2,1}};
//		double [][] b = {{6},{0.5},{1}}; //just so that it can be treated as a 1-d matrix
//		double [][] x = {{0},{0},{0}};
		double temp;
		double [] tempRow;
		int maxPivotRow;
		long starttime;
		long endtime;
		
		//PrintMatrix(matrix);
		//PrintMatrix(b);
		
//		int rows = matrix.length;
//		int cols = matrix[0].length;
		
		starttime = System.nanoTime();
		for (int i=0; i<rows; i++) {
			//find pivot row
			maxPivotRow = i;
			for (int j=i+1 ; j<rows; j+= 1) {
				if (Math.abs(matrix[j][i]) > Math.abs(matrix[maxPivotRow][i]))
					maxPivotRow = j;
				
				tempRow = matrix[i]; 
	            matrix[i] = matrix[maxPivotRow]; 
	            matrix[maxPivotRow] = tempRow;
			}
			
			
			temp = matrix[i][i];
			for (int j = i+1; j<cols; j++) {
				matrix[i][j] = matrix[i][j]/temp;
			}
			b[i][0] = b[i][0]/temp;  
			matrix[i][i] = 1;
			
			for (int k=i+1; k<rows; k++) {
				for (int l = i+1; l<cols; l++) {
					matrix[k][l] = matrix[k][l] - matrix[k][i]*matrix[i][l];
				}
				b[k][0] = b[k][0] - matrix[k][i] * b[i][0];
				matrix [k][i] = 0;
				//PrintMatrix(matrix);
				//PrintMatrix(b);
			}
			
		}
		
		//Back substitution
		for (int i=rows-1; i>=0; i--) {
			
			x[i][0] = b[i][0];
			for (int j=i-1; j>=0; j--) {
				b[j][0] = b[j][0] - x[i][0] * matrix[j][i];
			}
		}
		endtime = System.nanoTime();
		double elapsedTime = endtime - starttime;
		double seconds = (double)elapsedTime / 1_000_000_000.0;
		System.out.println("Time taken " +  seconds);
		//PrintMatrix (x);

	}
	
	public static void PrintMatrix (double [][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		System.out.println();
		System.out.println("Matrix Dimensions "+ rows + " " + cols);
		for (int i=0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
