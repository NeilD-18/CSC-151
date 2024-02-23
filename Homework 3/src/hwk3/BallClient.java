package hwk3;
/**
 * Fills an array with 5 random Balls and sorts using a PQ.
 * 
 * @author Chris Fernandes
 * @version 2/16/2024
 */
public class BallClient
{
    public static void main(String[] args)
    {
        int n = 5;   // we sort n = 5 balls

        Ball[] A = new Ball[n];

        A[0] = new Ball();
        A[1] = new Ball(6.6,"white");
        A[2] = new Ball(8.9,"yellow");
        A[3] = new Ball(8.5,"orange");
        A[4] = new Ball(8.8,"white");
        
        // print, sort, then print again
        printArray(A);
        sort(A);
        printArray(A);            
     }

    // prints the array
    public static void printArray(Ball[] array){
        for (int i = 0; i < array.length; i++) 
        {
          System.out.print(array[i]);
          if (i!=array.length-1)
              System.out.print("\n");
        }
        System.out.println("\n"); 
    }
    
    // sorts using the generic PQ
    // Just put 'em all in & take 'em all out
    public static void sort(Ball[] A) {
    	PriorityQueue<Ball> PQ = new PriorityQueue<Ball>();

    	for (int i = 0; i < A.length; i++) { 
    		PQ.insert(A[i]);
    	}
   
    	for (int i = A.length-1; i >= 0; i--) {
    		A[i] = PQ.remove(); // highest priority item removed 1st!
    	}                       
    }
}
