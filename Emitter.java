public class Emitter implements Mobile, Shooter
{

    double x, y, xVel, yVel, xOffset = 0, yOffset = 0;
    boolean mortal = false;
    long birth;
    int life, type = 0, allegiance = 1, dir; //0 = dormant, 1 = explosion, 2 = bullethell, 3 = shooter
    double[] props = new double[5];
    Path path;
    Positioned anchor;

    public Emitter(Path path, int life)
    {

        this.path = path;
        this.life = life;

        x = path.getPos(0).x;
        y = path.getPos(0).y;

    }

    public Emitter(Positioned anchor)
    {

        this.anchor = anchor;

    }

    public Emitter(double x, double y)
    {

        this.x = x;
        this.y = y;

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

    public int getAllegiance()
    {

        return allegiance;

    }

    @Override
    public double getDecayFactor() 
    {

        return 1;
        
    }

    @Override
    public void setDecayFactor(double in) 
    {
        


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
    
}
