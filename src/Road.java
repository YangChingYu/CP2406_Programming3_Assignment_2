import java.util.ArrayList;

public class Road {
    private static ArrayList<TrafficLight> trafficLights = new ArrayList<>();
    protected int numOfSegments; // each segment is 1m of road
    private Boolean containsTrafficLight;
    private TrafficLight tLOnCurrentRoad;

    Road(int numOfSegments, Boolean containsTrafficLight){
        this.numOfSegments = numOfSegments;
        this.containsTrafficLight = containsTrafficLight;
        if(containsTrafficLight){
            trafficLights.add(new TrafficLight());
        }
        tLOnCurrentRoad = trafficLights.get(0);
    }

    public void nextRoad(){
    }

    public int getRoadLength(){
        return numOfSegments;
    }

    public TrafficLight getTLOnCurrentRoad(){
        return tLOnCurrentRoad;
    }
    public void addTrafficLight(TrafficLight light){
        trafficLights.add(light);
    }

}
