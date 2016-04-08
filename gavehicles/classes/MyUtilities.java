package gavehicles.classes;

public class MyUtilities {

    protected static boolean DEBUG = false;
    
    protected static int frameW = 1200;
    protected static int frameH = 800;
    protected static int food = 15;
    protected static int preySize = 1;
    protected static int predSize = 0;
    protected static int genTime = 1000;
    
    public static void debug(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }
    
    public static int randomInt(int max) {
        return (int) (Math.random() * max);
    }
    
    public static int randomInt(int min, int max) {
        int returnMe = min;
        returnMe += (int) (Math.random() * (max - min));
        return returnMe;
    }
    
    public static double randomDouble(double max) {
        return Math.random() * max;
    }
    
    public static double randomDouble(double min, double max) {
        double returnMe = min;
        returnMe += (Math.random() * (max - min));
        return returnMe;
    }
    
    public static int getWidth() {
        return frameW;
    }
    
    public static int getHeight() {
        return frameH;
    }
    
    public static int getFood() {
        return food;
    }

    public static int getPreySize() {
        return preySize;
    }
    
    public static int getPredSize() {
        return predSize;
    }
    
    public static int getGenTime() {
        return genTime;
    }
    
}
