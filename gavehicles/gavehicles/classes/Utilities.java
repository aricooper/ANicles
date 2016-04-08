package gavehicles.classes;

public class Utilities {
    
    static boolean DEBUG;

    public static int randomInt(int max) {
        return (int) (Math.random() * max);
    }
    
    public static double randomDouble(int max) {
        return Math.random() * max;
    }
    
    public static void debug(String s) {
        if (DEBUG) {
            System.out.println("s = " + s);
        }
    }

}
