public class Road {
    protected int numOfSegments; // each segment is 1m of road
    Boolean containsTrafficLight;

    Road(int numOfSegments, Boolean containsTrafficLight){
        this.numOfSegments = numOfSegments;
        this.containsTrafficLight = containsTrafficLight;
    }

    public void nextRoad(){
    }

    public int getRoadLength(){
        return numOfSegments;
    }
}
