/**
 * Created by MD on 3/31/2015.
 */
public class Plant implements Organism {
    public static Object [][] ecoArray;
    public static int [] xPlant = new int[32];
    public static int [] yPlant = new int[32];
    private static int usedArray = 0;
    public static int time = 1;
    public final int initPop = 100;

    public static int left = 0;
    public static int right = 0;

    public Plant(Object [][] ecoArray){
        this.ecoArray = ecoArray;
    }

    public Plant(int initX, int initY) {
        tryPlant(initX, initY);
    }

    public void tryPlant(int X, int Y){
        if (this.ecoArray[X][Y] == ".") {
            this.ecoArray[X][Y] = "~";
            if(usedArray == 32)
                usedArray--;
            this.xPlant[usedArray] = X;
            this.yPlant[usedArray] = Y;
            usedArray++;
        }else{

            if((Y - 1) >= 0) {
                this.left++;
                tryPlant(X, Y - 1);
            }
            else if((Y + this.left + 1) <= 32){
                tryPlant(X, Y + this.left + 1);
                this.left = 0;
            }else if((X - 1) >= 0 ) {
                this.right++;
                tryPlant(X - 1, Y);
            }else if((X + this.right + 1) <= 32){
                tryPlant(X + this.right + 1, Y);
                this.right = 0;
            }
        }
    }

    public void addTime(){
        this.time++;
        grow();
        eat();
    }
    public int getPlantCount(){
        return usedArray;
    }
    @Override
    public void grow() {
        int rand = randGen();
        if(rand > 30){
            for (int i = 0; i < usedArray ; i++) {
                tryPlant(xPlant[i], yPlant[i]);

            }
        }
    }

    @Override
    public void move() {
        // N/A
    }

    @Override
    public void eat() {
        if(time <= DAY)
            grow();
    }

    public int randGen(){
        int Random = (int)(Math.random()*32);
        return Random;
    }
}
