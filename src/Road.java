import javax.swing.*;
import java.awt.*;

public class Road extends JPanel {
    private TrafficLight light;
    private int numOfSegments; //length of road
    final int roadWidth = 100;
    final int roadYPos = 200;
    final int roadXPos = 70;
    private Color lightColor = Color.green;
    private String orientation;

    public void paintComponent(Graphics g){
        if(getOrientation().equals("horizontal")) {
            super.paintComponent(g);
            g.setColor(Color.black);
            g.fillRect(roadXPos, roadYPos, numOfSegments * 50 + 50, roadWidth);
            g.setColor(Color.white);
            for (int j = 0; j < numOfSegments * 50 + 50; j = j + 50) { // line being drawn
                g.fillRect(roadXPos+j, roadYPos + roadWidth/2, 30, 5);
            }
            for (int c = 0; c < Map.cars.size(); c++) {
                Map.cars.get(c).paintMeHorizontal(g);
            }
            for (int t = 0; t < Map.roads.size(); t++) {
                Road r = Map.roads.get(t);
                if (r.getTrafficLight() != null) {
                    r.paintLightHorizontal(g);
                }
            }
        }
        else{
            super.paintComponent(g);
            g.setColor(Color.black);
            g.fillRect(roadYPos, roadXPos, roadWidth, numOfSegments * 50 + 50);
            g.setColor(Color.white);
            for (int j = 0; j < numOfSegments * 50 + 50; j = j + 50) { // line being drawn
                g.fillRect( roadYPos + roadWidth / 2, roadXPos + j, 5,30);
            }
            for (int c = 0; c < Map.cars.size(); c++) {
                Map.cars.get(c).paintMeVertical(g);
            }
            for (int t = 0; t < Map.roads.size(); t++) {
                Road r = Map.roads.get(t);
                if (r.getTrafficLight() != null) {
                    r.paintLightVertical(g);
                }
            }
        }
    }
    // paints traffic light for horizontal road
    public void paintLightHorizontal(Graphics g){
        g.setColor(lightColor);
        g.fillRect(roadXPos+numOfSegments*50+50, roadYPos, 10, roadWidth);
    }
    // paints traffic light for vertical road
    public void paintLightVertical(Graphics g){
        g.setColor(lightColor);
        g.fillRect( roadYPos, roadXPos+numOfSegments*50+50, roadWidth, 10);
    }

    Road(int numOfSegments, String orientation){
        super();
        this.numOfSegments = numOfSegments;
        this.orientation = orientation;
    }
    Road(int numOfSegments, String orientation, TrafficLight light){
        super();
        this.numOfSegments = numOfSegments;
        this.orientation = orientation;
        this.light = light;

    }
    public String getOrientation(){ return orientation;}
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
