import java.awt.*;
import java.awt.image.BufferedImage;

public interface Drawable extends Positioned
{

    public boolean textured();

    public void setImage(String path);

    public Color getColor();
    public BufferedImage getImage();
    public double getSize();
    public double getRot();

}