import greenfoot.*;

public class Ship implements IDrawable
{
    private World _world;

    private int _targetX = 0;
    private int _targetY = 0;

    private static final int MARGIN = 25;

    private GreenfootImage _ship;
    private GreenfootImage _shadow;

    public Ship()
    {
        _ship = new GreenfootImage("ship.png");
        _shadow = new GreenfootImage("ship_shadow.png");
    }

    public void Init(World world) {
        _world = world;
    }

    public void Refresh() {
        int newX = _targetX-(_ship.getWidth()/2);
        int newY = _targetY-(_ship.getHeight()/2);

        int shadowX = (int)Math.round((newX-400)*.75) + 400;
        int shadowY = (int)Math.round((newY-300)*.75) + 300;

        _world.getBackground().drawImage(_shadow, shadowX, shadowY);
        _world.getBackground().drawImage(_ship, newX, newY);
    }

    public void setTarget(int x, int y) {
        if (x < MARGIN) return;
        if (x > 800-MARGIN) return;
        if (y < MARGIN) return;
        if (y > 600-MARGIN) return;
        _targetX = x;
        _targetY = y;
    }
}
