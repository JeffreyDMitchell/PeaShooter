import java.awt.Color;

public class Projectile implements Mobile, Drawable
{

    double x, y, xVel, yVel, decayFactor = 1, size = 10;
    Shooter source;
    int allegiance = 1;

    Projectile()
    {

        x = y = xVel = yVel = 0;

    }

    Projectile(double x, double y)
    {

        this.x = x;
        this.y = y;

    }

    Projectile(double x, double y, double xVel, double yVel)
    {

        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;

    }

    Projectile(Shooter source, double xVel, double yVel)
    {

        this.source = source;

        allegiance = source.getAllegiance();

        this.x = source.getX();
        this.y = source.getY();

        this.xVel = xVel;
        this.yVel = yVel;

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
        return Color.BLACK;
    }

    @Override
    public double getSize() 
    {
        return size;
    }

}