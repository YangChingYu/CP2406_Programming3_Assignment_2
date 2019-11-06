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
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 1200, 1200);

        for(int z = 0; z < Map.roads.size();z++){
            Road r = Map.roads.get(z);
            r.paintRoad(g);
            if(r.getOrientation().equals("vertical")){
                for (int c = 0; c < Map.cars.size(); c++) {
                    Car car = Map.cars.get(c);
                    if(car.getRoadCarIsOn().equals(r)) {
                        car.paintMeVertical(g);
                    }
                }
                if (r.getTrafficLight() != null) {
                    r.paintLightVertical(g);
                }
            }
            else{
                for (int c = 0; c < Map.cars.size(); c++) {
                    Car car = Map.cars.get(c);
                    if(car.getRoadCarIsOn().equals(r)) {
                        car.paintMeHorizontal(g);
                    }
                }
                if (r.getTrafficLight() != null) {
                    r.paintLightHorizontal(g);
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
        if(orientation.equals("horizontal")) {
            g.setColor(Color.black);
            g.fillRect(roadXPos, roadYPos,numOfSegments * 25, roadWidth);
            g.setColor(Color.WHITE);
            for (int j = 0; j < numOfSegments * 25; j = j + 50) { // line being drawn
                g.fillRect(roadXPos + j, roadYPos + roadWidth / 2, 30, 2);
            }
        }
        else{
            g.setColor(Color.black);
            g.fillRect(roadYPos, roadXPos, roadWidth, numOfSegments * 25);
            g.setColor(Color.WHITE);
            for (int j = 0; j < numOfSegments * 25; j = j + 50) { // line being drawn
                g.fillRect( roadYPos + roadWidth / 2, roadXPos + j, 2,30);
            }
        }
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
