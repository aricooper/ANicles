package gavehicles.classes;

public class Utilities {

    protected static int frameW = 1200;
    protected static int frameH = 800;
    protected static int food = 15;
    protected static int preySize = 10;
    protected static int predSize = 0;
    
    public static int randomInt(int max) {
        return (int) (Math.random() * max);
    }
    
    public static double randomDouble(int max) {
        return Math.random() * max;
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
    
}
