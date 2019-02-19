package properties;

/**
 * Created by lanjing on 2018/11/24.
 */
public class ArrayOutputUtil {

    public static void printArray(int[] arr) {
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void printArray(boolean[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("i:" + i + ", " + arr[i]);
        }
        System.out.println();
    }
}
