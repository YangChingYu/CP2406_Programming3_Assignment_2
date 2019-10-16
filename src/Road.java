import java.util.ArrayList;
import java.util.List;

public class Road {
    private TrafficLight[] trafficLights = new TrafficLight[1];
    protected int numOfSegments; // each segment is 1m of road
    Boolean containsTrafficLight;

    Road(int numOfSegments, Boolean containsTrafficLight){
        this.numOfSegments = numOfSegments;
        this.containsTrafficLight = containsTrafficLight;
        addTrafficLight();
    }

    public void nextRoad(){
    }

    public int getRoadLength(){
        return numOfSegments;
    }

    public void addTrafficLight(){
        if(containsTrafficLight){
            trafficLights[0] = new TrafficLight();
        }
    }
}
