import greenfoot.*;

public class Background implements IDrawable
{
    private World _world;
    private GreenfootImage _img;
    private int _coord = 0;

    public Background()
    {
        _img = new GreenfootImage("surface.jpg");
    }

    public void Init(World world) {
        _world = world;
    }

    public void setCoord(int coord) {
        _coord = coord;
    }

    public void Refresh() {
        if (true) return;
        int toUseCoord = (_coord/3) % 500;
        _world.getBackground().drawImage(_img, 0, toUseCoord);
        _world.getBackground().drawImage(_img, 0, toUseCoord+500);
        _world.getBackground().drawImage(_img, 0, toUseCoord-500);
    }
}
