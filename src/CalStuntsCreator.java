import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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

    public Stunt addStunt(File stuntImageFile) throws IOException {
        BufferedImage stuntImage = ImageIO.read(stuntImageFile);

        final Stunt stunt = new Stunt(stuntImage);
        show.addStunt(stunt);

        return stunt;
    }

    public void removeStunt(Stunt stunt) {
        show.removeStunt(stunt);
    }

    public void saveStuntDirections(String filename) throws IOException {
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        for(int row = 0; row < show.getShowHeight(); row++) {
            for(int seat = 0; seat < show.getShowWidth(); seat++) {
                writer.print((row + 1) + ", " + (seat + 1) + ", ");
                System.out.println("Directions for Row " + (row + 1) + ", Seat " + (seat + 1) + ": ");
                StringBuilder stuntsString = new StringBuilder();
                for (int stunt = 0; stunt < show.getNumOfStunts(); stunt++) {
                    stuntsString.append(show.getSeatColor(stunt, row, seat));
                    stuntsString.append(", ");
                    System.out.println("\tStunt " + (stunt + 1) + ": " + show.getSeatColor(stunt, row, seat));
                }
                writer.println(stuntsString.substring(0, stuntsString.length() - 2));
                writer.flush();
            }
        }
        writer.close();
    }
}
