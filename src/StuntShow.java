import java.util.ArrayList;

/**
 * Created by Shreyas Chand
 */
public class StuntShow {

    private ArrayList<Stunt> stuntList;

    public StuntShow() {
        this.stuntList = new ArrayList<Stunt>();
    }

    public void addStunt(Stunt stunt) {
        stuntList.add(stunt);
    }

    public void removeStunt(Stunt stunt) {
        stuntList.remove(stunt);
    }

}
