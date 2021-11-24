import java.awt.*;
public class Prompt implements Positioned
{

    String text;
    long birth, life, duration;
    double x, y;
    Positioned anchor;

    Font font = new Font("TimesRoman", Font.PLAIN, 12);

    public Prompt()
    {

        

    }

    public Prompt(double x, double y, long life, String text)
    {
        this.x = x;
        this.y = y;
        this.life = duration = life;
        this.text = text;

    }

    public Prompt(Positioned anchor, long life, String text)
    {

        this.anchor = anchor;
        this.life = duration = life;
        this.text = text;
        
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double in) {
        x = in;        
    }

    @Override
    public void setY(double in) {
        y = in;        
    }
    
}
