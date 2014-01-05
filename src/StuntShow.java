import java.util.ArrayList;

/**
 * Created by Shreyas Chand
 */
public class StuntShow {

    private ArrayList<Stunt> stuntList;
    private int showHeight;
    private int showWidth;

    public StuntShow() {
        this.stuntList = new ArrayList<Stunt>();
    }

    public void addStunt(Stunt stunt) {
        stuntList.add(stunt);
        showHeight = Math.max(showHeight, stunt.getStuntHeight());
        showWidth = Math.max(showWidth, stunt.getStuntWidth());
    }

    public void removeStunt(Stunt stunt) {
        stuntList.remove(stunt);
        if (stunt.getStuntHeight() == showHeight || stunt.getStuntWidth() == showWidth) {
            showHeight = showWidth = 0;

            for (Stunt s : stuntList) {
                showHeight = Math.max(showHeight, s.getStuntHeight());
                showWidth = Math.max(showWidth, s.getStuntWidth());
            }
        }
    }

    public int getShowHeight() {
        return showHeight;
    }

    public int getShowWidth() {
        return showWidth;
    }
}
