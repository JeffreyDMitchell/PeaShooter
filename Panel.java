/*
-CLEAN UP
-make dodging less jank
-change emitter life / path duration stuff
-rework constructors and parameter setting mess
-make defining levels more concise, load levels from text file?
-make levels
-add level "scripting"
-add boss fights
*/
//dude this is actually so bad hehe 

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class Panel extends JPanel implements ActionListener, KeyListener
{
    
    Timer t = new Timer(5, this);

    ArrayList<Level> levels = new ArrayList<Level>();

    ArrayList<Player> pList = new ArrayList<Player>();
    ArrayList<Projectile> projList = new ArrayList<Projectile>();
    ArrayList<Mobile> movList = new ArrayList<Mobile>();
    ArrayList<Drawable> drawList = new ArrayList<Drawable>();
    ArrayList<Emitter> emitList = new ArrayList<Emitter>();

    ArrayList[] LISTS = new ArrayList[]{pList, projList, emitList, movList, drawList};

    BufferedImage shipImage;

    //int[] control = new int[4];

    Player player = new Player(960, 540);

    Level currentLevel;
    long levelTime = 0;

    Level levelOne;

    Emitter AHH;
    Emitter BHH;
    Emitter CHH;

    int fc = 0;

    final int WINDOWBAR_WIDTH = 85;
    final int PLAYER_MAX_SPEED = 10;
    final int PLAYER_WIDTH = 50;
    int SCREEN_WIDTH, SCREEN_HEIGHT;
    
    public Panel(int x, int y)
    {

        setBackground(new Color(0x676767));
        

        addKeyListener(this);
        setFocusable(true);

        SCREEN_WIDTH = x;
        SCREEN_HEIGHT = y - WINDOWBAR_WIDTH;

        try
        {

            shipImage = ImageIO.read(new File("./resources/ship.png"));

        }
        catch(IOException e)
        {

            System.out.println("uh oh fucky wucky");

        }

        setLists(player, new int[]{1, 0, 0, 1, 1});

        //TODO temp code
        player.hp = player.maxHP = 30;
        player.size = 25;
        player.allegiance = 0;
        player.decayFactor = .9;
        player.textured = true;
        player.image = shipImage;

        //TODO temp code spawn players at start
        for(int i = 0; i < 0; i++)
        {

            Player p = new Player(rand(0, SCREEN_WIDTH), rand(0, SCREEN_HEIGHT), rand(-3, 3), rand(-3, 3));

            p.decayFactor = 1;

            setLists(p, new int[]{1, 0, 0, 1, 1});

        }

        

        //TODO temp code testing emitters
        //AHH = new Emitter(new Path(new Point(100, 100), new Point(500, 700)), 5000);
        //BHH = new Emitter(new Path(new Point(500, 500), new Point(500, 700)), 5000);
        //CHH = new Emitter(new Path(new Point(100, 100), new Point(500, 700)), 5000);

        //BHH.mortal = false;

        //AHH = new Emitter(960, 540, 100000);

        levelOne = new Level();

        Emitter test = new Emitter(new Path(new Point(0, 0), new Point(500, 500)), 100);
        test.props = new double[]{100, 5, 2, 2, 2};
        test.type = 1;
        test.life = 15000;
        test.mortal = true;

        Emitter test2 = new Emitter(SCREEN_WIDTH / 2.0, SCREEN_HEIGHT / 2.0, 20000);
        test2.props = new double[]{20, 6, 1, .01, 1000};
        test2.type = 2;
        test2.mortal = true;

        //Emitter test3 = new Emitter(new Path(new Point(0, 0), new Point(SCREEN_WIDTH, SCREEN_HEIGHT)), 10000);

        ArrayList<Emitter> emitgthree = new ArrayList<Emitter>();
        ArrayList<Player> playgthree = new ArrayList<Player>();

        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++)
            {

                Emitter temp = new Emitter(new Path(new Point(-40 + (10 * i) - (300 * j), 1), new Point(SCREEN_WIDTH + 1200 + (10 * i) - (300 * j), 1)), 10000);
                temp.type = 3;
                temp.dir = 2;
                temp.props = new double[]{2, 4, 0};
                temp.allegiance = 2;

                emitgthree.add(temp);

            }

        double theta = 0;

        for(int i = 0; i < 8; i++)
        {

            Player temp = new Player(960 + 100 * Math.cos(theta), 540 + 100 * Math.sin(theta));

            playgthree.add(temp);

            theta += 2 * Math.PI / 8;

        }


        // levelOne.Groups.add(new ArrayList());

        // levelOne.Groups.get(0).add(test);
        // levelOne.Groups.get(0).add(new Player(750, 750, -1, -1));

        ArrayList<Player> playgzero = new ArrayList<Player>();
        ArrayList<Emitter> emitgzero = new ArrayList<Emitter>(); 

        ArrayList<Player> playgone = new ArrayList<Player>();
        ArrayList<Emitter> emitgone = new ArrayList<Emitter>();

        ArrayList<Emitter> emitgtwo = new ArrayList<Emitter>();
        
        //emitgzero.add(test);
        //playgzero.add(new Player(500, 500, 1, 1));

        playgone.add(new Player(300, 400));
        playgone.add(new Player(200, 21, 10, 10));
        playgone.add(new Player(400, 400));

        emitgtwo.add(test2);

        

        levelOne.Emitters.add(emitgzero);
        levelOne.Players.add(playgzero);
        levelOne.Times.add(0);

        levelOne.Emitters.add(emitgone);
        levelOne.Players.add(playgone);
        levelOne.Times.add(10000);

        levelOne.Emitters.add(emitgtwo);
        levelOne.Players.add(new ArrayList<Player>());
        levelOne.Times.add(20000);

        levelOne.Emitters.add(emitgthree);
        levelOne.Players.add(playgthree);
        levelOne.Times.add(45000);

        //AHH = new Emitter(player);

        //AHH.type = 1;//BHH.type = 2;
        //AHH.allegiance = 0;
        //CHH.type = 1;

        //AHH.props = new double[]{3, 10, 2};

        //AHH.props = new double[]{15, 5, 2, .01, 500};
        //BHH.props = new double[]{10, 6, 2, .005};
        //CHH.props = new double[]{100, 25, 2};

        //setLists(AHH, new int[]{0, 0, 1, 0, 0});
        //setLists(BHH, new int[]{0, 0, 1, 0, 0});
        //setLists(CHH, new int[]{0, 0, 1, 0, 0});

        loadLevel(levelOne);

        t.start();

    }

    public void loadLevel(Level level)
    {

        for(ArrayList l : LISTS)
        {

            l.clear();

        }

        setLists(player, new int[]{1, 0, 0, 1, 1});

        player.hp = player.maxHP;

        currentLevel = level;

        levelTime = 0;

    }

    //cuter random method
    public double rand(double min, double max)
    {

        return min + ((max - min) * Math.random());

    }

    //maps value from old range to new
    public double map(double val, double omax, double omin, double max, double min)
    {

        return min + ((max - min) * ((val - omin) / (omax - omin)));

    }

    //adds or removes objects from various lists
    public void setLists(Object o, int[] set)
    {

        if(LISTS.length != set.length)
            System.out.println("possible fuckup");

        for(int i = 0; i < Math.min(set.length, LISTS.length); i++)
        {

            if(set[i] == 1 && !LISTS[i].contains(o))
                LISTS[i].add(o);
            else if (set[i] == 0 && LISTS[i].contains(o))
                LISTS[i].remove(o);

        }

    }

    //if called with only one parameter, removes from all lists, overload
    public void setLists(Object o)
    {

        setLists(o, new int[LISTS.length]);

    }

    //explode overload
    public void explode(Shooter p, int count, double speed)
    {

        explode(p, count, speed, 0);

    }

    //explode overload
    public void explode(double x, double y, int count, double speed)
    {

        explode(x, y, count, speed, 0);

    }

    //explode "overload"
    public void explode(Shooter p, int count, double speed, double offset)
    {

        double theta = offset;

        for(int j = 0; j < count; j++)
        {

            Projectile proj = new Projectile(p, Math.sin(theta) * speed, Math.cos(theta) * speed);

            setLists(proj, new int[]{0, 1, 0, 1, 1});

            theta += 2 * Math.PI / count;

        }

    }

    //creates projectiles around target coordinate x, y, modulated by count speed and offset.
    public void explode(double x, double y, int count, double speed, double offset)
    {

        double theta = offset;

        for(int j = 0; j < count; j++)
        {

            Projectile proj = new Projectile(x, y, Math.sin(theta) * speed, Math.cos(theta) * speed);

            setLists(proj, new int[]{0, 1, 0, 1, 1});

            theta += 2 * Math.PI / count;

        }

    }

    void dodge(Player p)
    {

        //really jank way of keeping player from dodging in place. same as if p.control[0] == 0 && p.control[1] == 0 && .... probably worse tbh. oh well, only happens when dodging, so low impact
        if(.1 * p.control[0] + .2 * p.control[1] + .3 * p.control[2] + .4 * p.control[3] == 0)
            return;

        if(p.iFrames < 30)
            p.iFrames = 30;

        p.rFrames = 100;

        p.xVel += 10 * (p.control[2] + p.control[3]);
        p.yVel += 10 * (p.control[0] + p.control[1]);

    }

    //draws graphics to display
    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        //draws contents of drawList
        for(Drawable d : drawList)
        {

            if(d.textured())
            {

                BufferedImage image = d.getImage();


                g.drawImage(image, (int) (d.getX() - image.getWidth() / 2.0), (int) (d.getY() - image.getHeight() / 2.0), null);

            }
            else{

                g.setColor(d.getColor());
                g.fillOval((int) (d.getX() - d.getSize() / 2.0), (int) (d.getY() - d.getSize() / 2.0), (int) d.getSize(), (int) d.getSize());    

            }


        }

    }

    //game tick, activated by timer
    public void actionPerformed(ActionEvent e)
    {

        //updates player-controlled player's controls, really regretting the naming choices now
        if(player.rFrames < 60)
        {

            player.x += (player.control[2] + player.control[3]) * 2;
            player.y += (player.control[0] + player.control[1]) * 2;

        }

        for(Mobile m : movList)
        {

            //updates position by velocity for all mobiles
            m.cX(m.getXVel());
            m.cY(m.getYVel());

            m.setXVel(m.getXVel() * m.getDecayFactor());
            m.setYVel(m.getYVel() * m.getDecayFactor());

        }

        //updates for every player
        for(int i = 0; i < pList.size(); i++)
        {

            Player p = pList.get(i);

            //caps player velocity to PLAYER_MAX_SPEED
            double vVec = Math.sqrt(Math.pow(p.xVel, 2) + Math.pow(p.yVel, 2));

            if(vVec > PLAYER_MAX_SPEED)
            {

                p.xVel = PLAYER_MAX_SPEED * (p.xVel / vVec);
                p.yVel = PLAYER_MAX_SPEED * (p.yVel / vVec);

            }

            //player edge collision
            if(p.x + p.getSize() / 2.0 > SCREEN_WIDTH || p.x - p.getSize() / 2.0 < 0)
            {

                p.xVel *= -1;
                p.x =  (p.x + p.getSize() / 2.0 < (SCREEN_WIDTH - p.getSize() / 2.0 - p.x)) ? p.getSize() / 2.0 : SCREEN_WIDTH - p.getSize() / 2.0;

            }
            if(p.y + p.getSize() / 2.0 > SCREEN_HEIGHT || p.y - p.getSize() / 2.0 < 0)
            {

                p.yVel *= -1;
                p.y =  (p.y + p.getSize() / 2.0 < (SCREEN_HEIGHT - p.getSize() / 2.0 - p.y)) ? p.getSize() / 2.0 : SCREEN_HEIGHT - p.getSize() / 2.0;

            }

            //TODO BLeehhhh 🤮🤮🤮🤮🤮🤮🤮🤮🤮 LMAO remove this
            for(int j = 0; j < p.fireDir.length; j++)
            {

                if(p.fireDir[j] == 1 && p.cooldown == 0)
                {
                    p.cooldown = p.maxCooldown;

                    Projectile proj = new Projectile(p, 4 * Math.sin(Math.PI * j / 2.0) + rand(-1 * p.spread, p.spread), -4 * Math.cos(Math.PI * j / 2.0) + rand(-1 * p.spread, p.spread));

                    setLists(proj, new int[]{0, 1, 0, 1, 1});

                    break;

                }

            }

            //player dies (not player character)
            if(p.hp <= 0)
            {

                explode(p, 5, 2);

                setLists(p);

            }

            //increase health by 1 to max health every 100 frames
            if(fc % 100 == 0)
                p.hp += p.hp < p.maxHP ? 1 : 0;

            p.cooldown -= p.cooldown > 0 ? 1 : 0;

            if(p.rFrames > 0)
                p.rFrames--;

            if(p.iFrames > 0)
                p.iFrames--;

        }

        //projectile specific code
        for(int i = 0; i < projList.size(); i++)
        {

            Projectile proj = projList.get(i);

            //Deletes offscreen projectiles
            if(proj.x > SCREEN_WIDTH || proj.x < 0 || proj.y > SCREEN_HEIGHT || proj.y < 0)
            {

                setLists(proj);

                i -= i == 0 ? 0 : 1;

            }

            for(int j = 0; j < pList.size(); j++)
            {

                Player p = pList.get(j);

                //player hit detection, uses bounding box instead of circular collisions because maybe faster?
                if(proj.allegiance != p.allegiance && p.iFrames == 0 &&Math.abs(p.getX() - proj.getX()) < (proj.getSize() / 2.0 + p.getSize() / 2.0) && Math.abs(p.getY() - proj.getY()) < (proj.getSize() / 2.0 + p.getSize() / 2.0))
                {

                    p.hp -= 5;

                    setLists(proj);

                    i -= i == 0 ? 0 : 1;

                }

            }
            
        }

        //Emitter specific code
        for(int i = 0; i < emitList.size(); i++)
        {

            Emitter emit = emitList.get(i);

            //kills mortal emitters past lifespan
            if(emit.mortal && System.currentTimeMillis() >= emit.birth + emit.life)
            {

                System.out.println("Killing emitter");
                setLists(emit);

            }
                

            //moves pathed emitters along paths
            if(emit.path != null)
            {

                Point temp = emit.path.getPos((System.currentTimeMillis() - emit.birth) / (double) emit.life);

                emit.setX(temp.x);
                emit.setY(temp.y);

            }
            else if(emit.anchor != null)
            {

                emit.setX(emit.anchor.getX());
                emit.setY(emit.anchor.getY()); //TODO remove offset just for demo

            }

            //allows for different emitter behavior
            switch(emit.type)
            {

                case 0:
                    break;

                case 1:
                    if(fc % emit.props[0] == 0)
                        explode(emit, (int) emit.props[1], emit.props[2]);
                    break;

                case 2:
                    if(fc % emit.props[0] == 0)
                        explode(emit, (int) emit.props[1], emit.props[2], emit.props[3]* fc * ((fc / (int) emit.props[4])));
                        //System.out.println(((fc / (int) emit.props[4])));
                    break;
                case 3:
                    //shooter
                    if(fc % emit.props[0] == 0)
                    {

                        Projectile proj = new Projectile(emit, emit.props[1] * 2 * Math.sin(Math.PI * emit.dir / 2.0) + rand(-1 * emit.props[2], emit.props[2]), emit.props[1] * -2 * Math.cos(Math.PI * emit.dir / 2.0) + rand(-1 * emit.props[2], emit.props[2]));

                        setLists(proj, new int[]{0, 1, 0, 1, 1});

                    }
                    
                    break;

            }

        }

        if(fc % 15 == 0)
        {
            //TODO shift emitter type 2 parameters to enable this behavior
            //explode(AHH.getX(), AHH.getY(), 5, 2, .01 * fc * (fc / 1000));
            //explode(BHH.getX(), BHH.getY(), 5, 2, fc / 1000.0);

            // Player p = new Player(rand(0, SCREEN_WIDTH), rand(0, SCREEN_HEIGHT), rand(-3, 3), rand(-3, 3));
            
            // p.decayFactor = 1;

            // setLists(p, new int[]{1, 0, 0, 1, 1});

        }

        // if(fc % 500 == 0)
        //     System.out.println(projList.size());

        //player death, junk testing code TODO
        if(player.hp <= 0)
        {

            emitList.clear();

        }

        //System.out.println(currentLevel.Times.get(0));

        if(currentLevel.Times.size() != 0 && currentLevel.Times.get(0) <= levelTime)
        {

            //System.out.println("here" + levelTime);
            
            for(Player p : currentLevel.Players.get(0))
            {

                setLists(p, new int[]{1, 0, 0, 1, 1});

            }

            for(Emitter emit : currentLevel.Emitters.get(0))
            {

                emit.birth = System.currentTimeMillis();
                setLists(emit, new int[]{0, 0, 1, 0, 0});

            }

            currentLevel.Times.remove(0);
            currentLevel.Players.remove(0);
            currentLevel.Emitters.remove(0);

        }
        else if (currentLevel.Times.size() == 0 && pList.size() == 1 && pList.contains(player))
        {

            //TODO load next level

        }

        levelTime += t.getDelay();

        if(fc % 200 == 0 && fc < 1000)
        {

            explode(rand(0, 300), rand(0, SCREEN_HEIGHT), 25, 1);
            explode(rand(SCREEN_WIDTH - 300, SCREEN_WIDTH), rand(0, SCREEN_HEIGHT), 25, 1);

        }
             
         
        //iterates frame counter
        fc++;

        //draws new frame
        repaint();

    }

    //input
    @Override
    public void keyPressed(KeyEvent e) 
    {
        
        switch(e.getKeyCode())
        {

            case KeyEvent.VK_W:
                player.control[0] = -1;
                break;

            case KeyEvent.VK_S:
                player.control[1] = 1;
                break;

            case KeyEvent.VK_A:
                player.control[2] = -1;
                break;

            case KeyEvent.VK_D:
                player.control[3] = 1;
                break;

            case KeyEvent.VK_UP:
                player.fireDir[0] = 1;
                break;

            case KeyEvent.VK_DOWN:
                player.fireDir[2] = 1;
                break;

            case KeyEvent.VK_LEFT:
                player.fireDir[3] = 1;
                break;

            case KeyEvent.VK_RIGHT:
                player.fireDir[1] = 1;
                break;

            case KeyEvent.VK_SPACE:
                if(player.rFrames == 0)
                    dodge(player);
                break;

        }

        
    }

    //input
    @Override
    public void keyReleased(KeyEvent e) 
    {
    
        switch(e.getKeyCode())
        {

            case KeyEvent.VK_W:
                player.control[0] = 0;
                break;

            case KeyEvent.VK_S:
                player.control[1] = 0;
                break;

            case KeyEvent.VK_A:
                player.control[2] = 0;
                break;

            case KeyEvent.VK_D:
                player.control[3] = 0;
                break;

            case KeyEvent.VK_UP:
                player.fireDir[0] = 0;
                break;

            case KeyEvent.VK_DOWN:
                player.fireDir[2] = 0;
                break;

            case KeyEvent.VK_LEFT:
                player.fireDir[3] = 0;
                break;

            case KeyEvent.VK_RIGHT:
                player.fireDir[1] = 0;
                break;

        }
        
    }

    //necessary override
    @Override
    public void keyTyped(KeyEvent e) 
    {
    
        
        
    }

}
