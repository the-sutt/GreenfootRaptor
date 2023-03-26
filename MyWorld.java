import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    private Background _bg;
    private Ship _ship;
    private int _worldYCoord = 0;

    private MouseInfo _mouse;

    public MyWorld()
    {    
        super(800, 600, 1);
        Init();
    }

    public void Init() {
        _bg = new Background();
        _bg.Init(this);
        _ship = new Ship();
        _ship.Init(this);
    }

    // public MouseInfo getMouseInfo() {
    //     return _mouse;
    // }

    public void act() {
        // advance the world
        _worldYCoord++;
        // grab latest mousinfo
        _mouse = Greenfoot.getMouseInfo();        

        if (_mouse != null) {
            // update targetPosition for ship
            _ship.setTarget(_mouse.getX(), _mouse.getY());
        }

        _bg.setCoord(_worldYCoord);

        RepaintStage();
    }

    private void RepaintStage() {
        _bg.Refresh();
        _ship.Refresh();
    }
}
