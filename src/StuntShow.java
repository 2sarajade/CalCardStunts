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

    public void removeStunt(Stunt stunt) {
        boolean stuntFound = false;
        for (int i = 0; i < numberOfStunts; i++) {
            if (stuntFound) {
                Stunt otherStunt = stuntOrdering.get(i);
                stuntOrdering.put(i - 1, otherStunt);
            } else if (stuntOrdering.get(i) == stunt) {
                stuntOrdering.remove(i);
                stuntFound = true;
            }
        }
        System.out.println(stuntOrdering.remove(numberOfStunts--));
    }

    public Stunt[] getStunts() {
        Stunt[] stunts = new Stunt[numberOfStunts];
        for (int i = 0; i < numberOfStunts; i++) {
            stunts[i] = stuntOrdering.get(i);
        }
        return stunts;
    }

}
