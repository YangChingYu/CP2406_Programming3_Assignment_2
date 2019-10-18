import java.util.ArrayList;

public class Map {
    protected static ArrayList<Road> roads = new ArrayList<>(); //array that contains roads without lighs
    protected static int roadCount = -1;


    public void addRoad(Road road){
        roads.add(road);
        roadCount += 1;
    }


}
