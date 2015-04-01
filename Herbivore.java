/**
 * Created by MD on 3/31/2015.
 */
public class Herbivore implements Organism {
    private int age;
    private int herbSize;
    public static int time;

    public Herbivore() {
        this.age = 1;
        this.herbSize = 1;
    }

    public void setAge() {
        this.age++;
    }

    @Override
    public void grow() {
        if(age < 2)
            return;
        else
            this.herbSize++;
    }

    @Override
    public void move(int [] ecoArray) {

    }

    @Override
    public void eat(int [] ecoArray) {

    }
}
