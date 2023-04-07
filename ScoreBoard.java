import greenfoot.*;

public class ScoreBoard extends Actor {

    GreenfootImage bg = new GreenfootImage(320,480);

    private String[] _fields = {"1","2","3","4","5","6","ZWISCHENSUMME","BONUS","GESAMT"};
    private int _rowHeight = 36;
    private int[] _values = {5,1,2,3,5,6,56,45,5,5,40,50,250};

    private Font myFont = new Font("Hermit", false, false, 14);  

    public ScoreBoard() {
    }

    public void act() {
        bg.setColor(Color.GREEN);
        bg.fillRect(0,0,320,480);
        bg.setColor(Color.ORANGE);
        bg.drawRect(0,0,320,480);

        bg.setColor(Color.BLACK);
        bg.setFont(myFont);

        int rowCounter = 0;

        for (String entry : _fields) {
            bg.drawRect(0,_rowHeight*rowCounter,320,_rowHeight);
            bg.drawString(entry, 10, _rowHeight*(rowCounter+1)-7);
            bg.drawString(Integer.toString(_values[rowCounter]), 250, _rowHeight*(rowCounter+1)-7);

            rowCounter++;
        }
        bg.drawLine(160,0,160,480);
        setImage(bg);

        _values[5]++;
    }
}