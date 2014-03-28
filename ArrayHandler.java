public class ArrayHandler {    
    public static double[] makeCopy(double[] arr) {
        double[] copy = new double[arr.length];
        for(int i = 0; i < arr.length; i++) {
                copy[i] = arr[i];
        }
        return copy;
	}
    
	public static double[][] makeCopy(double[][] arr) {
        double[][] copy = new double[arr.length][];
        for(int i = 0; i < arr.length; i++) {
                copy[i] = makeCopy(arr[i]);
        }
        return copy;
	}
    
    public static int[] makeCopy(int[] arr) {
        int[] copy = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
                copy[i] = arr[i];
        }
        return copy;
	}
    
	public static int[][] makeCopy(int[][] arr) {
        int[][] copy = new int[arr.length][];
        for(int i = 0; i < arr.length; i++) {
                copy[i] = makeCopy(arr[i]);
        }
        return copy;
	}
    
    public static void print(double [] arr) {
        for(int i = 0; i < arr.length; i++) {            
            System.out.print(arr[i]);
            if(i != arr.length - 1) {
                System.out.print(", ");               
            }
        }
        System.out.println();
    }
	public static void print(double[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            double[] subArr = arr[i];
            System.out.print("row " + i + ": ");
            print(subArr);
        }
        System.out.println();
    }
    
    public static void print(int [] arr) {
        for(int i = 0; i < arr.length; i++) {            
            System.out.print(arr[i]);
            if(i != arr.length - 1) {
                System.out.print(", ");               
            }
        }
        System.out.println();
    }
	public static void print(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            int[] subArr = arr[i];
            System.out.print("row " + i + ": ");
            print(subArr);
        }
        System.out.println();
    }
}
