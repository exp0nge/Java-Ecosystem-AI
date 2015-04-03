/**
 * Created by MD on 3/31/2015.
 */
public class Ecosystem {

    private final int obstacleCount = 100;
    public static Object [][] ecoArray;
    Plant p1;
    Herbivore h1;
    Carnivore c1;

    public Ecosystem(){
        ecoArray = new Object[32][32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                ecoArray[i][j] = ".";
            }
        }
        p1 = new Plant(ecoArray);
        h1 = new Herbivore(ecoArray);
        c1 = new Carnivore(ecoArray);

        initObstacles();
        initPlants();
        initHerbivores();
        initCarnivore();
    }
    public void addTime(){
        p1.addTime();
        h1.addTime();
        c1.addTime();
    }
    private void initObstacles(){
        for (int i = 0; i < this.obstacleCount; i++) {
            int randX = randGen();
            int randY = randGen();
            ecoArray[randX][randY] = "|";
        }
    }
    private void initPlants(){
        int size = p1.initPop;
        for (int i = 0; i < size; i++) {
            int randX = randGen();
            int randY = randGen();
            Plant plant = new Plant(randX, randY);
        }
    }
    private void initHerbivores(){
        int size = h1.initPop;
        for (int i = 0; i < size; i++) {
            int randX = randGen();
            int randY = randGen();
            Herbivore herbivore = new Herbivore(randX, randY);
        }
    }
    private void initCarnivore(){
        int size = c1.initPop;
        for (int i = 0; i < size; i++) {
            int randX = randGen();
            int randY = randGen();
            Carnivore carnivore = new Carnivore(randX, randY);
        }
    }
    public void print(){
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                System.out.print(ecoArray[i][j]);

            }
            System.out.println();
        }

    }
    public void getStats(){
        System.out.println("Herbs: " + h1.getHerbivoreCount() + " Carns: " + c1.getCarnivoreCount() + " Plants: " + p1.getPlantCount());
    }
    public static int randGen(){
        int Random = (int)(Math.random()*32);
        return Random;
    }
}
