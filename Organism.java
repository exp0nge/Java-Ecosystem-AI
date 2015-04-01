/**
 * Created by MD on 3/31/2015.
 */
public interface Organism {
    final static int DAY = 15;
    final static int NIGHT = 16;
    final static int minENERGY = 1;

    public void grow();
    public void move();
    public void eat();
}
