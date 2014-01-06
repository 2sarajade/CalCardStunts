import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created by Shreyas Chand
 */
public class Stunt extends ImageIcon {
    private BufferedImage imageFile;
    private boolean animated;
    private BufferedImage[] animationFrames;

    private int stuntHeight;
    private int stuntWidth;

    public Stunt(BufferedImage imageFile) {
        super(imageFile);
        setImage(imageFile);
    }

    public Stunt(BufferedImage[] imageFiles) {
        setAnimation(imageFiles);
    }

    public BufferedImage getImage() {
        return imageFile;
    }

    public BufferedImage[] getAnimation() {
        return animationFrames;
    }

    public void setImage(BufferedImage imageFile) {
        this.imageFile = imageFile;
        this.stuntHeight = imageFile.getHeight();
        this.stuntWidth = imageFile.getWidth();
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimation(BufferedImage[] animationFrames) {
        this.animated = true;
        this.animationFrames = animationFrames;
    }

    public int getStuntHeight() {
        return stuntHeight;
    }

    public int getStuntWidth() {
        return stuntWidth;
    }

    public String getColor(int row, int seat) {
        if (row > stuntHeight || seat > stuntWidth) {
            return "N/A";
        } else {
            return String.valueOf(imageFile.getRGB(row, seat));
        }
    }
}
