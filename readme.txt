1- Runnables and source code are provided separately
2- To run code please use jars provided under <runnable_files>
	2.1- Matrix.jar is for sequential version
		- To run it execute <java -jar Matrix.jar 1000>
				Where 1000 is command line arg for N (Matrix size)
	2.2- Matrix_Para.jar is for parallel version
		- To run it execute <java -jar Matrix_Para.jar 1000 3>
				Where 1000 is command line arg for N (Matrix size) and 3 is command line arg for number of threads
3- A and b matrix are generated randomly 
4- Matrix.java contains code for sequential version
5- Parallel_Driver.java contains main function for parallel version and remaining code is in GE_Parallel.java 
6- Time taken is printed on standard output
7- If you wish to see the resultant vector (x) just uncomment PrintMatrix(x) on line 82 for Matrix.java and line 117 for Parallel_Driver.java  
8- Same step can be used to print A matrix uncommenting PrintMatrix(A)
9- Three programs include 
	- Parallel laptop (Dual core)
	- Parallel Lab computer (Quad Core)
	- Sequential Lab computer
