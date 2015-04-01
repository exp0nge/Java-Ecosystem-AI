/**
 * Created by MD on 3/31/2015.
 */
public class Herbivore implements Organism {
    private int age = 1;
    private int energy = 3;
    private final static int MAX = 700;
    public static Herbivore [] herbivoreTracker = new Herbivore[700];
    private static int [] herbX = new int[MAX];
    private static int [] herbY = new int[MAX];
    private static int herbivoreCount = 0;
    private static Object [][] ecoArray;
    public final int initPop = 70;
    public static int time;

    public static int left = 0;
    public static int right = 0;


    public Herbivore(Object [][] ecoArray){
        this.ecoArray = ecoArray;
        herbivoreCount = 0;
    }
    public Herbivore(int X, int Y) {
        tryHerbivore(X, Y);
    }
    public void tryHerbivore(int X, int Y){
        if (this.ecoArray[X][Y] == "." || this.ecoArray[X][Y] == "~") {
            this.ecoArray[X][Y] = "&";
            if(this.ecoArray[X][Y] == "~")
                this.energy++;

            if(herbivoreCount == MAX)
                herbivoreCount--;
            herbivoreTracker[herbivoreCount] = this;
            this.herbX[herbivoreCount] = X;
            this.herbY[herbivoreCount] = Y;
            herbivoreCount++;
        }else{
            if((Y - 1) >= 0) {
                this.left++;
                tryHerbivore(X, Y - 1);
            }
            else if((Y + this.left + 1) <= 32){
                tryHerbivore(X, Y + this.left + 1);
                this.left = 0;
            }else if((X - 1) >= 0 ) {
                this.right++;
                tryHerbivore(X - 1, Y);
            }else if((X + this.right + 1) <= 32){
                tryHerbivore(X + this.right + 1, Y);
                this.right = 0;
            }
        }
    }
    public void setAge() {
        for (int i = 0; i < herbivoreCount; i++) {
            herbivoreTracker[i].age++;
        }
    }
    public void addTime(){
        time++;
        setAge();
        grow();
        move();
    }
    public int getHerbivoreCount(){
        return herbivoreCount;
    }
    public void giveBirth(){
        for (int i = 0; i < herbivoreCount; i++) {
            if(herbivoreTracker[i].age > 2 ||  herbivoreTracker[i].energy > 4)
                tryHerbivore(herbX[i], herbY[i]);
        }

    }
    @Override
    public void grow() {
        giveBirth();
    }
    private void moveHelper(int X, int Y, int j){
        if(Y-1 >= 0) {
            if (ecoArray[X][Y - 1] != "|" || ecoArray[X][Y - 1] != "!") {
                int rand = randGen();
                if(rand == 2) {
                    ecoArray[X][Y] = ".";
                    if(ecoArray[X][Y - 1] == "~")
                        herbivoreTracker[j].energy++;
                    ecoArray[X][Y - 1] = "&";
                    herbY[j] -= 1;
                    return;
                }
            }

        }
        if(Y+1 <= 31) {
            if (ecoArray[X][Y + 1] != "|" || ecoArray[X][Y + 1] != "!") {
                int rand = randGen();
                if(rand == 2) {
                    ecoArray[X][Y] = ".";
                    if(ecoArray[X][Y + 1] == "~")
                        herbivoreTracker[j].energy++;
                    ecoArray[X][Y + 1] = "&";
                    herbY[j] += 1;
                    return;
                }
            }
        }
        if(X-1 >= 0) {
            if (ecoArray[X - 1][Y] != "|" || ecoArray[X - 1][Y] != "!") {
                int rand = randGen();
                if(rand == 2) {
                    ecoArray[X][Y] = ".";
                    if(ecoArray[X - 1][Y] == "~")
                        herbivoreTracker[j].energy++;
                    ecoArray[X - 1][Y] = "&";
                    herbX[j] -= 1;
                    return;
                }
            }
        }
        if(X+1 <= 31) {
            if (ecoArray[X + 1][Y] != "|" || ecoArray[X + 1][Y] != "!") {
                int rand = randGen();
                if(rand == 2) {
                    ecoArray[X][Y] = ".";
                    if(ecoArray[X + 1][Y] == "~")
                        herbivoreTracker[j].energy++;
                    ecoArray[X + 1][Y] = "&";
                    herbX[j] += 1;
                    return;
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
    public void eat() {

    }

    public int randGen(){
        int Random = (int)(Math.random()*2+1);
        return Random;
    }
}
