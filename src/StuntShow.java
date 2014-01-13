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

    public int getNumOfStunts() {
        return stuntList.size();
    }

    public String getSeatColor(int stuntNum, int row, int seat) {
        return stuntList.get(stuntNum).getColor(row, seat);
    }

    public ArrayList<Stunt> getStunts() {
        return stuntList;
    }

    public int getStuntPosition(Stunt stunt) {
        for (int i = 0; i < stuntList.size(); i++) {
            if (stuntList.get(i) == stunt) {
                return i;
            }
        }
        return -1;
    }

    public void insertStunt(Stunt stunt, int index) {
        stuntList.add(index, stunt);
    }

    public void setStuntPosition(Stunt stunt, int index) {
        if (index >= 0 && index < getNumOfStunts()) {
            removeStunt(stunt);
            insertStunt(stunt, index);
        }
    }
}
