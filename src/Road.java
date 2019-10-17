import java.util.ArrayList;

public class Road {
    private static ArrayList<TrafficLight> trafficLights = new ArrayList<>();
    protected int numOfSegments; // each segment is 1m of road
    private static int trafficLightCount = -1;
    private Boolean containsTrafficLight;
    private TrafficLight tLOnCurrentRoad;


    Road(int numOfSegments, Boolean containsTrafficLight){
        this.numOfSegments = numOfSegments;
        this.containsTrafficLight = containsTrafficLight;
        if(containsTrafficLight){
            trafficLights.add(new TrafficLight());
            trafficLightCount += 1;
        }
        tLOnCurrentRoad = trafficLights.get(trafficLightCount); //fix
    }

    public static Road nextRoad(){
        return Map.roads.get(1);    //fix
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
    public boolean checkForTrafficLight(){
        return containsTrafficLight;
    }

}
