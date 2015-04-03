/**
 * Created by MD on 3/31/2015.
 */
public class Herbivore implements Organism {
    private int age = 1;
    private int energy = 6;
    private final static int MAX = 700;
    public static Herbivore [] herbivoreTracker = new Herbivore[MAX];
    public static int [] herbX = new int[MAX];
    public static int [] herbY = new int[MAX];
    private static int herbivoreCount = 0;
    private static Object [][] ecoArray;
    public final int initPop = 300;
    public static int time = 1;

    public static int left = 0;
    public static int right = 0;


    public Herbivore(Object [][] ecoArray){
        this.ecoArray = ecoArray;
    }
    public Herbivore(int X, int Y) {
        boolean herbAttempt = tryHerbivore(X, Y);
        if(!herbAttempt)
            this.ecoArray[X][Y] = ".";

    }
    private boolean tryHerbivore(int X, int Y){
        if(herbivoreCount == MAX)
            return false;
        if (this.ecoArray[X][Y] == "." || this.ecoArray[X][Y] == "~") {
            this.ecoArray[X][Y] = "&";
            this.herbivoreTracker[herbivoreCount] = this;
            this.herbX[herbivoreCount] = X;
            this.herbY[herbivoreCount] = Y;
            herbivoreCount++;
            return true;
        }else{
            if((Y - 1) >= 0) {
                this.left++;
                tryHerbivore(X, Y - 1);
            }
            else if((Y + this.left + 1) < 32){
                tryHerbivore(X, Y + this.left + 1);
                this.left = 0;
            }else if((X - 1) >= 0 ) {
                this.right++;
                tryHerbivore(X - 1, Y);
            }else if((X + this.right + 1) < 32){
                tryHerbivore(X + this.right + 1, Y);
                this.right = 0;
            }
        }
        return false;
    }
    public void setAge() {
        for (int i = 0; i < herbivoreCount; i++) {
            herbivoreTracker[i].age++;
            herbivoreTracker[i].energy--;
            if(herbivoreTracker[i].energy < 1) checkEnergy(i);
        }
    }
    public void addTime(){
        time++;
        grow();
        move();
        setAge();
    }
    public static int getHerbivoreCount(){
        return herbivoreCount;
    }
    private void giveBirth(){
        for (int i = 0; i < herbivoreCount; i++) {
            if(herbivoreTracker[i].age > 2 &&  herbivoreTracker[i].energy > 4)
                tryHerbivore(herbX[i], herbY[i]);
        }

    }
    private void checkEnergy(int i){
               removeHerbivore(i);
    }
    public void removeHerbivore(int i){
        this.ecoArray[herbX[i]][herbY[i]] = ".";
        System.arraycopy(herbivoreTracker, i + 1, herbivoreTracker, i, herbivoreTracker.length - 1 -i);
        System.arraycopy(herbX, i + 1, herbX, i, herbX.length - 1 -i);
        System.arraycopy(herbY, i + 1, herbY, i, herbY.length - 1 -i);
        herbivoreCount--;
    }
    public static void decreaseCount(){
        herbivoreCount--;
    }
    @Override
    public void grow() {
        giveBirth();
    }
    private void moveHelper(int X, int Y, int j){
        if(Y-1 >= 0) {
            if (ecoArray[X][Y - 1] == "." || ecoArray[X][Y - 1] == "~") {
                int rand = randGen();
                if(rand == 2) {
                    ecoArray[X][Y] = ".";
                    eat(X, Y-1, j);
                    ecoArray[X][Y - 1] = "&";
                    herbY[j] -= 1;
                    return;
                }
            }

        }else if(Y+1 <= 31) {
            if (ecoArray[X][Y + 1] == "." || ecoArray[X][Y + 1] == "~") {
                int rand = randGen();
                if(rand == 2) {
                    ecoArray[X][Y] = ".";
                    eat(X, Y+1, j);
                    ecoArray[X][Y + 1] = "&";
                    herbY[j] += 1;
                    return;
                }
            }
        }else if(X-1 >= 0) {
            if (ecoArray[X - 1][Y] == "." || ecoArray[X - 1][Y] == "~") {
                int rand = randGen();
                if(rand == 2) {
                    ecoArray[X][Y] = ".";
                    eat(X-1, Y, j);
                    ecoArray[X - 1][Y] = "&";
                    herbX[j] -= 1;
                    return;
                }
            }
        }else if(X+1 <= 31) {
            if (ecoArray[X + 1][Y] == "." || ecoArray[X + 1][Y] == "~") {
                int rand = randGen();
                if(rand == 2) {
                    ecoArray[X][Y] = ".";
                    eat(X+1, Y, j);
                    ecoArray[X + 1][Y] = "&";
                    herbX[j] += 1;
                }
            }
        }

    }
    @Override
    public void move() {
        for (int i = 0; i < herbivoreCount; i++) {
            moveHelper(herbX[i], herbY[i], i);
        }
    }

    @Override
    public void eat(int X, int Y, int herbIndex) {
        if(ecoArray[X][Y] == "~") {
            herbivoreTracker[herbIndex].energy += 2;
            int plantIndex = 0;
            for (int i = 0; i < Plant.usedArray; i++) {
                if(Plant.xPlant[i] == X){
                    for (int j = 0; j < Plant.usedArray; j++) {
                        if(Plant.yPlant[j] == Y){
                            plantIndex = j;
                            System.arraycopy(Plant.xPlant, plantIndex + 1, Plant.xPlant, plantIndex, Plant.xPlant.length - 1 -plantIndex);
                            System.arraycopy(Plant.yPlant, plantIndex + 1, Plant.yPlant, plantIndex, Plant.yPlant.length - 1 -plantIndex);
                            Plant.usedArray--;
                            break;
                        }
                    }
                }
                }

            }
        }


    public int randGen(){
        int Random = (int)(Math.random()*2+1);
        return Random;
    }
}
