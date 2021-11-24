import java.awt.*;
import java.awt.image.BufferedImage;

public interface Drawable extends Positioned, Comparable
{

    public boolean textured();

    public void setImage(String path);

    public Color getColor();
    public BufferedImage getImage();
    public double getSizeX();
    public double getSizeY();
    public double getRot();

    public int compareTo(Object o);

}