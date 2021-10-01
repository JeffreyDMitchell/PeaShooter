import java.awt.*;
import java.awt.image.BufferedImage;

public interface Drawable extends Positioned
{

    public boolean textured();

    public Color getColor();
    public BufferedImage getImage();
    public double getSize();

}