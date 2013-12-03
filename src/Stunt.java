import java.awt.image.BufferedImage;

/**
 * Created by Shreyas Chand
 */
public class Stunt {
    private BufferedImage imageFile;
    private boolean animated;
    private BufferedImage[] animationFrames;

    public Stunt(BufferedImage imageFile) {
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
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimation(BufferedImage[] animationFrames) {
        this.animated = true;
        this.animationFrames = animationFrames;
    }
}
