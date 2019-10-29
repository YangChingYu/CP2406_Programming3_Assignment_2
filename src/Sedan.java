import java.awt.*;

public class Sedan extends Car{
    Sedan(Road road, String name){
        super(road, name);
        width = 60;
        height = 25;
    }
    public void paintMe(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(xPos*50, yPos, width, height);
    }
}
