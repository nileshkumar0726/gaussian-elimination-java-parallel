package project1;

public class Parallel_Driver {
	
	public static void main (String [] args) {
		
		int N = Integer.parseInt(args[0]);
		int threads_to_create = Integer.parseInt(args[1]);
		
		int rows = N;
		int cols = N;
		double [] [] matrix = new double [rows] [cols];
		double [][] b = new double [rows][1]; //just so that it can be treated as a 1-d matrix
		double [][] x = new double [rows][1];
		long starttime;
		long endtime;

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
//		int rows = matrix.length;
//		int cols = matrix[0].length;

		//int threads_to_create = 2;
		GE_Parallel [] ge = new GE_Parallel[threads_to_create];
		
		GE_Parallel.setrows(rows);
		GE_Parallel.setcols(cols);
		GE_Parallel.setA(matrix);
		GE_Parallel.setb(b);
		GE_Parallel.setx(x);
		GE_Parallel.setaction(1);
		
		
		//PrintMatrix(matrix);
		//PrintMatrix(b);
		
//		int rows = matrix.length;
//		int cols = matrix[0].length;
		
		starttime = System.nanoTime();
		for (int i=0; i<rows; i++) {
			//find pivot row
			maxPivotRow = i;
			for (int j=i+1 ; j<rows; j+= 1) {
				if (Math.abs(GE_Parallel.A[j][i]) > Math.abs(GE_Parallel.A[maxPivotRow][i]))
					maxPivotRow = j;
				
				tempRow = GE_Parallel.A[i]; 
				GE_Parallel.A[i] = GE_Parallel.A[maxPivotRow]; 
				GE_Parallel.A[maxPivotRow] = tempRow;
			}
			
			temp = GE_Parallel.A[i][i];
			for (int j = i+1; j<cols; j++) {
				GE_Parallel.A[i][j] = GE_Parallel.A[i][j]/temp;
			}
			GE_Parallel.b[i][0] = GE_Parallel.b[i][0]/temp;  
			GE_Parallel.A[i][i] = 1;
			
			for (int curr_thread=0; curr_thread<threads_to_create; curr_thread++) {
				ge[curr_thread] = new GE_Parallel();
				ge[curr_thread].setcurrent_row(i+1);	
				ge[curr_thread].seti(i);
			}
			
			for (int curr_thread=0; curr_thread<threads_to_create; curr_thread++) {
				ge[curr_thread].start();								
			}
			
			for (int curr_thread=0; curr_thread<threads_to_create; curr_thread++) {
				try {
					ge[curr_thread].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}								
			}
			
			//matrix = ge[0].getA();
			//b = ge[0].getb();
			//x = ge[0].getx();
			
						
		}
		
		//PrintMatrix(matrix);
		//PrintMatrix(b);
		//PrintMatrix(x);
		
		
		for (int i=rows-1; i>=0; i--) {
			
			x[i][0] = GE_Parallel.b[i][0];
			for (int j=i-1; j>=0; j--) {
				GE_Parallel.b[j][0] = GE_Parallel.b[j][0] - GE_Parallel.b[i][0] * GE_Parallel.A[j][i];
			}
		}
		
		//PrintMatrix(matrix);
		//PrintMatrix(b);
		
		endtime = System.nanoTime();
		double elapsedTime = endtime - starttime;
		double seconds = (double)elapsedTime / 1_000_000_000.0;
		System.out.println("Time taken Parallel " +  seconds);
		//PrintMatrix(x);


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
