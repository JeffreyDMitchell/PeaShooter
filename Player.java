import java.awt.*;

public class Player implements Mobile, Drawable, Shooter
{
    //final int PLAYER_WIDTH = 50;
    double x = 0, y = 0, xVel = 0, yVel = 0, decayFactor = .98;
    int cooldown = 0, maxCooldown = 5, hp = 100, maxHP = 100, spread = 1, size = 50, allegiance = 1, dir;
    int[] fireDir = new int[4];

    Player()
    {

        x = y = xVel = yVel = 0;

    }

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
        int redChanel = 255 - (int) map(hp, maxHP, 0, 255, 0);

        return new Color(redChanel > 255 ? 255 : redChanel < 0 ? 0 : redChanel, 0, 0);
    }

    @Override
    public double getSize() {
        // TODO Auto-generated method stub
        return size;
    }

    public double map(double val, double omax, double omin, double max, double min)
    {

        return min + ((max - min) * ((val - omin) / (omax - omin)));

    }

}