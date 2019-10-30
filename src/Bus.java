import java.awt.*;

public class Bus extends Car {
    Bus(Road road){
        super(road);
        width = 90;
        height = 25;
    }
    public void paintMe(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(xPos, yPos, width, height);
    }
}
