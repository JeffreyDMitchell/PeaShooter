public class Point implements Positioned
{
    double x, y;

    public Point(double x, double y)
    {

        this.x = x;
        this.y = y;

    }

    @Override
    public double getX() {
        // TODO Auto-generated method stub
        return x;
    }

    @Override
    public double getY() {
        // TODO Auto-generated method stub
        return y;
    }

    @Override
    public void setX(double in) {
        // TODO Auto-generated method stub
        x = in;
        
    }

    @Override
    public void setY(double in) {
        // TODO Auto-generated method stub
        y = in;
        
    }
}
