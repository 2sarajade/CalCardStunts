import java.util.HashMap;

/**
 * Created by Shreyas Chand
 */
public class StuntShow {

    private HashMap<Integer, Stunt> stuntOrdering;
    private int numberOfStunts;

    public StuntShow() {
        this.stuntOrdering = new HashMap<Integer, Stunt>();
    }

    public void addStunt(Stunt stunt) {
        stuntOrdering.put(++numberOfStunts, stunt);
    }

    public Stunt[] getStunts() {
        Stunt[] stunts = new Stunt[numberOfStunts];
        for (int i = 0; i < numberOfStunts; i++) {
            stunts[i] = stuntOrdering.get(i);
        }
        return stunts;
    }

}
