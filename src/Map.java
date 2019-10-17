import java.util.ArrayList;

public class Map {
    protected static ArrayList<Road> roads = new ArrayList<>();
    protected static ArrayList<Road> roadsWithTrafficLights = new ArrayList<>();
    protected static int roadCount = -1;


    public void addRoad(Road road){
        if(road.checkForTrafficLight()){
            roadsWithTrafficLights.add(road);
            roadCount += 1;
        }
        else
            roads.add(road);
    }


}
