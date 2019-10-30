import javax.swing.*;
import java.awt.*;

public class Road extends JPanel {
    private TrafficLight light;
    private int numOfSegments; //length of road
    final int roadWidth = 100;
    final int roadYPos = 0;
    final int roadXPos = 0;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(roadXPos, roadYPos,numOfSegments*50 + 50, roadWidth);
        g.setColor(Color.white);
        for(int j = 0; j < numOfSegments*50+50 ;j = j + 50) { // line being drawn 
            g.fillRect(j, roadWidth/2, 30, 5);
            }
        for(int c = 0; c < Map.cars.size() ;c++){
            Map.cars.get(c).paintMe(g);
        }
    }

    Road(int numOfSegments){
        super();
        this.numOfSegments = numOfSegments;
    }
    Road(int numOfSegments, TrafficLight light){
        super();
        this.numOfSegments = numOfSegments;
        this.light = light;

    }
    public TrafficLight getTrafficLight(){
        return light;
    }
    public int getRoadLength(){
        return numOfSegments;
    }
    public int getRoadYPos(){
        return roadYPos;
    }

}
