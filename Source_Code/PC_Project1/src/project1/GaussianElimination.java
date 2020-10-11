package project1;

public class GaussianElimination {
	
	
	
	public double [][] gaussianElimination (double [][] A, double [][] b) {
		
		int rows = A.length;
		int cols = A[0].length;
		double [][] x = new double [rows][1];
		double [] tempRow;
		int maxPivotRow;
		
		for (int i = 0; i<rows; i++) {
			maxPivotRow = i;
			for (int j=i+1 ; j<rows; j+= 1) {
				if (Math.abs(A[j][i]) > Math.abs(A[maxPivotRow][i]))
					maxPivotRow = j;
				
				tempRow = A[i]; 
	            A[i] = A[maxPivotRow]; 
	            A[maxPivotRow] = tempRow;
			}
		}
		
		
		return x;
		
	} 
	public void PrintMatrix (double [][] matrix) {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
