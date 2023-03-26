import greenfoot.*;
import java.util.ArrayList;  

public class Ship implements IDrawable
{
    private World _world;

    private int _targetX = 0;
    private int _targetY = 0;
    private int _curX = 0;
    private int _curY = 0;

    private static final int MARGIN = 25;

    private GreenfootImage _ship;
    private GreenfootImage _shadow;
    private GreenfootImage _engine;

    private int _engineAnimation = 0;

    private long _lastShotFired = 0;
    private int _refireRate = 150;

    public ArrayList<Shot> Shots = new ArrayList<Shot>();

    public Ship()
    {
        _ship = new GreenfootImage("ship.png");
        _shadow = new GreenfootImage("ship_shadow.png");
        _engine = new GreenfootImage("ship_engine.png");
    }

    public void Init(World world) {
        _world = world;
    }

    public void Refresh() {
        _curX += (_targetX-_curX) / 10;
        _curY += (_targetY-_curY) / 10;

        int newX = _curX-(_ship.getWidth()/2);
        int newY = _curY-(_ship.getHeight()/2);


        int shadowX = (int)Math.round((newX-400)*.75) + 400;
        int shadowY = (int)Math.round((newY-300)*.75) + 300;

        // shadow
        _world.getBackground().drawImage(_shadow, shadowX, shadowY);
        // engine
        if (_engineAnimation++ > 4) _engineAnimation = 0;
        int engineOffsetX = _ship.getWidth()/2-6;
        int engineOffsetY = _ship.getHeight()-8;
        if (_engineAnimation == 0) _engine.scale(15,50);
        if (_engineAnimation == 1) _engine.scale((int)(15*.8),(int)(50*.8));
        if (_engineAnimation == 2) {_engine.mirrorHorizontally(); _engine.scale(15,50);}
        if (_engineAnimation == 3) {_engine.mirrorHorizontally(); _engine.scale((int)(15*.8),(int)(50*.8));}
        _world.getBackground().drawImage(_engine, newX+engineOffsetX, newY+engineOffsetY);
        // ship
        _world.getBackground().drawImage(_ship, newX, newY);
        // shots
        ArrayList<Shot> newShots = new ArrayList<Shot>();
        for (Shot shot : Shots) {
            shot.y -= shot.speed;
            if (shot.y < -100) continue;
            newShots.add(shot);
            // paint the shot
            _world.getBackground().setColor(Color.ORANGE);
            _world.getBackground().fillRect(shot.x-1, shot.y-1, 3, shot.speed+3);
            _world.getBackground().setColor(Color.YELLOW);
            _world.getBackground().drawLine(shot.x, shot.y, shot.x, shot.y+shot.speed);
        }
        Shots = newShots;
    }

    public void setTarget(int x, int y) {
        if (x < MARGIN) x = MARGIN;
        if (x > 800-MARGIN) x = 800-MARGIN;
        if (y < MARGIN) y = MARGIN;
        if (y > 600-MARGIN) y = 600-MARGIN;
        _targetX = x;
        _targetY = y;
    }

    public void Fire() {
        if (System.currentTimeMillis() - _lastShotFired > _refireRate) {
            _lastShotFired = System.currentTimeMillis();
            Shot shot = new Shot();
            shot.x = _curX;
            shot.y = _curY-15;
            shot.speed = 10;
            Shots.add(shot);
        }
    }



    public class Shot {
        public int x = 0;
        public int y = 0;
        public int speed = 0;
    }
}
