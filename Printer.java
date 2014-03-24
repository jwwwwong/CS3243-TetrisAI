public class Printer {
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
