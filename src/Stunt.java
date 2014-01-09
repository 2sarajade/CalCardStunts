import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Shreyas Chand
 */
public class Stunt extends ImageIcon {
    private BufferedImage imageFile;
    private boolean animated;
    private BufferedImage[] animationFrames;

    private int stuntHeight;
    private int stuntWidth;

    private static HashMap<Integer, String> colorMap = new HashMap<Integer, String>();

    static {
        colorMap.put(-1, "White");
        colorMap.put(-65536, "Red");
        colorMap.put(-3840, "Yellow");
        colorMap.put(-16776961, "Blue");
        colorMap.put(-16777216, "Black");
        //colorMap.put(, "Green");
        colorMap.put(-26368, "Orange");
        //colorMap.put(, "Purple");
        //colorMap.put(, "Light Blue");
        //colorMap.put(, "Brown");
    }

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
            int colorCode = imageFile.getRGB(seat, (stuntHeight - 1) - row);
            if (colorMap.containsKey(colorCode)) {
                return colorMap.get(colorCode);
            } else {
                return String.valueOf(colorCode);
            }
        }
    }
}
