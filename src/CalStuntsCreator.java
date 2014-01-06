import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Shreyas Chand
 */
public class CalStuntsCreator {

    private StuntShow show;
    private GUI gui;

    public static void main(String[] args) {
        new CalStuntsCreator().start();
    }

    private void start() {
        this.show = new StuntShow();
        this.gui = new GUI(this);
    }

    public Stunt addStunt(File stuntImageFile) {
        BufferedImage stuntImage = null;
        try {
            stuntImage = ImageIO.read(stuntImageFile);
        } catch (IOException e) {
            //e.printStackTrace();
            gui.fileError();
        }
        final Stunt stunt = new Stunt(stuntImage);
        show.addStunt(stunt);

        return stunt;
    }

    public void removeStunt(Stunt stunt) {
        show.removeStunt(stunt);
    }

    public void printStuntDirections() {
        // print directions for each seat
        System.out.println(show.getShowHeight());
        System.out.println(show.getShowWidth());
        for(int row = 0; row < show.getShowHeight(); row++) {
            for(int seat = 0; seat < show.getShowWidth(); seat++) {
                System.out.println("Directions for Row " + (row + 1) + ", Seat " + (seat + 1) + ": ");
                for (int stunt = 0; stunt < show.getNumOfStunts(); stunt++) {
                    System.out.println("\tStunt " + (stunt + 1) + ": " + show.getSeatColor(stunt, row, seat));
                }
            }
        }
    }
}
