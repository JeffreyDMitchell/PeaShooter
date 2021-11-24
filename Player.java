import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Player implements Mobile, Drawable, Shooter
{
    //final int PLAYER_WIDTH = 50;
    double x = 0, y = 0, xVel = 0, yVel = 0, decayFactor = .98, rot = 0, spread = 1;
    int cooldown = 0, maxCooldown = 5, hp = 20, maxHP = 20, sizeX = 50, sizeY = 50, allegiance = 1, dir, rFrames = 0, iFrames, rammingDmg = 1;
    Positioned target = null;
    boolean textured = false, rammingCD = false, alive = true;
    BufferedImage image = null;
    int[] fireDir = new int[4], control = new int[4];

    Player(double x, double y)
    {

        this.x = x;
        this.y = y;
        decayFactor = .99;

    }

    Player(double x, double y, double xVel, double yVel)
    {

        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
        decayFactor = .99;

    }

    Player(double x, double y, String image)
    {

        this.x = x;
        this.y = y;
        decayFactor = .99;

        textured = true;
        setImage(image);

        System.out.println("huh??");

    }

    Player(double x, double y, double xVel, double yVel, String image)
    {

        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
        decayFactor = .99;

        textured = true;
        setImage(image);

    }

    

    public void cXVel(double in) 
    {

        xVel += in;

    }

    public void cYVel(double in) 
    {

        yVel += in;

    }

    public void cX(double in) 
    {

        x += in;

    }

    public void cY(double in) 
    {

        y += in;

    }

    @Override
    public void setX(double in) 
    {
        
        x = in;

    }

    @Override
    public void setY(double in) 
    {
        y = in;
    }

    @Override
    public void setXVel(double in) 
    {

        xVel = in;

    }

    @Override
    public void setYVel(double in) 
    {

        yVel = in;

    }

    public void setDecayFactor(double in)
    {

        decayFactor = in;

    }

    public void setImage(String path)
    {

        try
        {

            image = ImageIO.read(new File(path));

        }
        catch(IOException e)
        {

            System.out.println("UH OHHHHH OOPSY WHOOPSIE");

        }

    }

    public void setRot(double rot)
    {

        this.rot = rot;

    }

    public void setRot(Positioned p)
    {

        setRot(p, 0);

    }

    public void setRot(Positioned p, double offset)
    {

        double y = this.y - p.getY();
        double x = this.x - p.getX();

        if(x == 0)
            x = Double.MIN_VALUE;

        //rotates players towards p
        this.rot = (Math.atan(y / x) + (((x + Math.abs(x)) / (2 * x)) * Math.PI) + (Math.PI / 2.0)) + offset;

    }

    public int getAllegiance()
    {

        return allegiance;

    }

    @Override
    public double getX() 
    {

        return x;

    }

    @Override
    public double getY() 
    {

        return y;

    }

    @Override
    public double getXVel() 
    {

        return xVel;

    }

    @Override
    public double getYVel() 
    {

        return yVel;

    }

    public double getDecayFactor()
    {

        return decayFactor;

    }

    @Override
    public Color getColor() 
    {

        // if(iFrames != 0)
        //     return Color.WHITE;

        int redChanel = 255 - (int) map(hp, maxHP, 0, 255, 0);

        return new Color(redChanel > 255 ? 255 : redChanel < 0 ? 0 : redChanel, 0, 0);
    }

    @Override
    public double getSizeX() 
    {
        // TODO Auto-generated method stub
        return sizeX;
    }

    public double getSizeY()
    {

        return sizeY;

    }

    public double getRot()
    {

        return rot;

    }

    public boolean textured()
    {

        return textured;

    }

    public BufferedImage getImage()
    {

        return image;

    }

    public double map(double val, double omax, double omin, double max, double min)
    {

        return min + ((max - min) * ((val - omin) / (omax - omin)));

    }

    @Override
    public int compareTo(Object o) 
    {
        // TODO Auto-generated method stub
        if(o instanceof Projectile)
            return 1;

        return 0;
        
    }

}
