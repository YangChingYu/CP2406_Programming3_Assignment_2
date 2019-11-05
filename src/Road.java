import javax.swing.*;
import java.awt.*;

public class Road extends JPanel {
    private TrafficLight light;
    private int numOfSegments; //length of road
    private final int roadWidth = 50;
    final int roadYPos;
    final int roadXPos;
    private Color lightColor = Color.green;
    private String orientation;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(getOrientation().equals("horizontal")) {
            g.setColor(Color.black);
            g.fillRect(roadXPos, roadYPos, numOfSegments * 25, roadWidth);
            g.setColor(Color.white);
            for (int j = 0; j < numOfSegments * 25; j = j + 50) { // line being drawn
                g.fillRect(roadXPos+j, roadYPos + roadWidth/2, 30, 2);
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
            for(int r = 0; r < Map.roads.size();r++) {
                Map.roads.get(r).paintRoad(g);
            }
            g.setColor(Color.white);
            for (int j = 0; j < numOfSegments * 25; j = j + 50) { // line being drawn
                g.fillRect( roadYPos + roadWidth / 2, roadXPos + j, 2,30);
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
        g.fillRect(roadXPos+numOfSegments*25, roadYPos, 10, roadWidth);
    }
    // paints traffic light for vertical road
    public void paintLightVertical(Graphics g){
        g.setColor(lightColor);
        g.fillRect( roadYPos, roadXPos+numOfSegments*25, roadWidth, 10);
    }
    public void paintRoad(Graphics g){
        g.setColor(Color.black);
        g.fillRect(roadYPos, roadXPos, roadWidth, numOfSegments * 25);
    }

    Road(int numOfSegments, String orientation, int xPos, int yPos){
        super();
        this.numOfSegments = numOfSegments*2;
        this.orientation = orientation;
        this.roadXPos = xPos;
        this.roadYPos = yPos;
    }
    Road(int numOfSegments, String orientation, int xPos, int yPos, TrafficLight light){
        super();
        this.numOfSegments = numOfSegments*2;
        this.orientation = orientation;
        this.light = light;
        this.roadXPos = xPos;
        this.roadYPos = yPos;

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
    public int getRoadXPos(){
        return roadXPos;
    }
    public void setLightColor(Color c){
        lightColor = c;
    }

}
