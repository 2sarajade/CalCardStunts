import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Shreyas Chand
 */
public class Stunt extends JLabel {
    private BufferedImage imageFile;

    private int stuntHeight;
    private int stuntWidth;

    private static final HashMap<Integer, String> colorMap = new HashMap<Integer, String>();

    static {
        colorMap.put(-16776961, "Blue");
        colorMap.put(-256, "Yellow");
        colorMap.put(-65536, "Red");
        colorMap.put(-1, "White");
        colorMap.put(-16777216, "Black");
        colorMap.put(-16724992, "Green");
        colorMap.put(-26368, "Orange");
        colorMap.put(-8388480, "Purple");
        colorMap.put(-16711681, "Light Blue");
        colorMap.put(-6724096, "Brown");
    }

    public Stunt(BufferedImage imageFile) {
        super(new ImageIcon(imageFile));
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        setImage(imageFile);
    }

    public void setImage(BufferedImage imageFile) {
        this.imageFile = imageFile;
        this.stuntHeight = imageFile.getHeight();
        this.stuntWidth = imageFile.getWidth();
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
