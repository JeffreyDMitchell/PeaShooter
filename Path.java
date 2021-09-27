public class Path 
{
    
    Point a, b;

    public Path(Point a, Point b)
    {

        this.a = a;
        this.b = b;

    }

    public Point getPos(double prop)
    {

        prop %= 2;

        prop = -1 * Math.abs(prop - 1) + 1;

        return new Point(a.x + (prop * (b.x - a.x)), a.y + (prop * (b.y - a.y)));

    }

}
