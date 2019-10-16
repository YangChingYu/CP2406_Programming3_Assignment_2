import java.util.ArrayList;

public class Road {
    protected ArrayList<TrafficLight> trafficLights = new ArrayList<>();
    protected int numOfSegments; // each segment is 1m of road
    private int trafficLightPosition;
    private Boolean containsTrafficLight;

    Road(int numOfSegments, Boolean containsTrafficLight){
        this.numOfSegments = numOfSegments;
        this.containsTrafficLight = containsTrafficLight;
    }

    public void nextRoad(){
    }

    public int getRoadLength(){
        return numOfSegments;
    }

    public void addTrafficLight(TrafficLight light){
        if(containsTrafficLight){
            trafficLights.add(light);
            trafficLightPosition = numOfSegments;
        }
    }
}
