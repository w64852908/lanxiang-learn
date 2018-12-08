package iv.util;

/**
 * Created by lanjing on 2018/12/1.
 */
public class ArrayUtil {

    public static void display(char[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }
        for (char a : arr) {
            System.out.print(a + ",");
        }
        System.out.println();
    }

    public static void display(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }
        for (int a : arr) {
            System.out.print(a + ",");
        }
        System.out.println();
    }
}
