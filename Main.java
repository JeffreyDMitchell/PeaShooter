import javax.swing.*;

public class Main 
{

    public static void main(String[] args)
    {

        int x = 1920, y = 1080;

        if(args.length >= 2)
        {

            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);

        }

        System.setProperty("sun.java2d.opengl", "true");

        JFrame f = new JFrame("Pea Shooter!");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(x, y);
        f.setVisible(true);

        f.add(new Panel(x, y));

    }

}
