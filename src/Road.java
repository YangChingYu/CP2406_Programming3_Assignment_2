import javax.swing.*;
import java.awt.*;

public class Road extends JPanel {
    private TrafficLight light;
    private int numOfSegments; //length of road
    final int roadWidth = 100;
    final int roadYPos = 0;
    final int roadXPos = 0;
    private Color lightColor = Color.green;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(roadXPos, roadYPos,numOfSegments*50 + 50, roadWidth);
        g.setColor(Color.white);
        for(int j = 0; j < numOfSegments*50 + 50 ;j = j + 50) { // line being drawn
            g.fillRect(j, roadWidth/2, 30, 5);
            }
        for(int c = 0; c < Map.cars.size() ;c++){
            Map.cars.get(c).paintMe(g);
        }
        for(int t = 0; t < Map.roads.size() ;t++){
            Road r = Map.roads.get(t);
            if(r.getTrafficLight() != null){
                r.paintMe(g);
            }
        }
    }
    public void paintMe(Graphics g){
        g.setColor(lightColor);
        g.fillRect(roadXPos+numOfSegments*50+50, roadYPos, 10, roadWidth);
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
    public void setLightColor(Color c){
        lightColor = c;
    }

}
