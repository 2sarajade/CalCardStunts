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
}
