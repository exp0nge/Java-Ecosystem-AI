/**
 * Created by MD on 4/1/2015.
 */
public class Carnivore implements Organism{
    private int age = 1;
    private int energy = 3;
    private final static int MAX = 700;
    public static Carnivore [] carnTracker = new Carnivore[700];
    private static int [] carnX = new int[MAX];
    private static int [] carnY = new int[MAX];
    private static int carnivoreCount = 0;
    private static Object [][] ecoArray;
    public final int initPop = 10;
    public static int time;

    public Carnivore(Object [][] ecoArray){
        this.ecoArray = ecoArray;
    }
    public Carnivore(int X, int Y){
        tryCarnivore(X, Y);
    }
    private void tryCarnivore(int X, int Y){

    }
    @Override
    public void grow() {

    }

    @Override
    public void move() {

    }

    @Override
    public void eat(int X, int Y, int orgIndex) {

    }
}
