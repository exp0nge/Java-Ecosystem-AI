/**
 * Created by MD on 4/1/2015.
 */
public class Carnivore implements Organism{
    private boolean onPlant = false;
    private static int ENERGY_MAX = 1;
    private int age = 1;
    private int energy = 3;
    private final static int MAX = 300;
    public static Carnivore [] carnTracker = new Carnivore[300];
    private static int [] carnX = new int[MAX];
    private static int [] carnY = new int[MAX];
    private static int carnivoreCount = 0;
    private static Object [][] ecoArray;
    public final int initPop = 40;
    public static int time = 1;

    public static int left = 0;
    public static int right = 0;

    public Carnivore(Object [][] ecoArray){
        this.ecoArray = ecoArray;
    }
    public Carnivore(int X, int Y){
        if(tryCarnivore(X, Y))
            this.ecoArray[X][Y] = ".";
        else
            this.ecoArray[X][Y] = "!";


    }
    private boolean tryCarnivore(int X, int Y){
        if(carnivoreCount == MAX)
            return false;
        if (this.ecoArray[X][Y] == "." || this.ecoArray[X][Y] == "~" || this.ecoArray[X][Y] == "&") {
            this.carnTracker[carnivoreCount] = this;
            this.carnX[carnivoreCount] = X;
            this.carnY[carnivoreCount] = Y;
            if(this.ecoArray[X][Y] == "&")
                eat(X, Y, carnivoreCount);
            else if(this.ecoArray[X][Y] == "~")
                this.onPlant = true;
            this.ecoArray[X][Y] = "!";
            carnivoreCount++;
            return true;
        }else{
            if((Y - 1) >= 0) {
                this.left++;
                tryCarnivore(X, Y - 1);
            }
            else if((Y + this.left + 1) < 32){
                tryCarnivore(X, Y + this.left + 1);
                this.left = 0;
            }else if((X - 1) >= 0 ) {
                this.right++;
                tryCarnivore(X - 1, Y);
            }else if((X + this.right + 1) < 32){
                tryCarnivore(X + this.right + 1, Y);
                this.right = 0;
            }
        }
        return false;
    }
    public void addTime(){
        this.time++;
        move();
        setAge();
    }
    private void removeCarnivore(int i){
        if(carnTracker[i].onPlant)
            this.ecoArray[carnX[i]][carnY[i]] = "~";
        else
            this.ecoArray[carnX[i]][carnY[i]] = ".";
        System.arraycopy(carnTracker, i + 1, carnTracker, i, carnTracker.length - 1 -i);
        System.arraycopy(carnX, i + 1, carnX, i, carnX.length - 1 - i);
        System.arraycopy(carnY, i + 1, carnY, i, carnY.length - 1 - i);
        carnivoreCount--;
    }
    private void setAge(){
        for (int i = 0; i < carnivoreCount; i++) {
            carnTracker[i].age++;
            carnTracker[i].energy--;
            if(carnTracker[i].age > 5 && carnTracker[i].energy > 6) {
                tryCarnivore(carnX[i], carnY[i]);
            }
            if(carnTracker[i].energy < 1) removeCarnivore(i);
        }
    }
    public int getCarnivoreCount(){
        return carnivoreCount;
    }
    @Override
    public void grow() {
        //done in setAge()
    }
    private void moveHelper(int X, int Y, int j){
        if(Y - 1 >= 0) {
            if (ecoArray[X][Y - 1] == "." || ecoArray[X][Y - 1] == "~" || ecoArray[X][Y - 1] == "&") {
                int rand = randGen();
                if(rand == 2) {
                    if(this.carnTracker[j].onPlant == true)
                        ecoArray[X][Y] = "~";
                    else
                        ecoArray[X][Y] = ".";
                    if(ecoArray[X][Y - 1] == "&")
                        eat(X, Y - 1, j);
                    else if(ecoArray[X][Y - 1] == "~")
                        removePlant(X, Y-1);
                    ecoArray[X][Y - 1] = "!";
                    carnY[j] -= 1;
                    return;
                }
            }

        }
        if(Y+1 <= 31) {
            if (ecoArray[X][Y + 1] == "." || ecoArray[X][Y + 1] == "~"|| ecoArray[X][Y + 1] == "&") {
                int rand = randGen();
                if(rand == 2) {
                    if(this.carnTracker[j].onPlant == true)
                        ecoArray[X][Y] = "~";
                    else
                        ecoArray[X][Y] = ".";
                    if(ecoArray[X][Y + 1] == "&")
                        eat(X, Y + 1, j);
                    else if(ecoArray[X][Y + 1] == "~")
                        removePlant(X, Y + 1);
                    ecoArray[X][Y + 1] = "!";
                    carnY[j] += 1;
                    return;
                }
            }
        }
        if(X-1 >= 0) {
            if (ecoArray[X - 1][Y] == "." || ecoArray[X - 1][Y] == "~"|| ecoArray[X -1 ][Y] == "&") {
                int rand = randGen();
                if(rand == 2) {
                    if(this.carnTracker[j].onPlant == true)
                        ecoArray[X][Y] = "~";
                    else
                        ecoArray[X][Y] = ".";
                    if(ecoArray[X - 1][Y] == "&")
                        eat(X - 1, Y, j);
                    else if(ecoArray[X - 1][Y] == "~")
                        removePlant(X - 1, Y);
                    ecoArray[X - 1][Y] = "!";
                    carnX[j] -= 1;
                    return;
                }
            }
        }
        if(X+1 <= 31) {
            if (ecoArray[X + 1][Y] == "." || ecoArray[X + 1][Y] == "~"|| ecoArray[X + 1][Y] == "&") {
                int rand = randGen();
                if(rand == 2) {
                    if(this.carnTracker[j].onPlant == true)
                        ecoArray[X][Y] = "~";
                    else
                        ecoArray[X][Y] = ".";
                    if(ecoArray[X + 1][Y] == "&")
                        eat(X + 1, Y, j);
                    else if(ecoArray[X + 1][Y] == "~")
                        removePlant(X + 1, Y);
                    ecoArray[X + 1][Y] = "!";
                    carnX[j] += 1;
                }
            }
        }
    }
    private void removePlant(int X, int Y){
        int plantIndex = 0;
        for (int i = 0; i < Plant.usedArray; i++) {
            if(Plant.xPlant[i] == X){
                for (int j = 0; j < Plant.usedArray; j++) {
                    if(Plant.yPlant[j] == Y){
                        plantIndex = j;
                        System.arraycopy(Plant.xPlant, plantIndex + 1, Plant.xPlant, plantIndex, Plant.usedArray - 1 -plantIndex);
                        System.arraycopy(Plant.yPlant, plantIndex + 1, Plant.yPlant, plantIndex, Plant.usedArray - 1 -plantIndex);
                        Plant.usedArray--;
                    }
                }
            }
        }

    }

    @Override
    public void move() {
        for (int i = 0; i < this.carnivoreCount; i++) {
            moveHelper(carnX[i], carnY[i], i);
        }
    }

    @Override
    public void eat(int X, int Y, int orgIndex) {
        if(Herbivore.getHerbivoreCount() <= 0)
            return;
        carnTracker[orgIndex].energy += ENERGY_MAX;
        int herbIndex = 0;
        for (int i = 0; i < 32; i++) {
            if(Herbivore.herbX[i] == X){
                for (int j = 0; j < 32; j++) {
                    if(Herbivore.herbY[j] == Y){
                        herbIndex = j;
                        this.ecoArray[i][j] = ".";
                    }
                }
            }
        }
        System.arraycopy(Herbivore.herbX, herbIndex + 1, Herbivore.herbX, herbIndex, Herbivore.herbX.length - 1 - herbIndex);
        System.arraycopy(Herbivore.herbY, herbIndex + 1, Herbivore.herbY, herbIndex, Herbivore.herbY.length - 1 - herbIndex);
        System.arraycopy(Herbivore.herbivoreTracker, herbIndex + 1, Herbivore.herbivoreTracker, herbIndex, Herbivore.herbivoreTracker.length - 1 - herbIndex);
        Herbivore.decreaseCount();

    }
    public int randGen(){
        int Random = (int)(Math.random()*2+1);
        return Random;
    }
}
